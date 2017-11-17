package com.bmsmart.service.activiti.factory.entities;

import java.util.LinkedHashMap;

public class ExecutedServicesEntities {

    //private LinkedHashMap<String, String> serviceMapping;

    private LinkedHashMap<String, ServiceReturnContent> executedServiceContentLinkedHashMap;

    public LinkedHashMap<String, ServiceReturnContent> getExecutedServiceContentLinkedHashMap() {
        return executedServiceContentLinkedHashMap;
    }

    public void setExecutedServiceContentLinkedHashMap(LinkedHashMap<String, ServiceReturnContent> executedServiceContentLinkedHashMap) {
        this.executedServiceContentLinkedHashMap = executedServiceContentLinkedHashMap;
    }

    private ExecutedServicesEntities() {

    }

    public static ExecutedServicesEntities create() {

        ExecutedServicesEntities serviceEntity = new ExecutedServicesEntities();
        LinkedHashMap<String, ServiceReturnContent> serviceMapping = new LinkedHashMap<>();
        serviceEntity.setExecutedServiceContentLinkedHashMap(serviceMapping);

        return serviceEntity;
    }

    public void addExecutedServiceContent(ServiceReturnContent executedServiceContent) {
        executedServiceContentLinkedHashMap.put(executedServiceContent.getActivitiID(), executedServiceContent);
    }


}
