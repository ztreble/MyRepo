package com.neu.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mark on 17/11/4.
 */
@Component
public class CorsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");//跨域请求全部允许?
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");//允许5个请求方式
        response.setHeader("Access-Control-Allow-Headers", "token, accept, content-type, X-Requested-With");//请求头中添加4项许可
        return true;//说明这里放行了
    }


}
