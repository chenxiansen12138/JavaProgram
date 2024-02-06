package com.chen.xiansen.springsecurity6.component;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.util.HashMap;

/**
 * 注销时的处理
 */
public class PersonalAuthenticationLogOutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        HashMap result = new HashMap();
        result.put("code", "200");
        result.put("message", "用户注销");
        result.put("data", principal);
        String json = JSON.toJSONString(principal);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
