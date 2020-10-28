package com.yl.soft.controller.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yl.soft.common.config.Constants;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.DateUtils;
import com.yl.soft.common.util.ProductNumUtil;
import com.yl.soft.common.util.SendEmail;
import com.yl.soft.common.util.SendSms;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.po.EhbDataUpload;
import com.yl.soft.po.Smsrecord;
import com.yl.soft.service.EhbDataUploadService;
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

	@Autowired
	private SendEmail sendEmail;
	
	@Autowired
	private EhbDataUploadService ehbDataUploadService;

	/**
	 * 短信验证码过期时间
	 */
	@Value("${custom.sms.time_out}")
	private Integer sms_code_time_out;

	@ApiOperation(value = "发送验证码", notes = "发送验证码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "smstype", value = "短信类型（0：注册，1：登录，2：绑定手机号，3：忘记密码，4：验证原手机号码，5：更换手机号码）", required = true, paramType = "query"),
			@ApiImplicitParam(name = "phone", value = "验证码手机号", required = true, paramType = "query"), })
	@PostMapping("/send/phoneCode")
	public BaseResult phoneCode(
			@NotBlank(message = "-101-请输入正确的手机号") @Pattern(regexp = Constants.PHONE_REG, message = "-101-请输入正确的手机号") String phone,
			Integer smstype) {
		BaseResult r = new BaseResult();
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
				r.setCode(200);
				r.setStartTime(DateUtils.DateToString(new Date(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
				r.setData(val);
				sendSms.sendMsgCode(phone, val, smstype);
				redisUtil.set("I" + phone, val, sms_code_time_out);
			} else {
				r = error(-301, "保存发送验证码记录失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	@ApiOperation(value = "发邮箱附件", notes = "发邮箱附件")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "mail", value = "发送人邮件地址", required = true, paramType = "query"), 
		@ApiImplicitParam(name = "id", value = "附件id，多附件用英文逗号隔开", required = true, paramType = "query"), 
		})
	@PostMapping("/send/mailfile")
	public BaseResult mailfile(@NotBlank(message = "-101-请输入正确的邮箱地址") String mail,String id) {
		if (mail == null) {
			return error();
		}
		try {
			List<Integer> ids = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
			List<URL> attachments=new ArrayList<URL>();
			List<String> finalname=new ArrayList<>();
			for (Integer integer : ids) {
				EhbDataUpload ed=ehbDataUploadService.getById(integer);
				URL filename = new URL(ed.getUpadd());
				attachments.add(filename);
				finalname.add(ed.getTitle());
			}
			sendEmail.sendMails(attachments,finalname, mail);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		return ok2();
	}

	@ApiOperation(value = "发邮箱证码", notes = "发邮箱证码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "mail", value = "发送人邮件地址", required = true, paramType = "query"), })
	@PostMapping("/send/mailCode")
	public BaseResult mailCode(@NotBlank(message = "-101-请输入正确的邮箱地址") String mail) {
		BaseResult r = new BaseResult();
		if (mail == null) {
			return error();
		}
		String val = ProductNumUtil.getRandNum();
		try {
			r.setDesc("已发送至邮箱注意查收");
			r.setCode(200);
			r.setStartTime(DateUtils.DateToString(new Date(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
			r.setData(val);
			sendEmail.sendMail(mail, val);
			redisUtil.set("I" + mail, val, sms_code_time_out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

}
