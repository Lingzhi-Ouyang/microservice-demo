package com.njoy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ouyanglingzhi
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication4 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication4.class, args);
    }
}
