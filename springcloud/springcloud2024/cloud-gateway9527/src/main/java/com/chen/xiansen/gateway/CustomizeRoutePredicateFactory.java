package com.chen.xiansen.gateway;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class CustomizeRoutePredicateFactory extends AbstractRoutePredicateFactory<CustomizeRoutePredicateFactory.Config> {

    public CustomizeRoutePredicateFactory() {
        super(Config.class);
    }

    /**
     * 支持shortcut方式
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    @Override
    public Predicate<ServerWebExchange> apply(CustomizeRoutePredicateFactory.Config config) {

        return exchange -> {
            String userType = exchange.getRequest().getQueryParams().getFirst("userType");
            if (userType == null) return false;
            return userType.equalsIgnoreCase(config.getUserType());
        };
    }

    @Setter
    @Getter
    @Validated
    public static class Config {
        @NotEmpty
        private String userType;
    }
}
