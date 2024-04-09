package com.chen.xiansen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrderApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderApplicaton.class, args);
    }
}