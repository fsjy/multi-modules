package org.activiti.engine;

import org.activiti.engine.delegate.Expression;

import java.util.HashSet;
import java.util.List;

public interface LocalBusinessRuleTaskDelegate {

    void setRuleVariableInputIdExpressions(List<Expression> expressions);

    void setRuleIdExpressions(List<Expression> ruleIdExpressions);

}
