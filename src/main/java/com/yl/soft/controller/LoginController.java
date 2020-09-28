package com.yl.soft.controller;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/platform")
public class LoginController extends BaseController {

    @Autowired
    private CrmUserService crmUserService;

    @Autowired
    private CrmRoleService crmRoleService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
        System.out.println(basePath);
        request.getSession().setAttribute("basePath",basePath);
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
    public BaseResponse logout(HttpServletRequest request) {
        //注销
        request.getSession(false).invalidate();
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
    public BaseResponse login(String username, String password,HttpServletRequest request) {
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
        HttpSession session = request.getSession();
        session.setAttribute("loginUserInfo",sessionUser);//利用session保存登陆者信息
        return setResultSuccess();
    }
}