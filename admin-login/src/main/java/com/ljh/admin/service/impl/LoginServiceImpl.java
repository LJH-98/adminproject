package com.ljh.admin.service.impl;


import com.ljh.admin.dao.mysql.LoginMySqlDao;
import com.ljh.admin.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {


    @Resource
    private LoginMySqlDao loginMySqlDao;


    @Override
    public Map<String, Object> userService(String id,String password) {
        return loginMySqlDao.userDao(id,password);
    }


}
