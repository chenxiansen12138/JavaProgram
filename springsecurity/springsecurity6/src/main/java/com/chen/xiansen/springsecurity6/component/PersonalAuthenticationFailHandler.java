package com.chen.xiansen.springsecurity6.component;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;

/**
 * 认证失败时的处理
 */
@Configuration
public class PersonalAuthenticationFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //获取用户身份信息
        Object principal = exception.getMessage();
        HashMap result = new HashMap();
        result.put("code", "1");
        result.put("status", "失败");
        result.put("data", principal);
        //结果转换为json
        String json = JSON.toJSONString(result);
        //返回json数据到前端
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
