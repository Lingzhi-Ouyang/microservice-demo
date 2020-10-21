package com.njoy.springcloud.config;

import com.njoy.springcloud.entities.SimulatedFault;
import com.njoy.springcloud.utils.CodeEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ouyanglingzhi
 */
@Configuration
@Slf4j
@RefreshScope
public class GateWayFilter {

    @Bean
    public GlobalFilter customFilter() {
        return new CustomGlobalFilter();
    }

    public class CustomGlobalFilter implements GlobalFilter, Ordered {

        @Value("${log.info}")
        private String logInfo;

        @Value("${log.variable}")
        private String var;

        @Value("${delay:0}")
        private String delay;

        @Resource
        SimulatedFault simulatedFault;

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            log.info("------------------Filter 1: id must be not in the query parameters!");
            String id = exchange.getRequest().getQueryParams().getFirst("id");
            if(id != null){
                log.info("id != null");
                exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                return exchange.getResponse().setComplete();
            }
            log.info("id == null!");
            Map<String,Object> map = new HashMap<>();
            map.put("log", log);
            map.put("id", id);
            String exp = "log.info(\"" + logInfo + "\"," + var + ")";
            log.info(exp);
            CodeEngine.convert(exp, map);
            log.info("config delay: {}", delay);
            log.info("---------------set delay: {}", simulatedFault.getDelayInMilliseconds());
            return chain.filter(exchange);
        }

        @Override
        public int getOrder() {
            return -1;
        }
    }
}
