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

    @GetMapping("/logInfo")
    public String getLogInfo() {
        return logInfo;
    }

    @GetMapping(value = "/order/get/{id}")
    public ApiResponse<Payment> queryById(@PathVariable("id") Long id) {
        log.info("Query by id: {}, {}", id,Long.class.getName());
        Map<String,Object> map = new HashMap<>();
//        map.put("alive", "coding every day");
//        map.put("out", System.out);
//        String expression = "out.print(alive)";
//        CodeEngine.convert(expression, map);
        map.put("log", log);
        map.put("id", id);
        String exp = "log.info(\"" + logInfo + "\"," + var + ")";
        log.info(exp);
        CodeEngine.convert(exp, map);
        return orderService.queryById(id);
    }

    @GetMapping(value = "/order/get")
    public ApiResponse<Payment> queryById1(@RequestParam Long id){
        log.info("query by id: {}", id);
        return orderService.queryById(id);
    }
}
