package com.anands.virtual.config;

import com.anands.virtual.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletResponse response = CorsHelper.addResponseHeaders(res);
            chain.doFilter(req, response);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Failed to get the response.");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
