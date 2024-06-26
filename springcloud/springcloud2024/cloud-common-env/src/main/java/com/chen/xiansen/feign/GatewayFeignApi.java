package com.chen.xiansen.feign;

import com.chen.xiansen.component.GatewayFeignFactory;
import com.chen.xiansen.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "gateway",name = "cloud-gateway",fallbackFactory = GatewayFeignFactory.class)
public interface GatewayFeignApi {
    @GetMapping(value = "/payment/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") String id);

    @GetMapping(value = "/payment/gateway/info")
    public ResultData<String> getGatewayInfo();

    @GetMapping(value = "/payment/gateway/filter/get")
    public ResultData<String> filterInit(@RequestHeader HttpHeaders headers);
}
