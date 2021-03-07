package com.ljh.admin.service;


import org.springframework.stereotype.Service;

import java.util.Map;


public interface LoginService {

    Map<String,Object> userService(String id,String password);



}
