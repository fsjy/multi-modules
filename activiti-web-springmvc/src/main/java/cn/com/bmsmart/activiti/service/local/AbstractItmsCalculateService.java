package cn.com.bmsmart.activiti.service.local;

import cn.com.bmsmart.activiti.service.local.entities.CalculateInputParams;
import cn.com.bmsmart.activiti.service.local.entities.CalculateOutputParams;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ThreadContext;

import java.util.Map;


public abstract class AbstractItmsCalculateService implements ItmsCalculateService {

    @Deprecated
    public void logging() {

        // 不要在service中使用session信息
        ThreadContext.getSubject().getSession();
        SecurityUtils.getSubject().getSession();

    }

    @Override
    public CalculateOutputParams execute(CalculateInputParams calculateInputParams) {


        Map<String, String> usingIdAndContentMapping = calculateInputParams.getIntputServiceIDandContentMapping();
        //Map<String, String> entireIdAndContentMapping = calculateInputParams.getIntputServiceIDandContentMapping();


        // 打印所有已处理的服务输出内容
//        System.out.println("All Service params:");
//        System.out.println("---------------------------");
//        entireIdAndContentMapping.forEach((k, v) -> {
//            System.out.println(k.toString());
//            System.out.println(v.toString());
//
//        });
//        System.out.println("");

        // 打印所需服务输出内容
        System.out.println("Using Service params:");
        System.out.println("---------------------------");
        usingIdAndContentMapping.forEach((k, v) -> {
            System.out.println(k.toString());
            System.out.println(v.toString());
        });
        System.out.println("");


        // 处理输出内容到output
        CalculateOutputParams outputParams = CalculateOutputParams.create();

        outputParams.setContent(getResult());
        outputParams.setStatus(getStatus());
        outputParams.setSize(getSize());


        System.out.println("Execute output params:");
        System.out.println("content:" + getResult());
        System.out.println("status :" + getStatus());
        System.out.println("size   :" + getSize());

        System.out.println("---------------------------");
        return outputParams;
    }


    abstract public String getResult();

    abstract public String getStatus();

    abstract public Long getSize();

}
