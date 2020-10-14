package com.njoy.lb;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ouyanglingzhi
 */
@Configuration
public class RandomLbRule {

    @Bean
    public IRule RandomLbRule(){
        return new RandomRule();
    }
}
