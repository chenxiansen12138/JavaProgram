package com.chen.xiansen.webflux.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController
public class ApiController {

    /**
     * ServerWebExchange:封装了请求和响应对象的对象;自定义获取数据,自定义响应
     * ServerHttpRequest,ServerHttpResponse:请求,响应
     * WebSession:访问Session对象
     * org.springframework.http.HttpMethod:请求方式
     *
     * @param exchange
     * @return
     * @PathVariable 路径变量
     * @RequestParam 请求参数
     * @CookValue 获取cookie
     */
    public Mono<String> api(ServerWebExchange exchange,
                            ServerHttpRequest request,
                            ServerHttpResponse response,
                            WebSession session,
                            HttpMethod method) {
        return Mono.empty();
    }

    /**
     * Rendering:一种视图对象,新版的页面跳转API;如果是页面跳转,不能标注@ResonseBody
     *
     * @return
     */
    public Rendering render() {
        //重定向
        return Rendering.redirectTo("http://www.baidu.com").build();
    }
}
