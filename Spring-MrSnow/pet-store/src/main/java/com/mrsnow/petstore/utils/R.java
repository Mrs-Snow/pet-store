package com.mrsnow.petstore.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author MrSnow *** dz
 * @CreateTime: 2022-12-14  14:11
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {
    private static final int SUCCESS_CODE = 200;
    private static final int LOGIN_CODE = 110;
    private static final int FAIL_CODE = -1;
    private int code;
    private T data;
    private String message;

    public R (int code,String message){
        this.code=code;
        this.message=message;
    }

    public R(T data) {
       this.data = data;
    }

    public static <E> R<E> success(E data,String message){
        return new R<E>(SUCCESS_CODE,data,message);
    }

    public static R success(String message){
        return new R(SUCCESS_CODE,message);
    }

    public static <E> R<E> success(E data){
        return new R(data);
    }

    public static  R fail(String message){
        return new R(FAIL_CODE,message);
    }

    public static  R noLogin(String message){
        return new R(LOGIN_CODE,message);
    }
}
