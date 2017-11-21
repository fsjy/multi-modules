package cn.com.bmsmart.activiti.service.local;

import cn.com.bmsmart.activiti.service.local.entities.CalculateInputParams;

public interface ItmsCalculateService<T> {

    T execute(CalculateInputParams inputCalculateParams);

}
