//package com.mrsnow.petstore.configs.interceptor;
//
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Intercepts;
//import org.apache.ibatis.plugin.Invocation;
//import org.apache.ibatis.plugin.Signature;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.util.Properties;
//
///**
// * @Author MrSnow *** dz
// * @CreateTime: 2023-03-23  16:55
// **/
//@Component
//@Intercepts(@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class}))
//public class InsertInterceptor implements Interceptor {
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        Object[] invocationArgs = invocation.getArgs();
//
//        return invocation.proceed();
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Interceptor.super.plugin(target);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//        Interceptor.super.setProperties(properties);
//    }
//}
