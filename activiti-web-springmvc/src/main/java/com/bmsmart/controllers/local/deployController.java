package com.bmsmart.controllers.local;

import com.bmsmart.constant.CONST;
import com.bmsmart.service.activiti.factory.entities.ExposeServiceEntity;
import com.bmsmart.tookit.JsonModifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Controller
public class deployController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 测试使用在实现Delegate的class中使用variable来传递参数的 TaskService运行入口
     * 并且加入网关的条件选择路径，测试传递条件值${input}是否正确
     * 测试使用Parallel网关
     * <p>
     * ignore "POST" and "GET"
     *
     * @return
     */
    @RequestMapping(value = "local/deploy", method = RequestMethod.POST)
    public ResponseEntity executeRuleService(String modelId, String title, String content) throws IOException {

        byte[] bytes = repositoryService.getModelEditorSource(modelId);

        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(new String(bytes, "UTF-8"));

        // 发布之前替换所有自定义的ruleTaskService的ID
        JsonModifier.changeNodeValue(modelNode,
                CONST.PROPERTY_ID,
                CONST.businessRuleReplaceValues,
                CONST.BUSINESSRULE_BASE,
                true);

        deployModelerModel(modelNode);

        Map<String, Object> variablesMaps = new HashMap<>();

        variablesMaps.put(CONST.ACT_TITLE, "这是一个悲惨的故事");
        variablesMaps.put(CONST.ACT_CONTENT, "每天上班8个小时难道不够悲惨么？");

        // 使用process_id进行发布
        // 保存动作时已确保唯一process_id = authorName + "_" + ACT_RE_MODEL.ID_
        String key = modelNode.get("properties").get(CONST.PROPERTY_PROCESS_ID).asText();

        ProcessInstance processInstanceBykey = runtimeService.startProcessInstanceByKey(key, variablesMaps);

        Map<String, Object> vars = processInstanceBykey.getProcessVariables();


        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");


        System.out.println("=============================================================================================================================================================================================");

        System.out.println("End Instance variables:");
        System.out.println("-------------------------------------------------------");
        int count = 1;
        vars.forEach(new BiConsumer<String, Object>() {

            /**
             * Performs this operation on the given arguments.
             *
             * @param k the first input argument
             * @param v the second input argument
             */
            @Override
            public void accept(String k, Object v) {

                System.out.println("key: " + k.toString());
                if (v instanceof ExposeServiceEntity) {
                    System.out.println("  | 内容:" + ((ExposeServiceEntity) v).getContent());
                    System.out.println("  | 状态:" + ((ExposeServiceEntity) v).getStatus());
                    System.out.println("  | 大小:" + ((ExposeServiceEntity) v).getSize());
                }

            }

        });

        System.out.println("-------------------------------------------------------");

        return new ResponseEntity<String>("a", HttpStatus.OK);

        //return "modellist";

    }

    protected String deployModelerModel(final ObjectNode modelNode) {
        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        String processName = "temp_" + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name("temp_name")
                .addString(processName, new String(bpmnBytes))
                .deploy();

        //ExplorerApp.get().getViewManager().showDeploymentPage(deployment.getId());


        return deployment.getId();
    }


}
