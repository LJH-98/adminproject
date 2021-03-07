package com.ljh.admin.dao.oracle;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

//@Mapper
public interface LoginOracleDao {

    /**
     * 查用户信息
     * @param id 账号
     * @param password  密码
     * @return
     */
    Map<String,Object> userDao(@Param("id") String id, @Param("password") String password);
}
