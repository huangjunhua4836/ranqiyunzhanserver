package com.yl.soft.controller.api;


import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yl.soft.common.config.Constants;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.EhbAudienceDto;
import com.yl.soft.dto.UserConv;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.enums.LoginType;
import com.yl.soft.enums.UserEnum;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.service.EhbAudienceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <p>
 * 用户 前端控制器
 * <p>
 * 注册就2个入口： 一个手机号验证码登陆自动注册 一个第三方+手机号注册 登陆3个： 密码+手机(不提示是否注册、只有账密错误) 验证码+手机（自动注册）
 * 第三方（第三方未注册登陆code是-301） 修改密码： 只能短信验证码修改（包括找回密码的修改）
 * </p>
 *
 * @author xingdi
 * @since 2020-09-07
 */
@Api(tags = {"C端模块-用户登录"})
@RestController
@RequestMapping("/")
@Validated
public class UserLoginController extends BaseController{

    @Autowired
    private EhbAudienceService ehbAudienceService;
    
    @Autowired
    private SessionState sessionState;
	
    private ResultItem<EhbAudienceDto> setSessionUser(EhbAudience user) {
        if (UserEnum.State.of(user.getEnabled()) == UserEnum.State.禁用) {
            return error(-203, "账号已被冻结");
        }
        String token = UUID.randomUUID().toString();
        SessionUser sessionUser = new SessionUser();
        sessionUser.setCode(0);
        sessionUser.setId(user.getId());
        BeanUtils.copyProperties(user, sessionUser);
        sessionState.setSessionUser(token, sessionUser);
        EhbAudienceDto userDto = UserConv.do2dto(user);
        ResultItem<EhbAudienceDto> result = new ResultItem<>();
        result.setToken(token);
        result.setData(userDto);
        return result;
    }
    
    private EhbAudience generateNewUserWithPhone(String phone) {
    	EhbAudience user = new EhbAudience();
        user.setCreatetime(LocalDateTime.now());
        user.setUpdatetime(LocalDateTime.now());
        user.setIsdel(Boolean.FALSE);
        user.setPhone(phone);
        user.setState(UserEnum.Qualification.未认证.getValue());
        user.setEnabled(UserEnum.State.启用.getValue());
        return user;
    }
    
    
	@ApiOperation(value = "手机密码登录", notes = "使用手机和密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = -101, message = "请输入手机号"),
            @ApiResponse(code = -102, message = "请输入密码"),
            @ApiResponse(code = -202, message = "账号或密码错误"),
            @ApiResponse(code = -203, message = "账号已被冻结"),
            @ApiResponse(code = -301, message = "未注册，请先注册"),
            @ApiResponse(code = 0, message = "登录成功"),
            @ApiResponse(code = 500, message = "未知异常,请联系管理员"),
    })
    @PostMapping("/signin_password")
    public ResultItem<EhbAudienceDto> signinWithPassword(
            @NotBlank(message = "-101-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-101-请输入正确的手机号") String phone,
            @NotBlank(message = "-102-请输入密码") @Pattern(regexp = Constants.PASSWD_REG, message = "-102-密码必须为字母开头，由6-20位字符组成") String password) {
		EhbAudience user = ehbAudienceService.lambdaQuery().eq(EhbAudience::getPhone, phone)
                .eq(EhbAudience::getPassword, ehbAudienceService.encryptPassword(password)).one();
        if (user == null) {
            return error(202, "账号或密码错误");
        }
        return setSessionUser(user);
    }
	
	
	 	@ApiOperation(value = "手机验证码登录", notes = "使用手机验证码登录， 未注册手机会自动注册并登陆")
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
	            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
	    })
	    @ApiResponses({
	            @ApiResponse(code = -101, message = "请输入手机号"),
	            @ApiResponse(code = -102, message = "请输入验证码"),
	            @ApiResponse(code = -202, message = "验证码错误"),
	            @ApiResponse(code = -203, message = "账号已被冻结"),
	            @ApiResponse(code = 0, message = "登录成功"),
	            @ApiResponse(code = 500, message = "未知异常,请联系管理员"),
	    })
	    @PostMapping("/signin_sms")
	    public ResultItem<EhbAudienceDto> signinWithSms(
	            @NotBlank(message = "-101-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-101-请输入正确的手机号") String phone,
	            @NotBlank(message = "-101-验证码错误") String code) {
	        EhbAudience user = ehbAudienceService.lambdaQuery().eq(EhbAudience::getPhone, phone).one();
	        if (user == null) {
	            // NOTICE: 手机号登陆， 未注册直接注册即可。 第三方注册时会与该账号合并。
	        	user = generateNewUserWithPhone(phone);
	            if (!ehbAudienceService.save(user)) {
	                return error();
	            }
	        }
	        return setSessionUser(user);
	    }
	 	
	 	
	    @ApiOperation(value = "第三方登录", notes = "第三方登录")
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "type", value = "第三方类型：1=微信，2=QQ", required = true, paramType = "query"),
	            @ApiImplicitParam(name = "reqcode", value = "请求码", required = true, paramType = "query")
	    })
	    @ApiResponses({
	            @ApiResponse(code = -101, message = "请输入正确的第三方类型"),
	            @ApiResponse(code = -102, message = "请输入请求码"),
	            @ApiResponse(code = -202, message = "请求码无效"),
	            @ApiResponse(code = -203, message = "账号已被冻结"),
	            @ApiResponse(code = -301, message = "未注册，请先注册"),
	            @ApiResponse(code = 0, message = "登录成功"),
	            @ApiResponse(code = 500, message = "未知异常,请联系管理员"),
	    })
	    @PostMapping("/signin_openid")
	    public ResultItem<EhbAudienceDto> signinWithOpenid(
	            @NotNull(message = "-101-无效的第三方类型") @Positive(message = "-101-无效的第三方类型") Integer type,
	            @NotBlank(message = "-102-请求码无效") String reqcode) {
	        LoginType loginType = LoginType.of(type);
	        if (loginType == null) {
	            return error(-101, "请输入正确的第三方类型");
	        }
	        String openId = ehbAudienceService.getOpenId(loginType, reqcode);
	        if (StringUtils.isBlank(openId)) {
	            return error(-102, "请求码无效");
	        }
	        EhbAudience user = null;
	        switch (loginType) {
	            case 微信:
	                user = ehbAudienceService.lambdaQuery().eq(EhbAudience::getWxOpenid, openId).one();
	                break;
	            case QQ:
	                user = ehbAudienceService.lambdaQuery().eq(EhbAudience::getQqOpenid, openId).one();
	                break;
	        }
	        if (user == null) {
	            return error(-301, "未注册，请先注册");
	        }
	        return setSessionUser(user);
	    }
	    
	    
	    @ApiOperation(value = "第三方注册", notes = "第三方注册")
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "type", value = "第三方类型：1=微信，2=QQ", required = true, paramType = "query"),
	            @ApiImplicitParam(name = "reqcode", value = "请求码", required = true, paramType = "query"),
	            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
	            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
	    })
	    @ApiResponses({
	            @ApiResponse(code = -101, message = "请输入正确的第三方类型"),
	            @ApiResponse(code = -102, message = "请输入请求码"),
	            @ApiResponse(code = -202, message = "请求码无效"),
	            @ApiResponse(code = -101, message = "请输入手机号"),
	            @ApiResponse(code = -102, message = "请输入验证码"),
	            @ApiResponse(code = -202, message = "验证码错误"),
	            @ApiResponse(code = -301, message = "已注册，请登陆"),
	            @ApiResponse(code = 0, message = "登录成功"),
	            @ApiResponse(code = 500, message = "未知异常,请联系管理员"),
	    })
	    @PostMapping("/signup_openid")
	    public ResultItem<EhbAudienceDto> signupWithOpenid(
	    		@NotNull(message = "-101-无效的第三方类型") @Positive(message = "-101-无效的第三方类型") Integer type,
	            @NotBlank(message = "-102-请输入请求码") String reqcode,
	            @NotBlank(message = "-103-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-103-请输入正确的手机号") String phone,
	            @NotBlank(message = "-104-验证码错误") String code) {
	        LoginType loginType = LoginType.of(type);
	        if (loginType == null) {
	            return error(-101, "请输入正确的第三方类型");
	        }
	        String openId = ehbAudienceService.getOpenId(loginType, reqcode);
	        if (StringUtils.isBlank(openId)) {
	            return error(-102, "请求码无效");
	        }
	        EhbAudience user = null;
	        switch (loginType) {
	            case 微信:
	                user = ehbAudienceService.lambdaQuery().eq(EhbAudience::getWxOpenid, openId).one();
	                break;
	            case QQ:
	                user = ehbAudienceService.lambdaQuery().eq(EhbAudience::getQqOpenid, openId).one();
	                break;
	        }
	        if (user != null) {
	            return error(-301, "已注册，请登陆");
	        }
	        user = ehbAudienceService.lambdaQuery().eq(EhbAudience::getPhone, phone).one();
	        if (user != null) {
	            // NOTICE: 已有手机的账号做合并 添加qqwx号
	            switch (loginType) {
	                case 微信:
	                    if (StringUtils.isNotBlank(user.getWxOpenid())) {
	                        return error(-203, "该手机号已绑定其他微信账号");
	                    }
	                    user.setWxOpenid(openId);
	                    break;
	                case QQ:
	                    if (StringUtils.isNotBlank(user.getQqOpenid())) {
	                        return error(-203, "该手机号已绑定其他QQ账号");
	                    }
	                    user.setQqOpenid(openId);
	                    break;
	            }
	            if (!ehbAudienceService.updateById(user)) {
	                return error();
	            }
	        } else {
	            // 新手机 且 新qqwx号
	            user = generateNewUserWithPhone(phone);
	            switch (loginType) {
	                case 微信:
	                    user.setWxOpenid(openId);
	                    break;
	                case QQ:
	                    user.setQqOpenid(openId);
	                    break;
	            }
	            if (!ehbAudienceService.save(user)) {
	                return error();
	            }
	        }
	        return setSessionUser(user);
	    }
}
