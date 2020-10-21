package com.njoy.springcloud.config;

import com.njoy.springcloud.entities.SimulatedFault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ouyanglingzhi
 */
@Configuration
public class FaultInjectionConfig {

    @Bean
    public SimulatedFault simulatedFault(){
        return new SimulatedFault();
    }
}
