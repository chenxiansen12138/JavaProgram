package com.chen.xiansen.springsecurity6;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class Springsecurity6ApplicationTests {

    @Resource
    BCryptPasswordEncoder encoder;

    @Test
    void contextLoads() {
        System.out.println(encoder.encode("password"));

        System.out.println(encoder.matches("password", encoder.encode("password")));

    }

}
