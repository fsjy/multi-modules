package org.activiti.engine.impl;

import org.activiti.engine.delegate.BusinessRuleTaskDelegate;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.bpmn.deployer.BpmnDeployer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.function.Consumer;


/**
 * 优雅解决BusinessRuleDelegate的class中使用xml配置参数的问题
 *
 * @author Yanglu
 */
public abstract class LocalBusinessRuleTaskDelegateImpl implements BusinessRuleTaskDelegate {

    private HashSet<Expression> ruleVariableInputIdExpressions = new HashSet<>();
    private HashSet<Expression> ruleIdExpressions = new HashSet<>();
    private boolean exclude;
    private String resultVariable;
    private static final Logger log = LoggerFactory.getLogger(LocalBusinessRuleTaskDelegateImpl.class);

    public void setRuleVariableInputIdExpressions(HashSet<Expression>  expressions) {
        this.ruleVariableInputIdExpressions = expressions;
    }

    public void setRuleIdExpressions(HashSet<Expression> ruleIdExpressions) {
        this.ruleIdExpressions = ruleIdExpressions;
    }

    protected HashSet<Expression> getRuleVariableInputIdExpressions() {
        return this.ruleVariableInputIdExpressions;
    }

    protected HashSet<Expression> getRuleIdExpressions() {
        return this.ruleIdExpressions;
    }

    @Override
    public void addRuleVariableInputIdExpression(Expression inputId) {
        this.ruleVariableInputIdExpressions.add(inputId);
    }

    @Override
    public void addRuleIdExpression(Expression inputId) {
        this.ruleIdExpressions.add(inputId);
    }

    @Override
    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }

    @Override
    public void setResultVariable(String resultVariableName) {
        this.resultVariable = resultVariableName;
    }

    public String getResultVariable() {
        return this.resultVariable;
    }

    public boolean getExclude() {
        return this.exclude;
    }

    protected void testLogging() {
        log.warn("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.warn("【"+this.getClass().getSimpleName() + "】:" + this.toString());
        //System.out.println("Instance of " + this.getClass().getSimpleName() + ":");
        //System.out.println("++++++++++++++++++++++++++++++++");
        //System.out.println(this.toString());
        //System.out.println("++++++++++++++++++++++++++++++++");
        //System.out.println();
        log.warn("【Input value is】: ");
        //System.out.println(this.expression.toString());
        getRuleVariableInputIdExpressions().forEach(new Consumer<Expression>() {
            @Override
            public void accept(Expression expression) {
                log.warn(expression.getExpressionText());
            }
        });
        log.warn("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
    }
}
