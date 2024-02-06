package com.chen.xiansen.springsecurity6.component;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class SessionHandler implements SessionInformationExpiredStrategy {


    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        HashMap result = new HashMap();
        result.put("code", "-1");
        result.put("message", "该账号已经在其他设备登录");
        result.put("data", "session out");
        String json = JSON.toJSONString(result);
        response.getWriter().println(json);
    }
}
