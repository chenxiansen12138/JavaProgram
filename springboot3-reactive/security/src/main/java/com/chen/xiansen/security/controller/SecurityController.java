package com.chen.xiansen.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SecurityController {

    //角色
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/security")
    public Mono<String> security(){

        return Mono.just("Hello Security!");
    }

    //权限
    @PreAuthorize("hasAuthority('haha')")
    @GetMapping("/authority")
    public Mono<String> authority(){

        return Mono.just("Hello Authority!");
    }
}
