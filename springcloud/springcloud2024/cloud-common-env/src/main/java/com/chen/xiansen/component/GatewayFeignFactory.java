package com.chen.xiansen.component;

import com.chen.xiansen.feign.GatewayFeignApi;
import jakarta.annotation.Resource;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class GatewayFeignFactory implements FallbackFactory<GatewayFeignApi> {
    @Resource
    GatewayFailFallback fallback;

    @Override
    public GatewayFeignApi create(Throwable cause) {
        cause.printStackTrace();
        return fallback;
    }
}
