package com.bmsmart.service.activiti.rule.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceInputParams {

    LinkedHashMap<String, String> inputParams = new LinkedHashMap<>();

    public LinkedHashMap<String, String> getInputParams() {
        return inputParams;
    }

    public void setInputParams(LinkedHashMap<String, String> inputParams) {
        this.inputParams = inputParams;
    }

    public void addParams(String k, String v) {
        this.inputParams.put(k, v);
    }
}
