package com.bmsmart.service.local;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ThreadContext;


public class AbstractItmsCalculateService implements ItmsCalculateService {


    @Override
    public Object execute() {
        return null;
    }


    public void logging() {

        ThreadContext.getSubject().getSession();
        SecurityUtils.getSubject().getSession();

    }
}
