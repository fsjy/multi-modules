package cn.com.bmsmart.activiti.aop.aspect.impl;

import cn.com.bmsmart.activiti.aop.aspect.AspectCalculatePointcut;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.function.Consumer;


@Aspect
public class AspectCalculateParamsPointcutImpl implements AspectCalculatePointcut {

    private static final Logger log = LoggerFactory.getLogger(AspectCalculateParamsPointcutImpl.class);

    @Before("execution(* com.bmsmart.service.activiti.rule.RuleDelegate*.execute(..))")
    public void setParamsBefore(JoinPoint joinPoint) {

        System.out.println("++++++++++Before++++++++++++");

        Object[] args =  joinPoint.getArgs();

        Arrays.stream(args).forEach(new Consumer<Object>() {

            /**
             * Performs this operation on the given argument.
             *
             * @param o the input argument
             */
            @Override
            public void accept(Object o) {

                if (o instanceof ActivityExecution) {

                    log.warn(o.toString());
                }


            }

        });

        System.out.println("Before AOP AspectJ jet");
        System.out.println("++++++++++Before++++++++++++");

    }

    @After("execution(* com.bmsmart.service.activiti.rule.RuleDelegate*.execute(..))")
    public void setParamsAfter(JoinPoint joinPoint) {


        System.out.println("++++++++++After++++++++++++");
        System.out.println("After AOP AspectJ jet");
        System.out.println("++++++++++After++++++++++++");

    }




}
