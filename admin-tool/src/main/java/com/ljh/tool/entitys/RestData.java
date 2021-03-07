package com.ljh.tool.entitys;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data //封装
@AllArgsConstructor //有参构造函数
@NoArgsConstructor //无参构造函数
public class RestData<T> implements Serializable {


    private  int code;
    private  String message;
    private  T   data;

    public RestData(int code,String message){
        this(code,message,null);
    }
}