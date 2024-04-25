package com.chen.xiansen.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class NacosClientController {

    @Value("${chen.xiansen.username}")
    private String username;

    @GetMapping("/getUsername")
    public String getUsername() {
        return "从配置文件中取得了用户名:".concat(username);
    }
}
