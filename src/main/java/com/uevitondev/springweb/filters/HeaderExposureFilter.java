package com.uevitondev.springweb.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HeaderExposureFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.addHeader("access-control-expose-headers", "location");
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
