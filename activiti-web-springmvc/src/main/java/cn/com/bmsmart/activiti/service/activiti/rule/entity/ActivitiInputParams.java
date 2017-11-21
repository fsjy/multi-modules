package cn.com.bmsmart.activiti.service.activiti.rule.entity;

import java.util.List;

public class ActivitiInputParams {

    private boolean isUsing = false;

    private List<String> inputs;

    public boolean isUsing() {
        return isUsing;
    }

    public void setUsing(boolean usingInput) {
        isUsing = usingInput;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }


}
