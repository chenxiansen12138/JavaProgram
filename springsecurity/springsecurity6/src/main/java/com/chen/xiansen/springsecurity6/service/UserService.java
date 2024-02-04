package com.chen.xiansen.springsecurity6.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.xiansen.springsecurity6.entity.User;

public interface UserService extends IService<User> {
    void createUser(User user);
}
