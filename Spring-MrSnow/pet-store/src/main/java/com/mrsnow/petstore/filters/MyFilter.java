package com.mrsnow.petstore.filters;

import com.mrsnow.petstore.utils.TokenUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;


/**
 * @Author MrSnow *** dz
 * @CreateTime: 2022-12-16  11:43
 **/
@Component
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //校验URL，本简易系统不实现了
        String requestURI = request.getRequestURI();
        //校验token
        String token = request.getHeader("Token");
        if(token==null){
            if(requestURI.contains("user")||requestURI.contains("goods")){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
            wrapper.sendRedirect("/login");
        }
        if(TokenUtil.verify(token)){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            wrapper.sendError(-1,"请登录");
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
