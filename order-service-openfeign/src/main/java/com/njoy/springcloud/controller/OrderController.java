package com.njoy.springcloud.controller;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.Payment;
import com.njoy.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ouyanglingzhi
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "/order/get/{id}")
    public ApiResponse<Payment> queryById(@PathVariable("id") Long id){
        log.info("query by id: {}", id);
        return orderService.queryById(id);
    }

    @GetMapping(value = "/order/get")
    public ApiResponse<Payment> queryById1(@RequestParam Long id){
        log.info("query by id: {}", id);
        return orderService.queryById(id);
    }

}
