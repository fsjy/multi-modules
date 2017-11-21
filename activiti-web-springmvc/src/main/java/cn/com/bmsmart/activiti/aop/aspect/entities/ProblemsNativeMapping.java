package cn.com.bmsmart.activiti.aop.aspect.entities;

import org.activiti.validation.validator.Problems;

import java.util.HashMap;
import java.util.Map;

public class ProblemsNativeMapping {


    private final static Map<String, String> problemsMapping = new HashMap<>();


    static {

        problemsMapping.put(Problems.SEQ_FLOW_INVALID_SRC, "流程箭头起始位置有误，请确保箭头从开始、服务、网关处开始");
        problemsMapping.put(Problems.SEQ_FLOW_INVALID_TARGET, "流程箭头终止位置有误，请确保箭头结束于服务、网关、结束标志位");

    }

    public static String mapping(String eName) {
        return problemsMapping.get(eName);
    }




}
