package com.njoy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ouyanglingzhi
 */
@SpringBootApplication
@EnableDiscoveryClient  /* Consul or Zookeeper */
public class PaymentZKMain {
    public static void main(String[] args) {
        SpringApplication.run(PaymentZKMain.class, args);
    }
}
