package com.ljh.admin.config;

import com.ljh.tool.entitys.RestData;
import com.ljh.tool.util.JwtConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登录拦截器
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {


    //@Value("${token.key}")
    private static final String KEY="privateKey";

    /**
     * 中断方式是令preHandle的返回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     * 前置拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器开启1");
        //String servletPath=request.getServletPath();  //servlet请求路径
        String header=request.getHeader("jwt"); //获取响应头
        //request.getHeaderNames();
        if(!JwtConfig.verify(header,KEY)){   //验证失败 返回客户端消息
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter()
                                         .append(new RestData<String>(500,"令牌不可用").toString());
            return false;
        }
        return true;
        //return false;
    }

    /**
     * 后置处理
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求完成后执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
