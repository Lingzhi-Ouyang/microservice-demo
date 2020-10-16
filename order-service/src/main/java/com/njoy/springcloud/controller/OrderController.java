package com.njoy.springcloud.controller;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ouyanglingzhi
 */
@RestController
@Slf4j
@RefreshScope
public class OrderController {

    public static final String PAYMENT_URL = "http://PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Value("${log.info}")
    private String logInfo;

    @Value("${log.variable}")
    private String var;

    @GetMapping("/logInfo")
    public String getLogInfo() {
        return logInfo;
    }

    @GetMapping(value = "/order/create")
    public ApiResponse<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, ApiResponse.class);
    }

    @GetMapping(value = "/order/get/{id}")
    public ApiResponse<Payment> queryById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, ApiResponse.class);
    }

    @GetMapping(value = "/order/get")
    public ApiResponse<Payment> queryById1(@RequestParam Long id){
        log.info("query by id: {}", id);
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, ApiResponse.class);
    }

    @GetMapping(value = "/order/postForEntity/create")
    public ApiResponse<Payment> create2(Payment payment){
        ResponseEntity<ApiResponse> responseEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, ApiResponse.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        } else {
            return new ApiResponse(444, "query failure");
        }
    }


}
