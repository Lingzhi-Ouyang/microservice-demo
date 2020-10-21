package com.njoy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ouyanglingzhi
 */
@SpringBootApplication
@EnableFeignClients
public class FaultInjectorApplication {
    public static void main(String[] args) {
        SpringApplication.run(FaultInjectorApplication.class, args);
    }
}
