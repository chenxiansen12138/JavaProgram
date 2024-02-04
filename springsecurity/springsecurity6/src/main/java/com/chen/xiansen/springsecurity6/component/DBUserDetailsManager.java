package com.chen.xiansen.springsecurity6.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.xiansen.springsecurity6.entity.User;
import com.chen.xiansen.springsecurity6.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {
    @Resource
    UserMapper mapper;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(true);
        mapper.insert(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = mapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            if (user.getUsername().equals("admin")) {
                authorities.add(() -> "USER_CREATE");
            }
            authorities.add(() -> "USER_GET");
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getEnabled(),
//                    true,
//                    true,
//                    true,
//                    authorities
//            );
            return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .disabled(!user.getEnabled())
                    .accountExpired(false)
                    .accountLocked(false)
                    .roles("ADMIN")
                    .build();
        }
    }
}
