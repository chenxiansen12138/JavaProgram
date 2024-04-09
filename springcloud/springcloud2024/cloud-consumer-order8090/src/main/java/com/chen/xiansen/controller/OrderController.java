package com.chen.xiansen.controller;

import com.chen.xiansen.common.URL;
import com.chen.xiansen.model.PaymentDTO;
import com.chen.xiansen.response.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {


    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/add")
    public ResultData add(PaymentDTO dto) {
        return restTemplate.postForObject(URL.PAYMENT_URL + "/payment/add", dto, ResultData.class);
    }

    @GetMapping("/get/{id}")
    public ResultData add(@PathVariable("id") String id) {
        return restTemplate.getForObject(URL.PAYMENT_URL + "/payment/get/" + id, ResultData.class, id);
    }

    @GetMapping("/loadbalance")
    public String loadBalance(){
        return restTemplate.getForObject(URL.PAYMENT_URL+"/payment/loadbalance", String.class);
    }
}
