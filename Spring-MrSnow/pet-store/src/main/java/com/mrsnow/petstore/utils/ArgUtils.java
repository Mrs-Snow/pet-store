package com.mrsnow.petstore.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author MrSnow *** dz
 * @CreateTime: 2023-02-21  14:58
 *  参数校验工具类
 **/
public class ArgUtils {
    public static void main(String[] args) {
        Integer a=null;
        Integer b= 1;
        ifNotNull(a,()-> System.out.println(b));
        System.out.println(getValue(a,10));
    }


    /**
     * 判断是否包含汉字
     * @param str 字符串
     * @return
     */
    public static boolean containsChinese(String str){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher isMatch = p.matcher(str);
        return isMatch.find();
    }

    /**
     * 如果为空则设置一个默认值
     * @param data 校验数据
     * @param defaultValue 默认值
     * @param <T> 类型
     */
    public static <T> T getValue(T data,T defaultValue){
        if (data==null){
            return defaultValue;
        }
        else {
            return data;
        }
    }

    /**
     * 如果不为空则do，否则抛出异常
     * @param data 校验数据
     * @param supplier do
     * @param message 异常信息
     * @param <T> data类型
     * @throws Exception
     */
    public static <T> void ifNotNull(T data,Supplier supplier,String message) throws Exception {
        if (data==null){
            throw new Exception(message);
        }
        else {
            supplier.help();
        }
    }

    /**
     * 如果为空则抛异常
     * @param data
     * @param message
     * @param <T>
     * @throws Exception
     */
    public static <T> void ifNull(T data,String message) throws Exception {
        if(data==null){
            throw new Exception(message);
        }
    }

    /**
     * 如果不为空则set
     */

    public static <T> void ifNotNull(T data,Supplier supplier){
        if(data!=null){
            supplier.help();
        }
    }

    /**
     * 如果为null 则 do
     * @param data
     * @param supplier
     * @param <T>
     */
    public static <T> void ifNull(T data,Supplier supplier){
        if(data==null){
            supplier.help();
        }
    }
}
