//package com.mrsnow.petstore.configs.interceptor;
//
//import org.apache.ibatis.binding.MapperMethod;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.ParameterMap;
//import org.apache.ibatis.mapping.SqlCommandType;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Intercepts;
//import org.apache.ibatis.plugin.Invocation;
//import org.apache.ibatis.plugin.Signature;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Field;
//import java.util.Date;
//import java.util.Objects;
//import java.util.Properties;
//import java.util.concurrent.Executors;
//
///**
// * @Author MrSnow *** dz
// * @CreateTime: 2023-03-23  16:00
// **/
//@Component
//@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
//public class MyInterceptor implements Interceptor {
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        //获取一个MappedStatement
//        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
//        //sql类型 UNKNOWN INSERT UPDATE DELETE SELECT FLUSH
//        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
//        if (sqlCommandType.equals(SqlCommandType.DELETE)||sqlCommandType.equals(SqlCommandType.SELECT)){
//            return invocation.proceed();
//        }
//        if (sqlCommandType.equals(SqlCommandType.INSERT)){
//            Object arg = invocation.getArgs()[1];
//            Field updateTime = arg.getClass().getDeclaredField("updateTime");
//            updateTime.setAccessible(true);
//            updateTime.set(arg, new Date());
//        }
//        if (sqlCommandType.equals(SqlCommandType.UPDATE)){
//            MapperMethod.ParamMap parameter = (MapperMethod.ParamMap)invocation.getArgs()[1];
//            //获取私有成员变量
//            System.out.println(parameter.getClass().getName());
//            Object param1 = parameter.get("param1");
//            Field updateTime = param1.getClass().getDeclaredField("updateTime");
//            updateTime.setAccessible(true);
//            updateTime.set(param1, new Date());
//        }
//
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
