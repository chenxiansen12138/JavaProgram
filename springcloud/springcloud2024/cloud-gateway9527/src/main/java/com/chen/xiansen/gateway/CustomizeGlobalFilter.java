package com.chen.xiansen.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义filter
 */
@Component
@Slf4j
public class CustomizeGlobalFilter implements GlobalFilter, Ordered {
    public static final String BEGIN_VISIT_TIME = "begin_visit_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //先记录下访问接口的开始时间
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long beginVisitTime = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (beginVisitTime != null) {
                System.out.println("访问的主机地址: " + exchange.getRequest().getHeaders().getHost());
                System.out.println("接口调用时间 " + (System.currentTimeMillis() - beginVisitTime)+"毫秒");
            }
        }));
    }

    /**
     * 数字越小,优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
