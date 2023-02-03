package com.meiken.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author glf
 * @Date 2021/9/16
 */
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("[LogFilter] : init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("[LogFilter] : - doFilter");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("[LogFilter] : - destroy");
    }
}
