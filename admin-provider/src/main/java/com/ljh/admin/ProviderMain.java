package com.ljh.admin;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderMain {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("provider.xml");
        ioc.start();
        System.in.read(); // 按任意键退出
    }
}
