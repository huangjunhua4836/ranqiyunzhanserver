package com.yl.soft.controller;

import com.alibaba.fastjson.JSON;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.MD5Util;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.base.PlatformSessionUser;
import com.yl.soft.po.CrmMenu;
import com.yl.soft.service.CrmRoleService;
import com.yl.soft.service.CrmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/platform")
public class LoginController extends BaseController {
    @Autowired
    private CrmUserService crmUserService;
    @Autowired
    private CrmRoleService crmRoleService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        return "index2";
    }

    /**
     * 欢迎页面设置
     * @return
     */
    @GetMapping("/")
    public String welcome() {
        return "redirect:/platform/login";
    }

    /**
     * 登出提示
     * @return
     */
    @GetMapping(value = "/logout")
    @ResponseBody
    public BaseResponse logout(HttpServletResponse response) {
        //注销
        Cookie cookie = new Cookie("loginCookieKey",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return setResultSuccess("成功注销！");
    }

    /**
     * 跳转登录页面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登陆
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public BaseResponse login(String username, String password,HttpServletResponse response) {
        if(StringUtils.isEmpty(username)){
            return setResultError("用户名为空！");
        }
        if(StringUtils.isEmpty(password)){
            return setResultError("密码为空！");
        }

        //根据登录名查询单个用户
        PlatformSessionUser sessionUser = crmUserService.loginByUserCode(username);
        if(sessionUser == null || StringUtils.isEmpty(sessionUser.getId())){
            return setResultError("用户不存在！");
        }
        password = MD5Util.MD5(password);
        if(!password.equals(sessionUser.getPassword())){
            return setResultError("密码错误！");
        }
        sessionUser.setPassword(null);//消除密码

        if(!StringUtils.isEmpty(sessionUser.getRoleId())){
            List<CrmMenu> crmMenus = crmRoleService.getMenusByRoleId(sessionUser.getRoleId());
            sessionUser.setCrmMenus(crmMenus);//登录用户权限
        }

        String loginCookieKey = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("loginCookieKey",loginCookieKey);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);

        redisService.set(loginCookieKey,JSON.toJSONString(sessionUser),60*60*24);
        return setResultSuccess();
    }
}