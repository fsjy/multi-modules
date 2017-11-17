package com.bmsmart.service.activiti.factory.entities;

import java.util.LinkedHashMap;

public class RuleDelegateInputParams {

    LinkedHashMap<String, String> intputServiceIDandContentMapping = new LinkedHashMap<>();


    public LinkedHashMap<String, String> getIntputServiceIDandContentMapping() {
        return intputServiceIDandContentMapping;
    }


    public void addParams(String k, String v) {
        this.intputServiceIDandContentMapping.put(k, v);
    }

    public void setIntputServiceIDandContentMapping(LinkedHashMap<String, String> inputParams) {
        this.intputServiceIDandContentMapping = inputParams;
    }


}
