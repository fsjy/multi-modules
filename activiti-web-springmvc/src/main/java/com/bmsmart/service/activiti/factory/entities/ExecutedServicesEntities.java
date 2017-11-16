package com.bmsmart.service.activiti.factory.entities;

import org.activiti.engine.impl.variable.CustomObjectType;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExecutedServicesEntities {

    private LinkedHashMap<String, String> serviceMapping;

    private ExecutedServicesEntities() {

    }

    public static ExecutedServicesEntities create() {

        ExecutedServicesEntities serviceEntity = new ExecutedServicesEntities();
        LinkedHashMap<String, String> serviceMapping = new LinkedHashMap<>();
        serviceEntity.setServiceMapping(serviceMapping);

        return serviceEntity;
    }

    public Map<String, String> getServiceMapping() {
        return serviceMapping;
    }

    private void setServiceMapping(LinkedHashMap<String, String> serviceMapping) {
        this.serviceMapping = serviceMapping;
    }

    public ExecutedServicesEntities addService(String serviceId, String simpleName) {
        this.serviceMapping.put(serviceId, simpleName);
        return this;
    }

}
