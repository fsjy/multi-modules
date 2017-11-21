package cn.com.bmsmart.activiti.service.local.entities;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 进行计算传递的领域模型
 */
public class CalculateInputParams {

    // 系统内服务ID 可以映射到服务名称
    private String simpleName;

    // 流程图中服务的ID
    private String activitiID;

    // 当前处理所需要的前处理的id及其对应输出内容
    private LinkedHashMap<String, String> intputServiceIDandContentMapping;

    // 当前处理之前所有处理的id以及对应输出内容
    private LinkedHashMap<String, String> allServiceIDandConetentMapping;

    public static CalculateInputParams create() {

        return new CalculateInputParams();
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getActivitiID() {
        return activitiID;
    }

    public void setActivitiID(String activitiID) {
        this.activitiID = activitiID;
    }

    public Map<String, String> getIntputServiceIDandContentMapping() {
        return intputServiceIDandContentMapping;
    }

    public void setIntputServiceIDandContentMapping(LinkedHashMap<String, String> intputServiceIDandContentMapping) {
        this.intputServiceIDandContentMapping = intputServiceIDandContentMapping;
    }

    public Map<String, String> getAllServiceIDandConetentMapping() {
        return allServiceIDandConetentMapping;
    }

    public void setAllServiceIDandConetentMapping(LinkedHashMap<String, String> allServiceIDandConetentMapping) {
        this.allServiceIDandConetentMapping = allServiceIDandConetentMapping;
    }
}
