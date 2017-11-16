package com.bmsmart.service.activiti.rule.impl;

import com.bmsmart.constant.CONST;
import com.bmsmart.service.activiti.rule.AbstractRuleDelegateService;
import com.bmsmart.service.local.ItmsCalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class RuleDelegateNerMmsegServiceImpl extends AbstractRuleDelegateService {

    private static final Logger log = LoggerFactory.getLogger(RuleDelegateNerMmsegServiceImpl.class);

    @Autowired
    private ItmsCalculateService itmsCalculateService;

    @Override
    public String getSimpleName() {
        return CONST.RULE_TASKS.NER_MMSEG.simpleName();
    }

    @Override
    public String getNativeName() {
        return CONST.RULE_TASKS.NER_MMSEG.nativeName();
    }

    @Override
    protected ItmsCalculateService getCalculateService() {
        return itmsCalculateService;
    }
}
