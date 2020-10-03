package com.yl.soft.controller.api;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.util.StringUtil;
import com.yl.soft.common.config.Constants;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.DateUtils;
import com.yl.soft.common.util.SendEmail;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.EhbAudienceDto;
import com.yl.soft.dto.EhbAudiencedlDto;
import com.yl.soft.dto.UserConv;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.enums.LoginType;
import com.yl.soft.enums.UserEnum;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <p>
 * 用户 前端控制器
 * 
 * @author xingdi
 * @since 2020-09-07
 */
@Api(tags = { "C端模块-用户登录" })
@RestController
@RequestMapping("/")
@Validated
public class UserLoginController extends BaseController {

	@Autowired
	private EhbAudienceService ehbAudienceService;

	@Autowired
	private RedisService redisUtil;
	
	@Autowired
	private EhbExhibitorService ehbExhibitorService;

	@Autowired
	private SessionState sessionState;
	
	@Autowired
	private SendEmail sendEmail;

	
	
	private BaseResult<EhbAudiencedlDto> setSessionUser(EhbAudience user) {
		if (UserEnum.State.of(user.getEnabled()) == UserEnum.State.禁用) {
			return error(-203, "账号已被冻结");
		}
		String token = UUID.randomUUID().toString();
		String pass=UUID.randomUUID().toString();
		SessionUser sessionUser = new SessionUser();
		sessionUser.setCode(200);
		sessionUser.setId(user.getId());
		BeanUtils.copyProperties(user, sessionUser);
		sessionState.setSessionUser(token, sessionUser);
		EhbAudiencedlDto userDto = UserConv.do2dto(user);
		userDto.setPassword(pass);
		userDto.setToken(token);
		userDto.setType(user.getType());
		userDto.setIsnew(user.getIsnew());
		userDto.setIszs(user.getBopie()==null?0:1);
		if(user.getBopie()!=null) {
			EhbExhibitor e=ehbExhibitorService.getById(user.getBopie());
			userDto.setIsrz(e==null?-1:e.getState());
		}
		BaseResult<EhbAudiencedlDto> result = new BaseResult<>();
		user.setTempass(pass);
		user.setIsnew(1);
		ehbAudienceService.updateById(user);
		
		result.setData(userDto);
		result.setCode(200);
		result.setDesc("登录成功");
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
	@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"), })
	@ApiResponses({ @ApiResponse(code = -101, message = "请输入手机号"), @ApiResponse(code = -102, message = "请输入密码"),
			@ApiResponse(code = -202, message = "账号或密码错误"), @ApiResponse(code = -203, message = "账号已被冻结"),
			@ApiResponse(code = -301, message = "未注册，请先注册"), @ApiResponse(code = 0, message = "登录成功"),
			@ApiResponse(code = 500, message = "未知异常,请联系管理员"), })
	@PostMapping("/signin_password")
	public BaseResult<EhbAudiencedlDto> signinWithPassword(
			@NotBlank(message = "-101-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-101-请输入正确的手机号") String phone,
			@NotBlank(message = "-102-请输入密码") @Pattern(regexp = Constants.PASSWD_REG, message = "-102-密码必须为字母开头，由6-20位字符组成") String password) {
		EhbAudience user = ehbAudienceService.lambdaQuery().eq(EhbAudience::getPhone, phone)
				.eq(EhbAudience::getPassword, ehbAudienceService.encryptPassword(password)).one();
		if (user == null) {
			return error(202, "账号或密码错误");
		}
		return setSessionUser(user);
	}
	
//	@ApiOperation(value = "ceshi", notes = "ces")
//	@GetMapping("/cc")
//	public BaseResult<EhbAudiencedlDto> ccc(){
//		sendEmail.sendMail("11185888@163.com", "122222");
//		return ok2();
//	}

	@ApiOperation(value = "手机验证码登录", notes = "使用手机验证码登录")
	@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"), })
	@ApiResponses({ @ApiResponse(code = -101, message = "请输入手机号"), @ApiResponse(code = -102, message = "请输入验证码"),
			@ApiResponse(code = -202, message = "验证码错误"), @ApiResponse(code = -203, message = "账号已被冻结"),
			@ApiResponse(code = 200, message = "登录成功"), @ApiResponse(code = 500, message = "未知异常,请联系管理员"), })
	@PostMapping("/signin_sms")
	public BaseResult<EhbAudiencedlDto> signinWithSms(
			@NotBlank(message = "-101-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-101-请输入正确的手机号") String phone,
			@NotBlank(message = "-101-验证码错误") String code) {
		EhbAudience user = ehbAudienceService.lambdaQuery().eq(EhbAudience::getPhone, phone).one();
		
		String sms = redisUtil.get("I" + phone.trim());
		if (!code.equals(sms)) {
			return error(-202, "验证码错误");
		}
		if (user == null) {
			return error(-202, "请您先注册再登录");
		}
		return setSessionUser(user);
	}
	
	@ApiOperation(value = "手机验证码注册", notes = "使用手机验证码注册")
	@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "type", value = "用户类型（0：观展用户，1参展用户）", required = true, paramType = "query"),
			})
	@ApiResponses({ @ApiResponse(code = -101, message = "请输入手机号"), @ApiResponse(code = -102, message = "请输入验证码"),
			@ApiResponse(code = -202, message = "验证码错误"), @ApiResponse(code = -203, message = "账号已被冻结"),
			@ApiResponse(code = 200, message = "登录成功"), @ApiResponse(code = 500, message = "未知异常,请联系管理员"), })
	@PostMapping("/signinWithSmsr")
	public BaseResult<EhbAudiencedlDto> signinWithSmsr(
			@NotBlank(message = "-101-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-101-请输入正确的手机号") String phone,
			@NotBlank(message = "-101-验证码错误") String code,Integer type,String password) {
		EhbAudience user = ehbAudienceService.lambdaQuery().eq(EhbAudience::getPhone, phone).one();
		
		if(user!=null) {
			return error(-302, "当前手机号已被注册");
		}
		String sms = redisUtil.get("I" + phone.trim());
		if (!code.equals(sms)) {
			return error(-202, "验证码错误");
		}
		EhbAudience ehbAudience=new EhbAudience();
		ehbAudience.setPhone(phone);
		ehbAudience.setLoginname(phone);
		ehbAudience.setPassword(ehbAudienceService.encryptPassword(password));
		ehbAudience.setType(0);
		ehbAudience.setIsnew(0);
		if(type==1) { //普通用户
			EhbExhibitor entity=new EhbExhibitor();
			entity.setIsdel(false);
			ehbExhibitorService.save(entity);
			ehbAudience.setBopie(entity.getId());
		}
		ehbAudienceService.save(ehbAudience);
		
		return setSessionUser(ehbAudience);
	}

	@ApiOperation(value = "第三方登录", notes = "第三方登录返回-301弹出绑定手机号进行注册")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "第三方类型：1=微信，2=QQ", required = true, paramType = "query"),
			@ApiImplicitParam(name = "reqcode", value = "请求码", required = true, paramType = "query") })
	@ApiResponses({ @ApiResponse(code = -101, message = "请输入正确的第三方类型"), @ApiResponse(code = -102, message = "请输入请求码"),
			@ApiResponse(code = -202, message = "请求码无效"), @ApiResponse(code = -203, message = "账号已被冻结"),
			@ApiResponse(code = -301, message = "未注册，请先注册"), @ApiResponse(code = 0, message = "登录成功"),
			@ApiResponse(code = 500, message = "未知异常,请联系管理员"), })
	@PostMapping("/signin_openid")
	public BaseResult<EhbAudiencedlDto> signinWithOpenid(
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

	@ApiOperation(value = "第三方注册绑定手机号", notes = "第三方注册绑定手机号")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "第三方类型：1=微信，2=QQ", required = true, paramType = "query"),
			@ApiImplicitParam(name = "reqcode", value = "请求码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"), })
	@ApiResponses({ @ApiResponse(code = -101, message = "请输入正确的第三方类型"), @ApiResponse(code = -102, message = "请输入请求码"),
			@ApiResponse(code = -202, message = "请求码无效"), @ApiResponse(code = -101, message = "请输入手机号"),
			@ApiResponse(code = -102, message = "请输入验证码"), @ApiResponse(code = -202, message = "验证码错误"),
			@ApiResponse(code = -301, message = "已注册，请登陆"), @ApiResponse(code = 0, message = "登录成功"),
			@ApiResponse(code = 500, message = "未知异常,请联系管理员"), })
	@PostMapping("/signup_openid")
	public BaseResult<EhbAudiencedlDto> signupWithOpenid(
			@NotNull(message = "-101-无效的第三方类型") @Positive(message = "-101-无效的第三方类型") Integer type,
			@NotBlank(message = "-102-请输入请求码") String reqcode,
			@NotBlank(message = "-103-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-103-请输入正确的手机号") String phone,
			@NotBlank(message = "-104-验证码错误") String code) {
		LoginType loginType = LoginType.of(type);
		String sms = redisUtil.get("I" + phone.trim());
		if (!code.equals(sms)) {
			return error(-202, "验证码错误");
		}
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
	
	

	@ApiOperation(value = "修改密码", notes = "修改密码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "token", value = "登陆标识", required = false, paramType = "query"), })
	@ApiResponses({ @ApiResponse(code = -101, message = "请输入手机号"), @ApiResponse(code = -103, message = "请输入验证码"),
			@ApiResponse(code = -102, message = "密码必须为字母开头，由6-20位字符组成"), @ApiResponse(code = -202, message = "验证码错误"),
			@ApiResponse(code = 0, message = "修改成功"), @ApiResponse(code = 500, message = "未知异常,请联系管理员"), })
	@PutMapping("/change_password")
	public BaseResult<Void> changePassword(
			@NotBlank(message = "-101-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-101-请输入正确的手机号") String phone,
			@NotBlank(message = "-103-请输入验证码") String code,
			@NotBlank(message = "-102-请输入密码") @Pattern(regexp = Constants.PASSWD_REG, message = "-102-密码必须为字母开头，由6-20位字符组成") String password,
			String token) {
		String sms = redisUtil.get("I" + phone.trim());
		if (!code.equals(sms)) {
			return error(-202, "验证码错误");
		}
		if (!ehbAudienceService.lambdaUpdate()
				.set(EhbAudience::getPassword, ehbAudienceService.encryptPassword(password))
				.eq(EhbAudience::getPhone, phone).update()) {
			return error();
		}
		BaseResult r = new BaseResult();
		r.setCode(200);
		r.setDesc("更换成功");
		r.setStartTime(DateUtils.DateToString(new Date(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
		return r;
	}

	@ApiOperation(value = "更换手机号", notes = "更换手机号码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "oldCode", value = "原手机验证码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"), })
	@ApiResponses({ @ApiResponse(code = -101, message = "请输入新手机号"), @ApiResponse(code = -102, message = "请输入原手机验证码"),
			@ApiResponse(code = -103, message = "请输入新手机验证码"),
			@ApiResponse(code = -201, message = "新手机号已被其他账号绑定，请更换其他手机号"),
			@ApiResponse(code = -202, message = "原手机验证码错误"), @ApiResponse(code = -203, message = "新手机验证码错误"),
			@ApiResponse(code = 0, message = "更换手机号成功"), @ApiResponse(code = 500, message = "未知异常,请联系管理员"), })
	@PutMapping("/change_phone")
	public BaseResult<Void> changePhone(@NotBlank(message = "-102-请输入原手机验证码") String oldCode,
			@NotBlank(message = "-101-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-101-请输入正确的手机号") String phone,
			@NotBlank(message = "-103-请输入新手机验证码") String code, String token) {
		BaseResult r = new BaseResult();
		SessionUser sessionUser = sessionState.getCurrentUser(token);
		String sms = redisUtil.get("I" + phone.trim());
		if (!code.equals(sms)) {
			return error(-203, "新手机验证码错误");
		}
		String old = redisUtil.get("I" + sessionUser.getPhone().trim());
		if (!code.equals(old)) {
			return error(-202, "新手机验证码错误");
		}
		if (ehbAudienceService.lambdaQuery().eq(EhbAudience::getPhone, phone).count() > 0) {
			return error(-201, "新手机号已被其他账号绑定，请更换其他手机号");
		}
		sessionUser.setPhone(phone);
		if (!ehbAudienceService.lambdaUpdate().set(EhbAudience::getPhone, phone)
				.eq(EhbAudience::getId, sessionUser.getId()).update()) {
			return error();
		}
		r.setCode(200);
		r.setDesc("更换成功");
		r.setStartTime(DateUtils.DateToString(new Date(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
		return r;
	}
	
	@ApiOperation(value = "注销登录", notes = "注销登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录手机号", required = true, paramType = "query")
    })
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "注销成功")
	})
    @PostMapping(value = "/log_out")
    public BaseResult log_out(@NotBlank(message = "-501-TOKEN为空！") String token) {
		BaseResult r = new BaseResult();
        sessionState.delSessionUser(token);
        r.setCode(200);
        r.setDesc("注销登录");
        r.setStartTime(DateUtils.DateToString(new Date(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
        return r;
    }
	
	 @ApiOperation(value = "临时密码授权登录", notes = "临时密码生成token", httpMethod = "POST")
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "temppass", value = "临时密码", required = true, paramType = "query")
	    })
		@ApiResponses({ 
			@ApiResponse(code = 200, message = "授权成功"),
			@ApiResponse(code = -501, message = "授权失败临时密码为空"),
			@ApiResponse(code = 500, message = "未知异常,请联系管理员"), })
	    @PostMapping(value = "/tempPass")
	    public BaseResult tempPass(String temppass) {
	        if (StringUtil.isEmpty(temppass)) {
	            return error(501,"临时密码为空");
	        }
	       EhbAudience user=  ehbAudienceService.lambdaQuery().eq(EhbAudience::getTempass, temppass).one();
	       return setSessionUser(user);
	    }
	
    @ApiOperation(value = "TOKEN续命", notes = "续命为延迟5分钟", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录手机号", required = true, paramType = "query")
    })
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "续命成功"),
		@ApiResponse(code = -501, message = "TOKEN为空"),
		@ApiResponse(code = -601, message = "续命失败！"),
		@ApiResponse(code = 500, message = "未知异常,请联系管理员"), })
    @PostMapping(value = "/heartbeat")
    public BaseResult heartbeat(String token) {
    	BaseResult r = new BaseResult();
        if (StringUtil.isEmpty(token)) {
            r.setDesc("token为空！");
            r.setCode(-501);
            return r;
        }
        boolean timeout = sessionState.DelayTokenTimeOut(token);
        if (timeout) {
            r.setCode(200);
            r.setDesc("续命成功！");
        } else {
            r.setCode(-601);
            r.setDesc("续命失败！");
        }
        return r;
    }
    
    
    
    
}
