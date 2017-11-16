package com.bmsmart.service.local;

import com.bmsmart.service.activiti.rule.entity.ServiceInputParams;

public interface ItmsCalculateService<T1> {

    T1 execute(ServiceInputParams params);

}
