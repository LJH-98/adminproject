package com.ljh.admin;

import com.ljh.admin.service.ProviderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerMain {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        ProviderService providerService = (ProviderService)context.getBean("ProviderService"); // 获取远程服务代理
        String hello = providerService.sayHello("world"); // 执行远程方法
        System.out.println( hello ); // 显示调用结果
    }
}
