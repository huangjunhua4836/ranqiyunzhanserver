package com.yl.soft.controller.api;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.LogUtils;
import com.yl.soft.common.util.MD5Util;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.AppLoginDTO;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(tags = {"C端模块-燃气云展登录"})
@Controller
@RequestMapping("/api")
public class AppLoginController extends BaseController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private EhbAudienceService ehbAudienceService;
    @Autowired
    private EhbExhibitorService ehbExhibitorService;

    /**
     * 登出提示
     * @return
     */
    @ApiOperation(value = "app注销接口")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping(value = "/logout")
    @ResponseBody
    public BaseResponse logout(@ApiParam(value = "token",required = true) @RequestParam("token") String token) {
        //注销
        redisService.del(token);
        return setResultSuccess("成功注销！");
    }

    /**
     * 登陆
     * @param phone 用户名
     * @param password 密码
     */
    @ApiOperation(value = "app登录接口")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping(value = "/login")
    @ResponseBody
    public BaseResponse<AppLoginDTO> login(@ApiParam(value = "手机号",required = true) @RequestParam("phone") String phone
            , @ApiParam(value = "密码",required = true) @RequestParam("password") String password) {
        if(StringUtils.isEmpty(phone)){
            return setResultError("手机号为空！");
        }
        if(StringUtils.isEmpty(password)){
            return setResultError("密码为空！");
        }

        //根据登录名查询单个用户
        QueryWrapper<EhbAudience> ehbAudienceQueryWrapper = new QueryWrapper<>();
        ehbAudienceQueryWrapper.eq("phone",phone);
        ehbAudienceQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        EhbAudience ehbAudience = ehbAudienceService.getOne(ehbAudienceQueryWrapper);
        if(ehbAudience == null){
            return setResultError("用户不存在！");
        }
        password = MD5Util.MD5(password);
        if(!password.equals(ehbAudience.getPassword())){
            return setResultError("密码错误！");
        }
        AppLoginDTO appLoginDTO = new AppLoginDTO();
        BeanUtil.copyProperties(ehbAudience,appLoginDTO);
        if(ehbAudience.getIszs()){
            EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(ehbAudience.getId());
            BeanUtil.copyProperties(ehbExhibitor,appLoginDTO);
        }
        boolean isSaveRedisAccessToken = redisService.set(UUID.randomUUID().toString(), JSON.toJSONString(appLoginDTO), BaseApiConstants.SESSIONEXPIRE);
        if(!isSaveRedisAccessToken){
            LogUtils.writeWarnLog(this.getClass(), "登录成功，但是保存redis失败！");
            return setResultError("登录失败！");
        }else{
            return setResultSuccess(appLoginDTO);
        }
    }
}