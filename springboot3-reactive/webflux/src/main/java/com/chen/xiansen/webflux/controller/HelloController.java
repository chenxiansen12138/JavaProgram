package com.chen.xiansen.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class HelloController {

    /**
     * webflux向下兼容springmvc的大部分注解
     *
     */
    @GetMapping("/helloworld")
    public String helloWorld() {
        return "hello World";
    }

    /**
     * 单个数据用Mono
     *
     */
    @GetMapping("/hellomono")
    public Mono<String> helloMono() {
        return Mono.just("Hello Mono!!!");
    }


    /**
     * 多个数据用Flux
     *
     */
    @GetMapping("/helloflux")
    public Flux<Integer> helloFlux() {
        return Flux.range(1, 5);
    }

    /**
     * SSE:
     * 服务端推送
     *
     */
    @GetMapping(value = "/sse",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> sse() {
        return Flux.range(1, 100)
                //构建一个SSE对象
                .map(integer -> ServerSentEvent.
                        builder("ha-"+integer).
                        id(integer+ " ").
                        comment("hei-"+integer).
                        event("haha").
                        build())
                .delayElements(Duration.ofMillis(500));
    }
    @GetMapping("/exception")
    public Mono<Integer> error(){
        return Mono.just(0).map(integer -> 10/integer);
    }

}
