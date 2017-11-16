package com.bmsmart.service.activiti.factory.entities;

import com.bmsmart.service.activiti.rule.entity.ActivitiInputParams;
import com.bmsmart.service.activiti.rule.entity.ServiceInputParams;

public class CurrentServiceEntity {

    // 服务英文唯一识别ID
    private String simpleName;

    // Activiti流程图当前服务输入变量
    private ActivitiInputParams activityInputVariables;

    private ServiceInputParams serviceInputParams;

    private String currentActivityID;

    public String getCurrentActivityID() {
        return currentActivityID;
    }

    public void setCurrentActivityID(String currentActivityID) {
        this.currentActivityID = currentActivityID;
    }

    public ServiceInputParams getServiceInputParams() {
        return serviceInputParams;
    }



    public void setServiceInputParams(ServiceInputParams serviceInputParams) {
        this.serviceInputParams = serviceInputParams;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public ActivitiInputParams getActivityInputVariables() {
        return activityInputVariables;
    }


    private CurrentServiceEntity(String simpleName, ActivitiInputParams activityInputVariables) {

        this.simpleName = simpleName;
        this.activityInputVariables = activityInputVariables;
    }

    public static CurrentServiceEntity create(String simpleName, ActivitiInputParams activityInputVariables) {
        return new CurrentServiceEntity(simpleName, activityInputVariables);
    }

}
