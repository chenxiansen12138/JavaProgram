package com.chen.xiansen.controller;

import com.chen.xiansen.feign.GatewayFeignApi;
import com.chen.xiansen.response.ResultData;
import jakarta.annotation.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @Resource
    private GatewayFeignApi api;

    @GetMapping("/gateway/info")
    public ResultData<String> info() {
        return api.getGatewayInfo();
    }

    @GetMapping("/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") String id) {
        return api.getById(id);
    }

    @GetMapping("/gateway/filter/get")
    public ResultData filterInit(@RequestHeader HttpHeaders headers) {
        return api.filterInit(headers);
    }

}
