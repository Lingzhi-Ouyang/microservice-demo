package com.njoy.springcloud.controller;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ouyanglingzhi
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/create")
    public ApiResponse<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, ApiResponse.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public ApiResponse queryById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, ApiResponse.class);
    }
}
