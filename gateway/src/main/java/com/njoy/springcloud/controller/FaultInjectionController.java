package com.njoy.springcloud.controller;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.SimulatedFault;
import com.njoy.springcloud.service.FaultInjectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ouyanglingzhi
 */
@RestController
@Slf4j
public class FaultInjectionController {

    @Resource
    private FaultInjectionService faultInjectionService;

    @GetMapping("/config")
    public ApiResponse<SimulatedFault> updateConfig(@RequestParam("delay") Long delay){
        log.info("------------------> Set delay as {}", delay);
        return faultInjectionService.injectDelay(delay);
    }

}
