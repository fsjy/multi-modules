package com.bmsmart.service.activiti.rule.impl;

import com.bmsmart.service.activiti.rule.AbstractRuleDelegateService;
import com.bmsmart.service.local.ItmsCalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.bmsmart.constant.CONST.RULE_TASKS.NER_CRF;


public class RuleDelegateNerCrfServiceImpl extends AbstractRuleDelegateService {

    private static final Logger log = LoggerFactory.getLogger(RuleDelegateNerCrfServiceImpl.class);

    @Autowired
    private ItmsCalculateService itmsCalculateService;

    @Override
    public String getSimpleName() {
        return NER_CRF.simpleName();
    }

    @Override
    public String getNativeName() {
        return NER_CRF.nativeName();
    }

    @Override
    protected ItmsCalculateService getCalculateService() {
        return this.itmsCalculateService;
    }
}
