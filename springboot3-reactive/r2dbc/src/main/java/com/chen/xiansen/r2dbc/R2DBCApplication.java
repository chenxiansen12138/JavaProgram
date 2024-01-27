package com.chen.xiansen.r2dbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot对r2dbc的自动配置
 * 1.R2dbcAutoConfiguration:    主要配置连接工厂,连接池
 * 2.R2dbcDataAutoConfiguration    (R2dbcEntityTemplate)用来操作数据库;提供crud
 * 3.R2dbcRepositoriesAutoConfiguration: 开启Spring Data声明式接口方式的CRUD;
 * 4.R2dbcTransactionManagerAutoConfiguration
 */
@SpringBootApplication
public class R2DBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(R2DBCApplication.class, args);
    }
}
