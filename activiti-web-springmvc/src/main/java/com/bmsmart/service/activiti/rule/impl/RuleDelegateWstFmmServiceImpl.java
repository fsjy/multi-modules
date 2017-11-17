package com.bmsmart.service.activiti.rule.impl;

import com.bmsmart.constant.CONST;
import com.bmsmart.service.activiti.rule.AbstractRuleDelegateService;
import com.bmsmart.service.local.ItmsCalculateService;
import org.activiti.engine.impl.LocalBusinessRuleTaskDelegateImpl;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Yanglu
 */
public class RuleDelegateWstFmmServiceImpl extends AbstractRuleDelegateService {

    private static final Logger log = LoggerFactory.getLogger(LocalBusinessRuleTaskDelegateImpl.class);

    @Autowired
    private ItmsCalculateService itmsCalculateServiceWstFmm;


    @Override
    public String getSimpleName() {
        return CONST.RULE_TASKS.WST_FMM.simpleName();
    }

    @Override
    public String getNativeName() {
        return CONST.RULE_TASKS.WST_FMM.nativeName();
    }

    @Override
    protected ItmsCalculateService getCalculateService() {
        return itmsCalculateServiceWstFmm;
    }
}
