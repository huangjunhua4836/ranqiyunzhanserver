package com.yl.soft.controller.api;

import com.alibaba.fastjson.JSON;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.LogUtils;
import com.yl.soft.common.util.MD5Util;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.base.SessionUser;
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
@RequestMapping("/api")
public class AppLoginController extends BaseController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private CrmUserService crmUserService;

    @Autowired
    private CrmRoleService crmRoleService;

    /**
     * 登出提示
     * @return
     */
    @GetMapping(value = "/logout")
    @ResponseBody
    public BaseResponse logout(HttpServletRequest request) {
        //注销
        redisService.del(request.getSession(false).getId());
        return setResultSuccess("成功注销！");
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
        SessionUser sessionUser = crmUserService.loginByUserCode(username);
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
        session.setAttribute("loginUserInfo",sessionUser);//利用shiro的session保存登陆者信息
        boolean isSaveRedisAccessToken = redisService.set(session.getId(), JSON.toJSONString(sessionUser), BaseApiConstants.SESSIONEXPIRE);
        if(!isSaveRedisAccessToken){
            LogUtils.writeWarnLog(this.getClass(), "登录成功，但是保存redis失败！");
        }
        return setResultSuccess();
    }
}