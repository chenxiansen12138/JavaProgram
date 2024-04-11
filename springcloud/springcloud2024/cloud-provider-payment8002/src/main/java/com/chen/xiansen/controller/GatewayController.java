package com.chen.xiansen.controller;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.chen.xiansen.response.ResultData;
import com.chen.xiansen.service.PaymentService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/gateway/filter/get")
    public ResultData<String> filterInit(@RequestHeader HttpHeaders headers) {
        String existKey = "";
        String existVal = "";
        for (Map.Entry<String, List<String>> en : headers.entrySet()) {
            System.out.println("en = " + en.getKey() + ":" + en.getValue().getFirst());
            if (en.getKey().equalsIgnoreCase("X-Request-Ch")) {
                existKey = en.getKey();
                existVal = en.getValue().getFirst();
            }
        }
        if (StringUtils.isEmpty(existVal)) {
            return ResultData.success("this request don't contains expected headers");
        }
        return ResultData.success("this request contains" + existKey + ":" + existVal);
    }
}
