package com.ljh.admin.controller;


import cn.hutool.crypto.digest.DigestUtil;
import com.ljh.admin.service.LoginService;
import com.ljh.admin.util.RedisUtil;
import com.ljh.tool.entitys.RestData;
import com.ljh.tool.util.JwtConfig;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Vector;


/**
 * 单点登录
 */
@RestController
public class LoginController  {




    private String mySalt="privateSalt";


    @Resource
    private LoginService LoginService;

    private static List<HttpSession> sessionList=new Vector<>();

    {
        HttpSession httpSession = sessionList.get(0);
        httpSession.getAttribute("key1");
    }

    @Resource
    private RedisUtil redisUtil;

    /**
     * 单点登录接口
     * @param id  用户名
     * @param password  用户密码
     * @return  RestData{code:状态 message:提示 data:令牌}
     */
    @PostMapping(value = {"/","/login"})
    //@Transactional
    public RestData<String> login(@RequestParam("id") String id,
                                  @RequestParam("password") String password,
                                  HttpSession session){

        //加密
        password = DigestUtil.md5Hex(password + mySalt);
        //数据库查询
        Map<String, Object> mysqlMap = LoginService.userService(id, password);
        if (mysqlMap == null) return new RestData<String>(500, "error/MySQL用户登录失败");
        //信息无误 生成jwt
        String jwt = JwtConfig.createToken(id);
        //存入Redis
        System.out.println(redisUtil.hmset(jwt,mysqlMap));
        //.opsForValue().set(jwt,mysqlMap.toString());



        //用户信息存入缓存 ,现在用session代替
        session.setAttribute(jwt, mysqlMap);
        return new RestData<String>(200, "success", jwt);
    }


    /**
     * 获取用户信息
     * @return
     */
    @GetMapping(value = "/getUser")
    public  RestData<Object> getUser(HttpSession session, HttpServletRequest request){
        String jwt=request.getHeader("jwt");
        if(StringUtils.isBlank(jwt))
            return new RestData<Object>(200,"success");
        return new RestData<Object>(200,"success",session.getAttribute(jwt));
    }


}
