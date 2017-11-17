package com.bmsmart.service.activiti.rule.impl;

import com.bmsmart.constant.CONST;
import com.bmsmart.service.activiti.rule.AbstractRuleDelegateService;
import com.bmsmart.service.local.ItmsCalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class RuleDelegateTccNavieBServiceImpl extends AbstractRuleDelegateService {

    private static final Logger log = LoggerFactory.getLogger(RuleDelegateTccNavieBServiceImpl.class);

    @Autowired
    private ItmsCalculateService itmsCalculateServiceTccNavieB;

    @Override
    public String getSimpleName() {
        return CONST.RULE_TASKS.TCC_NAVIEB.simpleName();
    }

    @Override
    public String getNativeName() {
        return CONST.RULE_TASKS.TCC_NAVIEB.nativeName();
    }

    @Override
    protected ItmsCalculateService getCalculateService() {
        return itmsCalculateServiceTccNavieB;

    }
}