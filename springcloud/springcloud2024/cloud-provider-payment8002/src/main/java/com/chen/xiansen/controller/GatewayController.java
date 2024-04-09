package com.chen.xiansen.controller;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.chen.xiansen.response.ResultData;
import com.chen.xiansen.service.PaymentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @Resource
    private PaymentService service;

    @GetMapping("/gateway/info")
    public ResultData<String> gatewayInfo() {
        return ResultData.success(JSON.toJSONString("gateway info-8002:") + UUID.fastUUID());
    }

    @GetMapping("/gateway/get/{id}")
    public ResultData<String> gatewayInfo(@PathVariable("id") String id) {
        return ResultData.success(JSON.toJSONString(service.getById(Integer.parseInt(id))));
    }
}
