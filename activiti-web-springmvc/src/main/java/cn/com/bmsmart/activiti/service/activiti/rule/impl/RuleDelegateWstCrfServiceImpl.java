package cn.com.bmsmart.activiti.service.activiti.rule.impl;

import cn.com.bmsmart.activiti.constant.CONST;
import cn.com.bmsmart.activiti.service.activiti.rule.AbstractRuleDelegateService;
import cn.com.bmsmart.activiti.service.local.ItmsCalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class RuleDelegateWstCrfServiceImpl extends AbstractRuleDelegateService {

    private static final Logger log = LoggerFactory.getLogger(RuleDelegateWstCrfServiceImpl.class);

    @Autowired
    private ItmsCalculateService itmsCalculateServiceWstCrf;

    @Override
    public String getSimpleName() {
        return CONST.RULE_TASKS.WST_CRF.simpleName();
    }

    @Override
    public String getNativeName() {
        return CONST.RULE_TASKS.WST_CRF.nativeName();
    }

    @Override
    protected ItmsCalculateService getCalculateService() {
        return itmsCalculateServiceWstCrf;
    }
}
