package com.chen.xiansen.gateway;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Component
public class CustomizeGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomizeGatewayFilterFactory.Config> {

    public CustomizeGatewayFilterFactory() {
        super(CustomizeGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("status");
    }

    @Override
    public GatewayFilter apply(CustomizeGatewayFilterFactory.Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (request.getQueryParams().containsKey("username")) {
                return chain.filter(exchange);
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                return exchange.getResponse().setComplete();
            }
        });
    }

    @Data
    @Validated
    public static class Config {
        private String status; //设定一个状态值,等于多少值的时候才能访问
    }
}
