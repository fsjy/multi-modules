package cn.com.bmsmart.activiti.aop.aspect.impl;

import cn.com.bmsmart.activiti.aop.aspect.entities.ProblemsNativeMapping;
import cn.com.bmsmart.activiti.constant.CONST;
import cn.com.bmsmart.activiti.tookit.JsonModifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 【处理】：现有activiti中，对流程图进行保存时，在调用
 * {@link org.activiti.rest.editor.model.ModelSaveRestResource#saveModel(String, MultiValueMap)}的进行拦截处理。
 * xml配置中需强制aop使用CGLIB方式进行proxy.
 * <p/>
 * <p>
 * 【目的】：1. 重写process_id 2. 过滤掉所有自定义的节点id，替换成activiti原生id
 * <p/>
 * <p>
 * 【原因】：
 * 「1」. process_id在web端建立时所有的process_id相同，在保存处理之前需要替换成可唯一识别的key，采用resource_id进行替换使得process_id。
 * 使得在instance启动的场合,第一个参数传入替换后的process_id
 * {@link org.activiti.engine.RuntimeService#startProcessInstanceByKey(String, Map)}。
 * <p/>
 * <p>
 * 「2」. stencilset.json中一个ruleTaskService被定义为多个，id也相应自定义了多个，但在activiti中自定义的id无法被识别，在aspect中需进行
 * id的复原处理，最后将json保存到数据库中
 *
 * @author Yanglu
 * @see org.activiti.rest.editor.model.ModelSaveRestResource#saveModel(String, MultiValueMap)
 * @see org.activiti.engine.RuntimeService#startProcessInstanceById(String, Map)
 */
@Aspect
public class AspectModelSavePointcutImpl {

    private static final Logger log = LoggerFactory.getLogger(AspectModelSavePointcutImpl.class);


    @Autowired
    private ObjectMapper objectMapper;

    /**
     * aspectJ切面执行
     *
     * @param joinPoint
     * @return aop的实际处理，并传入修改后的参数
     * @throws Throwable
     */
    @Around("execution(* org.activiti.rest.editor.model.ModelSaveRestResource.saveModel(..))")
    public Object refreshArgs(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] objects = joinPoint.getArgs();

        //Subject currentSubject = SecurityUtils.getSubject();

        // 从shiro中取得
        String authorName = String.valueOf("temp_yanglu");


        if (objects != null && objects.length > 1 && objects[1] instanceof MultiValueMap) {

            MultiValueMap<String, String> multiValueMap = (MultiValueMap) objects[1];

            String values = multiValueMap.getFirst("json_xml").toString();

            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(values);
            ObjectNode checkNode = (ObjectNode) objectMapper.readTree(values);

            // 为可唯一识别每一个建立的process,替换原有process_id为userid + "_" + resource_id(即数据库【ACT_RE_MODEL】中的ID_)
            JsonModifier.changeNodeValue(modelJson,

                    CONST.PROPERTY_PROCESS_ID,
                    null,
                    authorName.concat("_").concat(modelJson.get(CONST.PROPERTY_RESOURCE_ID).asText()),
                    false);


            ((MultiValueMap) objects[1]).set("json_xml", modelJson.toString());


            // 暂时 ------------------------------------------------------------------------------------
            JsonModifier.changeNodeValue(checkNode,
                    CONST.PROPERTY_ID,
                    CONST.businessRuleReplaceValues,
                    CONST.BUSINESSRULE_BASE,
                    true);
            // 暂时 ------------------------------------------------------------------------------------

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(checkNode);

            ProcessValidatorFactory factory = new ProcessValidatorFactory();
            ProcessValidator validator = factory.createDefaultProcessValidator();
            List<ValidationError> list = validator.validate(model);

            Map<String, String> map = new HashMap<>();
            if (list.size() > 0) {
                list.forEach(s -> {
                    map.put(s.getProblem(), ProblemsNativeMapping.mapping(s.getProblem()));
                });

                StringBuffer stringBuffer = new StringBuffer();
                map.forEach((k,v)-> {
                    if (null != v) {
                        stringBuffer.append(v.toString()).append("\n");
                    }
                });

                throw new ActivitiIllegalArgumentException(stringBuffer.toString());

            }

        } else {
            throw new ActivitiIllegalArgumentException("can not resolve json");
        }


        return joinPoint.proceed(objects);


    }


}
