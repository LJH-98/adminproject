package com.ljh.admin.config;


import com.ljh.tool.entitys.RestData;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 请求参数为空拦截器
 */
@Component
public class NotNullHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> map=request.getParameterMap();
        Set<Map.Entry<String,String[]>> entry=map.entrySet();
        for (Map.Entry<String, String[]> me : entry) {
            //为空拦截
            if (StringUtils.isBlank(me.getValue()[0])){
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter()
                        .append(new RestData<String>(500,"参数为空").toString());
                return false;
            }

        }
        return true;
    }
}
