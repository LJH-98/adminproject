package com.ljh.admin.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将自定义拦截器容器启动起来
        //registry.addInterceptor(new LoginHandlerInterceptor())
                //.addPathPatterns("/**")   //拦截的亲求 默认拦截全部
                //.excludePathPatterns("/","/login"); //放行的请求
        //registry.addInterceptor(new NotNullHandlerInterceptor())
                //.addPathPatterns("/**");   //拦截的亲求 默认拦截全部
    }
}
