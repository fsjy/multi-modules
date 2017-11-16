package com.bmsmart.service.local.entities;

import java.util.List;
import java.util.Map;

/**
 * 进行计算传递的领域模型
 */
public class CalculateParams {

    private String serviceId;

    private Map<String, String> inputParams;

    private Map<String, String> needVariables;

    private Map<String, String> outputParams;


    public static CalculateParams create () {

        return new CalculateParams();
    }

    public Map<String, String> getNeedVariables() {
        return needVariables;
    }

    public void setNeedVariables(Map<String, String> needVariables) {
        this.needVariables = needVariables;
    }

    public Map<String, String> getOutputParams() {
        return outputParams;
    }

    public void setOutputParams(Map<String, String> outputParams) {
        this.outputParams = outputParams;
    }

    public Map<String, String> getInputParams() {

        return inputParams;
    }

    public void setInputParams(Map<String, String> inputParams) {
        this.inputParams = inputParams;
    }
}
