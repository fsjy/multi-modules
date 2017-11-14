package com.bmsmart.service.activiti.rule;

import com.bmsmart.service.local.ItmsCalculateService;
import org.activiti.engine.impl.LocalBusinessRuleTaskDelegateImpl;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 *
 * @author Yanglu
 *
 */
public class AnnRuleDelegateService extends LocalBusinessRuleTaskDelegateImpl {

    private static final Logger log = LoggerFactory.getLogger(LocalBusinessRuleTaskDelegateImpl.class);

    @Autowired
    private ItmsCalculateService itmsCalculateServiceAnn;

    @Override
    public void execute(ActivityExecution execution) throws Exception {

        testLogging();

        itmsCalculateServiceAnn.execute();

        execution.setVariable("AnnRuleDelegateService", "AnnRuleDelegateServiceValue");

    }
}
