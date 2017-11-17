package com.bmsmart.service.activiti.factory.entities;

import com.bmsmart.service.activiti.rule.entity.ActivitiInputParams;

public class CurrentServiceEntity {

    // 服务英文唯一识别ID
    private String simpleName;

    // Activiti流程图当前服务输入变量
    private ActivitiInputParams activityInputVariables;


    private RuleDelegateInputParams ruleDelegateInputParams;

    private RuleDelegateOutputParams ruleDelegateOutputParams;

    public RuleDelegateOutputParams getRuleDelegateOutputParams() {
        return ruleDelegateOutputParams;
    }

    public void setRuleDelegateOutputParams(RuleDelegateOutputParams ruleDelegateOutputParams) {
        this.ruleDelegateOutputParams = ruleDelegateOutputParams;
    }

    public RuleDelegateInputParams getRuleDelegateInputParams() {
        return ruleDelegateInputParams;
    }

    public void setRuleDelegateInputParams(RuleDelegateInputParams ruleDelegateInputParams) {
        this.ruleDelegateInputParams = ruleDelegateInputParams;
    }

    private String currentActivityID;

    public String getCurrentActivityID() {
        return currentActivityID;
    }

    public void setCurrentActivityID(String currentActivityID) {
        this.currentActivityID = currentActivityID;
    }


    public void addOutput(String k, String v) {
        this.ruleDelegateInputParams.addParams(k, v);
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
