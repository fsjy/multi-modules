package com.bmsmart.service.activiti.rule.impl;

import com.bmsmart.constant.CONST;
import com.bmsmart.service.activiti.rule.AbstractRuleDelegateService;
import com.bmsmart.service.local.ItmsCalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class RuleDelegatePtrCrfServiceImpl extends AbstractRuleDelegateService {

    private static final Logger log = LoggerFactory.getLogger(RuleDelegatePtrCrfServiceImpl.class);

    @Autowired
    private ItmsCalculateService itmsCalculateService;

    @Override
    public String getSimpleName() {
        return CONST.RULE_TASKS.PTR_CRF.simpleName();
    }

    @Override
    public String getNativeName() {
        return CONST.RULE_TASKS.PTR_CRF.nativeName();
    }

    @Override
    protected ItmsCalculateService getCalculateService() {
        return itmsCalculateService;
    }
}
