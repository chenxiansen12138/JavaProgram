package com.chen.xiansen.controller;

import com.chen.xiansen.feign.PaymentFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class CircuitController {
    @Resource
    PaymentFeignApi api;

    /**
     * 熔断降级
     *
     * @param id
     * @return
     */
    @GetMapping("/circuit/{id}")
    @CircuitBreaker(name = "cloud-provider-payment", fallbackMethod = "circuitFallback")
    public String circuit(@PathVariable("id") String id) {
        return api.circuit(id);
    }

    /**
     * 舱板隔离
     *
     * @param id
     * @return
     */
    @GetMapping("/bulkhead/{id}")
    @Bulkhead(name = "cloud-provider-payment", fallbackMethod = "bulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public String bulkhead(@PathVariable("id") String id) {
        return api.bulkhead(id);
    }

    @GetMapping("/thread-pool-bulkhead/{id}")
    @Bulkhead(name = "cloud-provider-payment", fallbackMethod = "threadPoolBulkheadFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> threadPoolBulkhead(@PathVariable("id") String id) {
        return CompletableFuture.supplyAsync(() -> api.bulkhead(id));
    }

    @GetMapping("/rateLimit/{id}")
    @RateLimiter(name = "cloud-provider-payment", fallbackMethod = "rateLimitFallback")
    public String rateLimit(@PathVariable("id") String id) {
        return api.rateLimit(id);
    }

    public String rateLimitFallback(Throwable t) {
        return "被限流了,很尴尬😅";
    }

    public CompletableFuture<String> threadPoolBulkheadFallback(String id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "thread-pool最大为2");
    }

    public String bulkheadFallback(Throwable t) {
        return "最大并发为2,请稍后再试";
    }

    /**
     * 服务降级
     */
    public String circuitFallback(Throwable t) {
        return "系统繁忙,请稍后再试~~~";
    }

}
