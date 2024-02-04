package com.chen.xiansen.springsecurity6.config;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity //开启springsecurity的自定义配置
public class WebSecurityConfiguration {

    @Resource
    SessionHandler handler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        authorization ->
                                authorization
                                        //设置用户权限
                                        .requestMatchers("/getUsers").hasAuthority("USER_GET")
                                        .requestMatchers("/createUser").hasAuthority("USER_CREATE")
                                        //角色处理
                                        .requestMatchers("/info").hasRole("ADMIN")
                                        //对所有请求开始授权保护
                                        .anyRequest()
                                        //已经认证的请求会被自动授权
                                        .authenticated())
                //使用表单授权方式
                .formLogin(form -> {
                    form.loginPage("/login").permitAll()//无需授权即可访问
                            //认证成功时的处理
                            .successHandler(new PersonalAuthenticationSuccessHandler())
                            //认证失败时的处理
                            .failureHandler(new PersonalAuthenticationFailHandler())


                    //.usernameParameter("").passwordParameter();配置自定义表单参数
                    //.failureUrl("/login?failure");//校验失败时的跳转地址
                    ;
                }).logout(logoutConfigurer -> logoutConfigurer.logoutSuccessHandler(new PersonalAuthenticationLogOutHandler()))
                //处理未认证的请求
                .exceptionHandling(handler -> {
                    handler.authenticationEntryPoint(new NoSecurityHandler());
                    handler.accessDeniedHandler((request, response, accessDeniedException) -> {
                        Map map = new HashMap();
                        map.put("code", "-2");
                        map.put("status", "没有该权限");
                        String json = JSON.toJSONString(map);
                        response.setContentType("contextType/json;charset=UTF-8");
                        response.getWriter().println(json);
                    });
                })
        ;
        // 使用基本授权方式
//                .httpBasic(Customizer.withDefaults());
        // 跨域
        http.csrf(Customizer.withDefaults());
        //会话并发处理
        http.sessionManagement(session -> session.maximumSessions(1).expiredSessionStrategy(handler));
        return http.build();
    }

    //    @Bean
//    public UserDetailsService userDetailsService() {
//        //创建基于内存的用户信息管理器
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        //创建user对象用于管理用户名,密码,权限,角色等
//        manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("password").passwordEncoder(s -> encoder().encode(s)).build());
//        return manager;
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //创建基于内存的用户信息管理器
//        DBUserDetailsManager manager = new DBUserDetailsManager();
//        //创建user对象用于管理用户名,密码,权限,角色等
//        return manager;
//    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
