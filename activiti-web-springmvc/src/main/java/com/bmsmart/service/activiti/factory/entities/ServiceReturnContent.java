package com.bmsmart.service.activiti.factory.entities;

import com.bmsmart.constant.CONST;

public class ServiceReturnContent {

    // 流程图中的服务ID
    private String activitiID;

    // 当前服务ID 可映射服务名称
    private String simpleName;

    // 输出的内容结果
    private String content;

    // 处理状态
    private String status;

    // size
    private Long size;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getActivitiID() {
        return activitiID;
    }

    public void setActivitiID(String activitiID) {
        this.activitiID = activitiID;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static ServiceReturnContent createTitle(String title) {

        ServiceReturnContent executedServiceContent = new ServiceReturnContent();
        executedServiceContent.activitiID = CONST.ACT_TITLE;
        executedServiceContent.simpleName = CONST.ACT_TITLE;
        executedServiceContent.status = CONST.OK;
        executedServiceContent.setContent(title);
        executedServiceContent.setSize(Long.valueOf(title.length()));

        return executedServiceContent;

    }

    public static ServiceReturnContent createContent(String content) {

        ServiceReturnContent executedServiceContent = new ServiceReturnContent();
        executedServiceContent.activitiID = CONST.ACT_CONTENT;
        executedServiceContent.simpleName = CONST.ACT_CONTENT;
        executedServiceContent.status = CONST.OK;
        executedServiceContent.setContent(content);
        executedServiceContent.setSize(Long.valueOf(content.length()));

        return executedServiceContent;

    }

    public static ServiceReturnContent createDefaultService(String activitiID, String simpleName, String status, Long size, String content) {

        ServiceReturnContent executedServiceContent = new ServiceReturnContent();
        executedServiceContent.activitiID = activitiID;
        executedServiceContent.simpleName = simpleName;
        executedServiceContent.status = status;
        executedServiceContent.setContent(content);
        executedServiceContent.setSize(size);

        return executedServiceContent;

    }

}
