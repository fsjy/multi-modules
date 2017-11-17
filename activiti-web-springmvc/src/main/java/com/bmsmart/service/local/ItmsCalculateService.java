package com.bmsmart.service.local;

import com.bmsmart.service.local.entities.CalculateInputParams;

public interface ItmsCalculateService<T> {

    T execute(CalculateInputParams inputCalculateParams);

}
