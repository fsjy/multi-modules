package com.bmsmart.service.activiti.rule;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.LocalBusinessRuleTaskDelegateImpl;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class SelectRuleDelegateService extends LocalBusinessRuleTaskDelegateImpl {

    @Autowired
    private RepositoryService repositoryService;

    private static final Logger log = LoggerFactory.getLogger(LocalBusinessRuleTaskDelegateImpl.class);
    @Override
    public void execute(ActivityExecution execution) throws Exception {

        testLogging();

        log.warn("Autowired Class of :  " + repositoryService.getClass().toString());
    }
}
