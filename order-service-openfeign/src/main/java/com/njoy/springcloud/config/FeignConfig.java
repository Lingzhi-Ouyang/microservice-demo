package com.njoy.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ouyanglingzhi
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level FeignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
