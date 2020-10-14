package com.njoy.springcloud.service;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ouyanglingzhi
 */
@Component
@FeignClient("PAYMENT-SERVICE")
public interface OrderService {

    @GetMapping(value = "/payment/get/{id}")
    ApiResponse<Payment> queryById(@PathVariable("id") Long id);
}
