package cn.com.bmsmart.activiti.service.activiti.factory.entities;

public class ExposeServiceEntity {

    private String content;

    private Long size;

    private String status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static ExposeServiceEntity create() {

        return new ExposeServiceEntity();
    }
}
