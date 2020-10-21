package com.njoy.springcloud.controller;

import com.njoy.springcloud.entities.ApiResponse;
import com.njoy.springcloud.entities.Payment;
import com.njoy.springcloud.entities.SimulatedFault;
import com.njoy.springcloud.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class ConfigController {

    @Resource
    private ConfigService configService;

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

    /**
     * Get delay settings from configuration center.
     * @return delay : injected delay in milliseconds.
     */
    @GetMapping("/delay")
    public String getDelay() {
        log.info("delay: {}", delay);
        updateDelay(Long.valueOf(delay));
        return delay;
    }

    /**
     * Set delay with RESTFUL API.
     * @param delay : injected delay in milliseconds.
     * @return
     */
    @GetMapping("/config")
    public ApiResponse<SimulatedFault> updateDelay(@RequestParam("delay") Long delay){
        log.info("------------------> Fault injector updates delay as {} towards gateway.", delay);
        return configService.updateConfig(delay);
    }


}
