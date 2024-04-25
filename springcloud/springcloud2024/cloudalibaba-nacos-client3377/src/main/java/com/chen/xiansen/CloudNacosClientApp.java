package com.chen.xiansen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudNacosClientApp {
    public static void main(String[] args) {
        SpringApplication.run(CloudNacosClientApp.class, args);
    }
}