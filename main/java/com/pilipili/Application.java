package com.pilipili;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: pilipili
 * @description:
 * @author: 作者
 * @create: 2022-05-02 10:34
 */
@SpringBootApplication
@MapperScan("com.pilipili.mapper")
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
