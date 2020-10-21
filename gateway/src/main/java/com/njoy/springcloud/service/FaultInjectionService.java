package com.njoy.springcloud.service;

import com.njoy.springcloud.entities.ApiResponse;

/**
 * @author ouyanglingzhi
 */
public interface FaultInjectionService {
    ApiResponse injectDelay(Long delay);
}
