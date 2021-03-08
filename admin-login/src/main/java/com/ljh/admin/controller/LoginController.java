package com.ljh.admin.controller;


import cn.hutool.crypto.digest.DigestUtil;
import com.ljh.admin.service.LoginService;
import com.ljh.admin.util.RedisUtil;
import com.ljh.tool.entitys.RestData;
import com.ljh.tool.util.JwtConfig;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "登录Controller", tags = { "用户登录接口" })
@RequestMapping("/user")
public class LoginController  {




    private String mySalt="privateSalt";


    @Resource
    private LoginService LoginService;



    @Resource
    private RedisUtil redisUtil;

    /**
     * 单点登录接口
     * @param id  用户名
     * @param password  用户密码
     * @return  RestData{code:状态 message:提示 data:令牌}
     */
    @ApiOperation(value = "用户首次登录")
    @PostMapping(value = {"/","/login"})
    @ApiResponses(value = { @ApiResponse(code = 1000, message = "成功"), @ApiResponse(code = 1001, message = "失败"),
                            @ApiResponse(code = 1002, message = "缺少参数") })
    public RestData<String> login(@ApiParam("用户ID") @RequestParam("id") String id,
                                  @ApiParam("用户密码") @RequestParam("password") String password,
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
    @ApiOperation(value = "验证是否登录")
    @GetMapping(value = "/getUser")
    @ApiImplicitParams({ @ApiImplicitParam(name = "jwt",
            value = "用户唯一jwt",
            dataType = "String",
            paramType = "query",
            required = true) })
    public  RestData<Object> getUser(HttpSession session, HttpServletRequest request){
        String jwt=request.getHeader("jwt");
        if(StringUtils.isBlank(jwt))
            return new RestData<Object>(200,"success");
        return new RestData<Object>(200,"success",session.getAttribute(jwt));
    }


}
