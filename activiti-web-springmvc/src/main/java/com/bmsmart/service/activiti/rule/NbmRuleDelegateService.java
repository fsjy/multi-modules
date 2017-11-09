package com.bmsmart.service.activiti.rule;

import org.activiti.engine.impl.LocalBusinessRuleTaskDelegateImpl;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NbmRuleDelegateService extends LocalBusinessRuleTaskDelegateImpl {

    private static final Logger log = LoggerFactory.getLogger(LocalBusinessRuleTaskDelegateImpl.class);

    @Override
    public void execute(ActivityExecution execution) throws Exception {

        testLogging();
    }
}
