package com.chen.xiansen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicrometerController {

    @GetMapping("/micrometer")
    public String micrometer(){
        return "hello micrometer+zipkin";
    }
}
