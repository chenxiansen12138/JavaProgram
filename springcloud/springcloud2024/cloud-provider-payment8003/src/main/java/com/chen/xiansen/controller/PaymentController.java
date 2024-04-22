package com.chen.xiansen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @GetMapping("/hello")
    public String test(){
        return "hello";
    }
}
