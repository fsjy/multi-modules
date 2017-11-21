package cn.com.bmsmart.activiti.service.local.impl;

import cn.com.bmsmart.activiti.service.local.AbstractItmsCalculateService;

public class ItmsCalculateServiceDefaultImpl extends AbstractItmsCalculateService {


//    @Override
//    public Object execute(CalculateInputParams params) {
//
//
//        System.out.printf("==========Calculate Start==========");
//        Map<String, String> intputs = params.getInputParams();
//
//        System.out.println("All Params Values:");
//        intputs.forEach(new BiConsumer<String, String>() {
//            @Override
//            public void accept(String s, String s2) {
//                System.out.printf(s.toString());
//            }
//        });
//        System.out.println("");
//
//
//        Map<String, String> needs = params.getNeedVariables();
//        System.out.println("Using Params Values:");
//        needs.forEach(new BiConsumer<String, String>() {
//            @Override
//            public void accept(String s, String s2) {
//                System.out.printf(s.toString());
//            }
//        });
//        System.out.println("");
//
//        System.out.println("Set Output Params Values:");
//        Map<String, String> outputs = new ListHashMap<>();
//
////        outputs.put(serviceId + "_size", "1");
////        outputs.put(serviceId + "_status", "ok");
////        outputs.put(serviceId + "_output", "this one is output");
//
//        params.setOutputParams(outputs);
//
//        System.out.printf("==========Calculate End============");
//        return null;
//    }


    @Override
    public String getResult() {
        return "default";
    }

    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public Long getSize() {
        return null;
    }
}
