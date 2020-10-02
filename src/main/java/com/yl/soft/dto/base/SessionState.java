package com.yl.soft.dto.base;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.StringUtilsEX;

@Component
public class SessionState {

    private int time = 1 * 15 * 60;
    @Autowired
    private RedisService redisService;
    
    @Value("${custom.session.time_out}")
    private int sessionTimeOut;
    
    @Value("${custom.session.cookie_name}")
    private String sessionCookieName;

    @Bean
    public SessionState getSessionState() {
        return new SessionState();
    }
    
    
    /**
     * 延迟Token过期时间
     *
     * @param token
     */
    public Boolean DelayTokenTimeOut(String token) {
        String val = redisService.get(token);
        if (!StringUtilsEX.IsNullOrWhiteSpace(val)) {
            //5分钟
            redisService.set(token, val, time);
            return true;
        }
        return false;
    }

    /**
     * 获取当前用户信息
     *
     * @param token
     * @return code 0 获取成功 -1 未登陆或登陆超时
     */
    public SessionUser getCurrentUser(String token) {
        if (token == null)
            token = "";
        String userInfo = redisService.get(token);
        SessionUser sessionUser;
        if (userInfo == null) {
            sessionUser = new SessionUser();
            sessionUser.setCode(-1);
        } else {
            sessionUser = JSON.parseObject(userInfo, SessionUser.class);
        }
        if (sessionUser != null) {
            return sessionUser;
        } else {
            sessionUser = new SessionUser();
            sessionUser.setCode(-1);
            return sessionUser;
        }
    }

    /**
     * 获取当前用户信息
     *
     * @return code 0 获取成功 -1 未登陆或登陆超时
     */
    public SessionUser getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Cookie[] cookies = request.getCookies();
        SessionUser sessionUser;
        String token = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(sessionCookieName)) {
                    token = cookie.getValue();
                    break;
                }
            }
            String userInfo = redisService.get(token);
            if (StringUtils.isBlank(userInfo)) {
                sessionUser = new SessionUser();
                sessionUser.setCode(-1);
            } else {
                sessionUser = JSON.parseObject(userInfo, SessionUser.class);
            }
        } else {
            sessionUser = new SessionUser();
            sessionUser.setCode(-1);
        }
        return sessionUser;
    }

    /**
     * 保存 登陆用户信息
     *
     * @param token
     * @param user
     */
    public void setSessionUser(String token, SessionUser user) {
        //5分钟
        redisService.set(token, user);
    }

    /**
     * 删除 登陆用户信息
     *
     * @param token
     */
    public void delSessionUser(String token) {
        redisService.delete(token);
    }

    /**
     * 延迟Token过期时间
     *
     * @param token
     */
    public Boolean delayTokenTimeOut(String token) {
        String val = redisService.get(token);
        if (StringUtils.isNotBlank(val)) {
            //5分钟
            redisService.set(token, val, sessionTimeOut);
            return true;
        }
        return false;
    }

    /**
     * 延迟Token过期时间
     */
    public Boolean delayTokenTimeOut() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        String token = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(sessionCookieName)) {
                    token = cookie.getValue();
                    break;
                }
            }
            String val = redisService.get(token);
            if (StringUtils.isNotBlank(val)) {
                redisService.set(token, val, sessionTimeOut);
                return true;
            }
        }
        return false;
    }

}
