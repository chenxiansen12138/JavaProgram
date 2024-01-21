package com.chen.xiansen.webflux.natively;


import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class WebfluxApplication {
    public static void main(String[] args) throws IOException {
        //1.创建一个能处理Http请求的处理器
        HttpHandler handler = (request, response) -> {
            System.out.println("请求:" + request.getURI());
            DataBufferFactory factory = response.bufferFactory();
            DataBuffer buff = factory.wrap("hello world".getBytes(StandardCharsets.UTF_8));
            //需要一个DataBuffer的发布者

            //编写请求处理的业务
            return response.writeWith(Mono.just(buff));
        };
        //2.启动一个服务器,监听8080端口,接受数据,拿到数据给HttpHandler进行请求处理
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
        //3.启动Netty服务器
        HttpServer.create().host("localhost").port(8080).handle(adapter).bindNow();
        System.out.println("服务已经启动...监听8080端口");
        System.in.read();
    }
}
