package com.ljh.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ljh.admin.dao.mysql")
public class LoginMain {
    public static void main(String[] args) {
        SpringApplication.run(LoginMain.class,args);
    }

}
