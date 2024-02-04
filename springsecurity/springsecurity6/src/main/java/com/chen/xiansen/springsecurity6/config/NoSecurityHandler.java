package com.chen.xiansen.springsecurity6.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;

/**
 * 未认证时的处理
 */
public class NoSecurityHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //获取用户身份信息
        Object principal = authException.getMessage();
        HashMap result = new HashMap();
        result.put("code", "1");
        result.put("status", "请求未认证");
        result.put("data", principal);
        //结果转换为json
        String json = JSON.toJSONString(result);
        //返回json数据到前端
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
