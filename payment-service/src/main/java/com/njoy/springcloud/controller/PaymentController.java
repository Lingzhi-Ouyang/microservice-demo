package com.njoy.springcloud.controller;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.Payment;
import com.njoy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ouyanglingzhi
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


    @PostMapping(value = "/payment/create")
    public ApiResponse<Payment> create(@RequestBody Payment payment){
        log.info("create payment : " + payment);
        int result = paymentService.create(payment);
        log.info("create payment result : " + result);
        if(result > 0){
            return new ApiResponse(200, "create success at port " + serverPort, result);
        }
        return new ApiResponse(444, "create failure at port "+ serverPort);
    }

    @GetMapping(value = "/payment/get/{id}")
    public ApiResponse<Payment> queryById(@PathVariable("id") Long id){
        log.info("query payment : " + id);
        Payment result = paymentService.queryById(id);
        log.info("query payment result : " + result);
        if(result != null){
            return new ApiResponse(200, "query success at port " + serverPort, result);
        }
        return new ApiResponse(444, "query failure at port " + serverPort);
    }

    @GetMapping(value = "/payment/discovery")
    public Object serviceDiscovery(){
        List<String> services = discoveryClient.getServices();
        for(String service : services){
            log.info("---------Service : {}--------", service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVICE");
        for(ServiceInstance serviceInstance: instances){
            log.info("Service id: {}, instance id: {}, host: {}, port:{}, uri: {}", serviceInstance.getServiceId(), serviceInstance.getInstanceId(),
                    serviceInstance.getHost(), serviceInstance.getPort(), serviceInstance.getUri());
        }
        return this.discoveryClient;



    }

}
