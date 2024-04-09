package com.chen.xiansen.controller;

import com.chen.xiansen.feign.PaymentFeignApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicrometerController {
    @Resource
    PaymentFeignApi api;

    @GetMapping("/micrometer")
    public String micrometer() {
        return api.micrometer();
    }
}
