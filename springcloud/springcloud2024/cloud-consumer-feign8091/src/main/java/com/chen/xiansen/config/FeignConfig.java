package com.chen.xiansen.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    /**
     * 重试机制
     * @return
     */
//    @Bean
//    public Retryer retryer() {
//        return new Retryer.Default(100, 1, 3);
//    }

    /**
     * 自定义feign日志
     * @return
     */
    @Bean
    public Logger.Level logger(){
        return Logger.Level.FULL;
    }
}
