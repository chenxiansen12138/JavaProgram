package com.chen.xiansen.security.component;

import jakarta.annotation.Resource;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ReactiveSecurityUserDetailsService implements ReactiveUserDetailsService {

    @Resource
    DatabaseClient client;
    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
//        Mono<UserDetails> details = client.sql("")
//                .bind(0, username)
//                .fetch()
//                .one()
//                .map(map -> {
//                    UserDetails build = User.builder()
//                            .username(username)
//                            .password(map.get("password").toString())
//                            .passwordEncoder(s -> passwordEncoder.encode(s))
//                            .authorities("download", "delete", "upload")
//                            .roles("admin","sales")
//                            .build();
//                    return build;
//                });
        UserDetails build = User
                .builder()
                .username("zhangsan")
                .password("$2a$10$zIuGZMx770MASoOooAhKJeXtAGlvica6gNvbXpyycpgupg1IEid5u")
                .authorities("download", "delete", "upload")
                .roles("admin", "sales")
                .build();
        return Mono.just(build);
    }
}
