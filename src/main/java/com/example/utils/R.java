package com.example.utils;

import lombok.Data;

@Data
public class R<T>{

    private Integer code;// 200表示成功，400表示失败
    private String msg;// 成功或失败的提示信息
    private T data;//传递的数值

    public static <T> R<T> success(T object,String msg,Integer codex){
        R<T> r=new R<T>();
        r.data=object;
        r.code=codex;
        r.msg=msg;
        return r;
    }

    public static <T> R<T> error(String msg,Integer codex){
        R<T> r=new R<T>();
        r.msg=msg;
        r.code=codex;
        return r;
    }
}