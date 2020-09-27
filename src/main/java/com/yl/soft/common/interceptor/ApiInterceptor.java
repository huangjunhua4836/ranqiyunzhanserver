/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yl.soft.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class is for Interceptor Login Platform This
 *
 * @author Administrator
 * @version $Id: TracebacktoLoginInterceptor.java, v 0.1 2016年2月22日 下午4:00:42 Administrator Exp $
 */
public class ApiInterceptor implements HandlerInterceptor {



    /**
     * @see HandlerInterceptor#afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
                                Exception arg3) throws Exception {
       }

    /**
     * @see HandlerInterceptor#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
                           ModelAndView arg3) throws Exception {
     }

    /**
     * @see HandlerInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
       // response.setHeader("Access-Control-Allow-Credentials", "true");//true代表允许携带cookie
        // String origin = request.getHeader("Origin");
      /*  if (origin == null) {
            origin = request.getHeader("Referer");
        }
        resp.setHeader("Access-Control-Allow-Origin", origin);//这里不能写*，*代表接受所有域名访问，如写*则下面一行代码无效。谨记

        chain.doFilter(servletRequest, servletResponse);*/
        return true;
    }
}