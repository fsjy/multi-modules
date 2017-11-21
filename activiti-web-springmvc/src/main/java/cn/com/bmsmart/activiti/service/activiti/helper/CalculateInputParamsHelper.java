package cn.com.bmsmart.activiti.service.activiti.helper;

public class CalculateInputParamsHelper {

    private static ThreadLocal threadLocal = new ThreadLocal();


    public static void main(String[] args) {
        threadLocal.get();
    }
}
