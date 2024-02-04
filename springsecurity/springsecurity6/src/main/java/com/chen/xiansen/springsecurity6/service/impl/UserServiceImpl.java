package com.chen.xiansen.springsecurity6.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.xiansen.springsecurity6.component.DBUserDetailsManager;
import com.chen.xiansen.springsecurity6.entity.User;
import com.chen.xiansen.springsecurity6.mapper.UserMapper;
import com.chen.xiansen.springsecurity6.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    DBUserDetailsManager manager;

    @Resource
    BCryptPasswordEncoder encoder;

    @Override
    public void createUser(User user) {
        UserDetails service = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUsername())
                .password(user.getPassword())
                .passwordEncoder(s -> encoder.encode(s))
                .build();
        manager.createUser(service);
    }
}
