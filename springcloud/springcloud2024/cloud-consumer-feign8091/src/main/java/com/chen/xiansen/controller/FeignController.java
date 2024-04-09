package com.chen.xiansen.controller;

import com.chen.xiansen.feign.PaymentFeignApi;
import com.chen.xiansen.response.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Resource
    PaymentFeignApi api;

    @GetMapping("/get/{id}")
    public ResultData getOne(@PathVariable("id") String id) {
        return api.getOne(id);
    }

    @GetMapping("/loadbalance")
    public String loadbalance() {
        return api.loadbalance();
    }
}
