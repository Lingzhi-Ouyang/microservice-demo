package com.njoy.springcloud;

import com.njoy.lb.RandomLbRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author ouyanglingzhi
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "PAYMENT-SERVICE", configuration = RandomLbRule.class)
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
