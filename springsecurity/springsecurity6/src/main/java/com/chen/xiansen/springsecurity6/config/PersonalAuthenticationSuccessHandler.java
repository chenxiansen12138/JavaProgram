package com.chen.xiansen.springsecurity6.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/**
 * 认证成功时的处理
 */
@Configuration
public class PersonalAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取用户身份信息
        Object principal = authentication.getPrincipal();
        //获取用户凭证信息
        Object credentials = authentication.getCredentials();
        //获取用户权限信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        HashMap result = new HashMap();
        result.put("code", "0");
        result.put("status", "登录成功");
        result.put("data", principal);
        //结果转换为json
        String json = JSON.toJSONString(result);
        //返回json数据到前端
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
