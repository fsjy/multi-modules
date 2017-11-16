package com.bmsmart.service.local;

import com.bmsmart.service.activiti.rule.entity.ServiceInputParams;
import com.bmsmart.service.local.entities.CalculateParams;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ThreadContext;


public class AbstractItmsCalculateService implements ItmsCalculateService {




    public void logging() {

        ThreadContext.getSubject().getSession();
        SecurityUtils.getSubject().getSession();

    }

    @Override
    public Object execute(ServiceInputParams params) {
        return null;
    }
}
