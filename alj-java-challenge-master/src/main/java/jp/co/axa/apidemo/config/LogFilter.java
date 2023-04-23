package jp.co.axa.apidemo.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: LIN
 * @createDate: 2023/4/23
 * @description: log the info when received the requests.
 */
@Slf4j
public class LogFilter implements Filter {
    @Override public void init(FilterConfig filterConfig) {
        log.info("============ LogFilter Initialized ==============");
    }

    /**
     * log the request info
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException { // do something 处理request 或response

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("============ request received ==============");
        // request url
        log.info("Request URL  : {}", request.getRequestURL().toString());
        // Http method
        log.info("HTTP Method  : {}", request.getMethod());
        // request IP
        log.info("Request from : {}", request.getRemoteAddr());
        filterChain.doFilter(request, response);
    }

    @Override public void destroy() {
        log.info("============ LogFilter destroyed ==============");
    }
}