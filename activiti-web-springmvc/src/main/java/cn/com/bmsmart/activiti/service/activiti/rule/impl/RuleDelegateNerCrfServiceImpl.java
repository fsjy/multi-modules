package cn.com.bmsmart.activiti.service.activiti.rule.impl;

import cn.com.bmsmart.activiti.service.local.ItmsCalculateService;
import cn.com.bmsmart.activiti.service.activiti.rule.AbstractRuleDelegateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.com.bmsmart.activiti.constant.CONST.RULE_TASKS.NER_CRF;


public class RuleDelegateNerCrfServiceImpl extends AbstractRuleDelegateService {

    private static final Logger log = LoggerFactory.getLogger(RuleDelegateNerCrfServiceImpl.class);

    @Autowired
    private ItmsCalculateService itmsCalculateServiceNerCrf;

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
        return this.itmsCalculateServiceNerCrf;
    }
}
