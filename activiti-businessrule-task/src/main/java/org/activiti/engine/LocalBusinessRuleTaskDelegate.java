package org.activiti.engine;

import org.activiti.engine.delegate.Expression;

import java.util.HashSet;

public interface LocalBusinessRuleTaskDelegate {

    void setRuleVariableInputIdExpressions(HashSet<Expression> expressions);

    void setRuleIdExpressions(HashSet<Expression> ruleIdExpressions);

}
