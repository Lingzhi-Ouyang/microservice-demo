package com.njoy.springcloud.controller;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.Payment;
import com.njoy.springcloud.service.OrderService;
import com.njoy.springcloud.utils.CodeEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ouyanglingzhi
 */
@RestController
@Slf4j
@RefreshScope
public class OrderController {

    @Resource
    private OrderService orderService;

    @Value("${log.info}")
    private String logInfo;

    @Value("${log.variable}")
    private String var;

    @Value("${delay:0}")
    private String delay;

    @GetMapping("/logInfo")
    public String getLogInfo() {
        return logInfo;
    }

    @GetMapping("/delay")
    public String getDelay() {
        return delay;
    }

    @GetMapping(value = "/order/get/{id}")
    public ApiResponse<Payment> queryById(@PathVariable("id") Long id) {
        log.info("query by id = {}, delay: {}", id, delay);
        return orderService.queryById(id);
    }

    @GetMapping(value = "/order/get")
    public ApiResponse<Payment> queryById1(@RequestParam Long id){
        log.info("Query by id: {}, delay: {}", id, delay);
        return orderService.queryById(id);
    }
}
