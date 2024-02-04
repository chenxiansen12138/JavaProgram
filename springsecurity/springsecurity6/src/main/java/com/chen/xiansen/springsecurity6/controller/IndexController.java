package com.chen.xiansen.springsecurity6.controller;

import com.chen.xiansen.springsecurity6.entity.User;
import com.chen.xiansen.springsecurity6.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
    @Resource
    UserService userService;

    @GetMapping("getUsers")
    public List<User> index() {
        return userService.list();
    }

    @PostMapping("/createUser")
    public void createUser(User user) {
        userService.createUser(user);
    }

    /**
     * 用户认证信息
     * @return
     */
    @GetMapping("/info")
    public Map getInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = context.getAuthentication().getAuthorities();
        Map map = new HashMap();
        map.put("name", name);
        map.put("authorities", authorities);
        return map;

    }
}
