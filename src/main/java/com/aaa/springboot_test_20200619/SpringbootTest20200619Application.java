package com.aaa.springboot_test_20200619;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aaa.springboot_test_20200619.mapper")
public class SpringbootTest20200619Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTest20200619Application.class, args);
    }

}
