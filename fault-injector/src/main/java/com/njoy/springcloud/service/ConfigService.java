package com.njoy.springcloud.service;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.Payment;
import com.njoy.springcloud.entities.SimulatedFault;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ouyanglingzhi
 */
@Component
@FeignClient("GATEWAY")
public interface ConfigService {

    @GetMapping(value = "/config")
    ApiResponse<SimulatedFault> updateConfig(@RequestParam("delay") Long delay);
}
