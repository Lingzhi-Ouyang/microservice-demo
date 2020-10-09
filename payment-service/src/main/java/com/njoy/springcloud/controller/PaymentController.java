package com.njoy.springcloud.controller;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.Payment;
import com.njoy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ouyanglingzhi
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public ApiResponse<Payment> create(@RequestBody Payment payment){
        log.info("create payment : " + payment);
        int result = paymentService.create(payment);
        log.info("create payment result : " + result);
        if(result > 0){
            return new ApiResponse(200, "create success", result);
        }
        return new ApiResponse(444, "create failure");
    }

    @GetMapping(value = "/payment/get/{id}")
    public ApiResponse<Payment> queryById(@PathVariable("id") Long id){
        log.info("query payment : " + id);
        Payment result = paymentService.queryById(id);
        log.info("query payment result : " + result);
        if(result != null){
            return new ApiResponse(200, "query success", result);
        }
        return new ApiResponse(444, "query failure");
    }
}
