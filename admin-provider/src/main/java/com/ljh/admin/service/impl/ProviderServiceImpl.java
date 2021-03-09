package com.ljh.admin.service.impl;

import com.ljh.admin.service.ProviderService;

public class ProviderServiceImpl implements ProviderService {


    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
