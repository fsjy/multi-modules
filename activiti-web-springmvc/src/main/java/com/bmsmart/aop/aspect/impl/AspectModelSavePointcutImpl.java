package com.bmsmart.aop.aspect.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.*;

import java.io.IOException;


@Aspect
public class AspectModelSavePointcutImpl {

    private static final Logger log = LoggerFactory.getLogger(AspectModelSavePointcutImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Around("execution(* org.activiti.rest.editor.model.ModelSaveRestResource.saveModel(..))")
    public Object setParamsBefore(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] objects = joinPoint.getArgs();


        if (objects != null && objects.length > 1 && objects[1] instanceof MultiValueMap) {

            MultiValueMap<String, String> multiValueMap = (MultiValueMap) objects[1];

            String value = multiValueMap.getFirst("json_xml").toString();

            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(value);

            //JsonNode node =  modelJson.get("process_id");

            change(modelJson, "process_id", "yangluishero");


            ((MultiValueMap) objects[1]).set("json_xml", modelJson.toString());


        }


        System.out.printf("pointcut");

        return joinPoint.proceed(objects);


    }


    private void change(JsonNode parent, String fieldName, String newValue) {
        if (parent.has(fieldName)) {
            ((ObjectNode) parent).put(fieldName, newValue);
        }

        // Now, recursively invoke this method on all properties
        for (JsonNode child : parent) {
            change(child, fieldName, newValue);
        }
    }


}
