package com.bmsmart.service.local.impl;

import com.bmsmart.service.activiti.rule.entity.ServiceInputParams;
import com.bmsmart.service.local.ItmsCalculateService;
import com.bmsmart.service.local.entities.CalculateParams;
import org.codehaus.groovy.util.ListHashMap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ItmsCalculateServiceDefaultImpl implements ItmsCalculateService<Object> {


//    @Override
//    public Object execute(CalculateParams params) {
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
    public Object execute(ServiceInputParams params) {

        LinkedHashMap<String,String> hashMap = params.getInputParams();

        System.out.println("Service using params:");
        System.out.println("---------------------------");
        hashMap.forEach((k,v) -> {

            System.out.println(k.toString());
            System.out.println(v.toString());


        });

        System.out.println("---------------------------");
        return null;
    }
}
