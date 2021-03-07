package com.ljh.admin;


import com.auth0.jwt.interfaces.Claim;
import com.ljh.tool.util.JwtConfig;

//@SpringBootApplication
//@MapperScan("com.ljh.admin.dao.mysql")
//@MapperScan({"com.ljh.admin.mp.dao"})
public class LoginMain {
//    public static void main(String[] args) {
//        SpringApplication.run(LoginMain.class,args);
//    }
public static void main(String[] args) {
    String jwt=JwtConfig.createToken("wslwd");
    Claim str=JwtConfig.parseToken(jwt).get("userId");
    System.out.println(str);
}
}
