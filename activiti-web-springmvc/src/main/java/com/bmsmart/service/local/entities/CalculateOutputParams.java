package com.bmsmart.service.local.entities;

import java.util.Map;

/**
 * 进行计算后的结果领域模型
 */
public class CalculateOutputParams {


    public static CalculateOutputParams create() {
        return new CalculateOutputParams();
    }

    private String status = "OK";

    private Long size = 0L;

    private String content;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
