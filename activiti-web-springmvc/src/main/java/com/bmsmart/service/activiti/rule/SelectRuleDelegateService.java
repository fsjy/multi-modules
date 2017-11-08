package com.bmsmart.service.activiti.rule;

import org.activiti.engine.delegate.BusinessRuleTaskDelegate;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class SelectRuleDelegateService implements BusinessRuleTaskDelegate, ApplicationContextAware {


    private Expression expression;

    private static ApplicationContext ctx;


    @Override
    public void addRuleVariableInputIdExpression(Expression inputId) {
        this.expression = inputId;
    }

    @Override
    public void addRuleIdExpression(Expression inputId) {

    }

    @Override
    public void setExclude(boolean exclude) {

    }

    @Override
    public void setResultVariable(String resultVariableName) {
        String x = resultVariableName;

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @Override
    public void execute(ActivityExecution execution) throws Exception {
        System.out.println("Instance of "+ this.getClass().getSimpleName() +":");
        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println(this.toString());
        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println();

//        System.out.println("Input value is:");
//        System.out.println("++++++++++++++++++++++++++++++++");
//        System.out.println(this.expression.toString());
//        System.out.println("++++++++++++++++++++++++++++++++");
//        System.out.println();

        execution.setVariable("service1.select", 1);
        execution.setVariable("service2.select", 1);
        execution.setVariable("service3.select", 1);
        execution.setVariable("service4.select", 1);
        execution.setVariable("service5.select", 1);




//        BasicDataSource dataSource = (BasicDataSource) ctx.getBean("dataSource");
//
//        System.out.println("Spring bean dataSource is:");
//        System.out.println("++++++++++++++++++++++++++++++++");
//        System.out.println(dataSource.toString());
//        System.out.println("++++++++++++++++++++++++++++++++");
//        System.out.println();
    }
}
