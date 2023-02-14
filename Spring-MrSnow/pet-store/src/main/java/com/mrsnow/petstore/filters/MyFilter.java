package com.mrsnow.petstore.filters;

import com.alibaba.fastjson.JSON;
import com.mrsnow.petstore.utils.BeanContextUtil;
import com.mrsnow.petstore.utils.R;
import com.mrsnow.petstore.utils.TokenUtil;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.applet.AppletContext;
import java.io.IOException;
import java.util.List;


/**
 * @Author MrSnow *** dz
 * @CreateTime: 2022-12-16  11:43
 **/
@Component
@Data
public class MyFilter implements Filter {

    public static int REDIRECT_CODE = -1;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //校验URL，本简易系统不实现了
        String requestURI = request.getRequestURI();
        //校验token
        String token = request.getHeader("Token");


        FilterItem filterItem = BeanContextUtil.getBean(FilterItem.class);
        List<String> passList = filterItem.getPassList();
        for (String checkUrl : passList) {
            if (requestURI.contains(checkUrl)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
//            if(requestURI.contains("user")||requestURI.contains("goods")){
//                filterChain.doFilter(servletRequest,servletResponse);
//                return;
//            }
//            wrapper.sendRedirect("/login");
        if (token==null){
            servletResponse.getWriter().write(JSON.toJSONString(R.fail("请登录!")));
            return;
        }
        if (TokenUtil.verify(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            wrapper.sendError(REDIRECT_CODE, "登录已过期");
        }
    }

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
