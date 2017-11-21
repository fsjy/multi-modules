package cn.com.bmsmart.activiti.service.activiti.rule.impl;

import cn.com.bmsmart.activiti.service.local.ItmsCalculateService;
import cn.com.bmsmart.activiti.constant.CONST;
import cn.com.bmsmart.activiti.service.activiti.rule.AbstractRuleDelegateService;
import org.activiti.engine.impl.LocalBusinessRuleTaskDelegateImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class RuleDelegateTccMmsegServiceImpl extends AbstractRuleDelegateService {

    private static final Logger log = LoggerFactory.getLogger(LocalBusinessRuleTaskDelegateImpl.class);

    @Autowired
    private ItmsCalculateService itmsCalculateServiceTccMmseg;

    @Override
    public String getSimpleName() {
        return CONST.RULE_TASKS.TCC_MMSEG.simpleName();
    }

    @Override
    public String getNativeName() {
        return CONST.RULE_TASKS.TCC_MMSEG.nativeName();
    }

    @Override
    protected ItmsCalculateService getCalculateService() {
        return itmsCalculateServiceTccMmseg;
    }
}
