package com.mrsnow.petstore.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import java.util.ArrayList;

/**
 * @Author MrSnow *** dz
 * @CreateTime: 2022-12-16  15:48
 **/
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> baseFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MyFilter());
        ArrayList<String> list = new ArrayList<>();
        list.add("/*");
        filterRegistrationBean.setUrlPatterns(list);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
