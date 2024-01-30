package com.chen.xiansen.security.config;

import com.chen.xiansen.security.component.ReactiveSecurityUserDetailsService;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {
    @Resource
    ReactiveSecurityUserDetailsService service;
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //1. 定义那些请求需要认证,哪些不需要
        http.authorizeExchange(authorize -> {
            //1.1.允许所有人都访问静态资源
            authorize.matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            //1.2.剩下的所有请求都需要认证
            authorize.anyExchange().authenticated();
        });
        //2.开启默认的表单登录
        http.formLogin();
        //3.安全控制
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);

        //认证方式:自定义用户名和密码
        //4.认证规则:使用认证管理器查询用户信息
        http.authenticationManager(new UserDetailsRepositoryReactiveAuthenticationManager(service));

        //构建安全配置
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
