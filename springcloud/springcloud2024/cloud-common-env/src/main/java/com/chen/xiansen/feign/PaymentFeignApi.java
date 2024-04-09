package com.chen.xiansen.feign;

import com.chen.xiansen.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "payment",name = "cloud-provider-payment")
public interface PaymentFeignApi {

    @GetMapping("/payment/get/{id}")
    public ResultData getOne(@PathVariable("id") String id);

    @GetMapping("/payment/loadbalance")
    public String loadbalance();

    @GetMapping("/payment/circuit/{id}")
    public String circuit(@PathVariable("id") String id);

    @GetMapping("/payment/bulkhead/{id}")
    public String bulkhead(@PathVariable("id") String id);

    @GetMapping("/payment/rateLimit/{id}")
    public String rateLimit(@PathVariable("id") String id);

    @GetMapping("/payment/micrometer")
    public String micrometer();
}
