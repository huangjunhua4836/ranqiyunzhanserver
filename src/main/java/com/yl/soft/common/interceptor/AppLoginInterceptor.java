/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yl.soft.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.unified.service.BaseResponseUtil;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.dto.EhbAudienceDto;
import com.yl.soft.dto.UserConv;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.enums.UserEnum;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.service.EhbAudienceService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * The Class is for Interceptor Login Platform This
 *
 * @author Administrator
 * @version $Id: TracebacktoLoginInterceptor.java, v 0.1 2016年2月22日 下午4:00:42 Administrator Exp $
 */
public class AppLoginInterceptor implements HandlerInterceptor {

    RedisService redisService;
    EhbAudienceService ehbAudienceService;

    public AppLoginInterceptor(RedisService redisService,EhbAudienceService ehbAudienceService){
        this.redisService = redisService;
        this.ehbAudienceService = ehbAudienceService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("***********拦截器开始*******************");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("***********拦截器结束*******************");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //后台登录拦截器
        System.out.println("***********拦截器执行*******************");
        response.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSON(new BaseResponseUtil().setResultError(401,"","token为空！")));
            out.flush();
            out.close();
            return false;
        }
        //生产环境请去掉testtoken
        if("123456".equals(token)){
            EhbAudience ehbAudience = ehbAudienceService.getById(24);
            SessionUser sessionUser = new SessionUser();
            sessionUser.setCode(0);
            sessionUser.setId(ehbAudience.getId());
            BeanUtils.copyProperties(ehbAudience, sessionUser);
            redisService.set(token, sessionUser);
            return true;
        }
        if(!redisService.hasKey(token)){
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSON(new BaseResponseUtil().setResultError(402,"","token失效！")));
            out.flush();
            out.close();
            return false;
        }
        return true;
    }
}