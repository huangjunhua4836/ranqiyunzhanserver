package com.yl.soft.controller.api;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yl.soft.common.config.Constants;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.ProductNumUtil;
import com.yl.soft.common.util.SendSms;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.po.Smsrecord;
import com.yl.soft.service.SmsrecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "C端模块-发送验证码" })
@RestController
@RequestMapping("/")
public class SendMsgCodeController extends BaseController {

	@Autowired
	private RedisService redisUtil;

	@Autowired
	private SmsrecordService smsrecordService;
	
	@Autowired
	private SendSms sendSms;

	/**
	 * 短信验证码过期时间
	 */
	@Value("${custom.sms.time_out}")
	private Integer sms_code_time_out;

	@ApiOperation(value = "发送验证码", notes = "发送验证码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "smstype", value = "短信类型（0：注册，1：登录，2：绑定手机号，3：忘记密码，4：验证原手机号码，5：更换手机号码）", required = true, paramType = "query"), 
			@ApiImplicitParam(name = "phone", value = "验证码手机号", required = true, paramType = "query"),
	})
	@PostMapping("/send/phoneCode")
	public ResultItem phoneCode(@NotBlank(message = "-101-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-101-请输入正确的手机号") String phone, Integer smstype) {
		ResultItem r = new ResultItem();
		if (smstype == null) {
			return error();
		}
		try {
			String val = ProductNumUtil.getRandNum();
			Smsrecord smsrecord = new Smsrecord();
			smsrecord.setPhone(phone);
			smsrecord.setCreatetime(LocalDateTime.now());
			smsrecord.setType(smstype);
			smsrecord.setContent(val);
			boolean result = smsrecordService.save(smsrecord);
			if (result) {
				r.setDesc("获取验证码成功");
				r.setCode(0);
				r.setData(val);
				sendSms.sendMsgCode(phone, val, smstype);
				redisUtil.set("I" + phone, val, sms_code_time_out);
			} else {
				r = error(-301, "保存发送验证码记录失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok(r);
	}

}
