package com.chen.xiansen.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
public class CircuitController {
    @GetMapping("/circuit/{id}")
    public String circuit(@PathVariable("id") String id) throws InterruptedException {
        if (id.equals("-1")) {
            throw new RuntimeException("不能为负数");
        }
        if (id.equals("1")) {
            TimeUnit.SECONDS.sleep(5);
        }
        return "hello circuit";
    }

    @GetMapping("/bulkhead/{id}")
    public String bulkhead(@PathVariable("id") String id) throws InterruptedException {
        if ("9".equals(id)) {
            TimeUnit.SECONDS.sleep(10);
        }
        return "hello bulkhead!";
    }

    @GetMapping("/rateLimit/{id}")
    public String rateLimit(@PathVariable("id") String id) {
        return "RateLimiter-" + id + "-" + IdUtil.fastUUID();
    }
}
