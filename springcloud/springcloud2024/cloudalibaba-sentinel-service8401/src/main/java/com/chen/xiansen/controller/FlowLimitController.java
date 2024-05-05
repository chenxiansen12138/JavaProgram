package com.chen.xiansen.controller;

import com.chen.xiansen.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @Resource
    private FlowLimitService service;

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }

    @GetMapping("/testC")
    public String testC() {
        service.common();
        return "------testC";
    }

    @GetMapping("/testD")
    public String testD() {
        service.common();
        return "------testD";
    }
}
