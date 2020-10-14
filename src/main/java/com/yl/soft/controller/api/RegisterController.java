package com.yl.soft.controller.api;

import cn.hutool.core.bean.BeanUtil;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.IDUtils;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.RegisterAudienceDto;
import com.yl.soft.dto.RegisterExhibitorDto;
import com.yl.soft.dto.app.LabelDto;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.EhbAboutus;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.EhbLabel;
import com.yl.soft.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Api(tags = {"C端模块-燃气云展信息完善"})
@RestController
@RequestMapping("/api")
@Slf4j
public class RegisterController extends BaseController {
    @Autowired
    private EhbAudienceService ehbAudienceService;
    @Autowired
    private EhbLabelService ehbLabelService;
    @Autowired
    private EhbExhibitorService ehbExhibitorService;
    @Autowired
    private EhbAboutusService ehbAboutusService;
    @Autowired
    private SessionState sessionState;
    @Autowired
    private RedisService redisService;
    

    /**
     * 参展用户补充信息提交接口
     * @return
     */
    @ApiOperation(value = "参展用户补充信息提交接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/perfectAudience")
    public BaseResult perfectAudience(RegisterAudienceDto registerAudienceDto,String token) {
        String randNum = redisService.get("I"+registerAudienceDto.getMailbox());
        if(!randNum.equals(registerAudienceDto.getEmailverificationcode())){
            return error(-100,"验证码错误");
        }
        SessionUser sessionUser = sessionState.getCurrentUser(token);
		EhbAudience ehbAudience = ehbAudienceService.getById(sessionUser.getId());
		if(ehbAudience == null){
            return error(-100,"参展人没有注册！");
        }
		if(StringUtils.isEmpty(registerAudienceDto.getName())) {
			 return error(-100,"请输入您的姓名！");
		}
		
		if(registerAudienceDto.getName().length()>30) {
			 return error(-100,"请输入一个正确的姓名！");
		}
		
		if(StringUtils.isEmpty(registerAudienceDto.getPhone())) {
			 return error(-100,"请输入手机号！");
		}
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(registerAudienceDto.getPhone().length() != 11){
        	return error(-100,"手机号应为11位数！");
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(registerAudienceDto.getPhone());
            boolean isMatch = m.matches();
            if(!isMatch){
            	return error(-100,"请输入一个正确的手机号");
            }
        }
		
        if(StringUtils.isEmpty(registerAudienceDto.getEnterprise())) {
        	return error(-100,"请输入您的企业名称！");
        }
        
        if(registerAudienceDto.getEnterprise().length()>50) {
        	return error(-100,"请输入一个正确的企业名称！");
        }
        
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regexmail = Pattern.compile(check);
        Matcher matcher = regexmail.matcher(registerAudienceDto.getMailbox());
        boolean isMatchmail = matcher.matches();
        
        if(!isMatchmail) {
        	return error(-100,"请输入一个正确的邮箱地址");
        }
        
        
        
		
        BeanUtil.copyProperties(registerAudienceDto,ehbAudience);
        ehbAudience.setIsdel(false);
        ehbAudience.setUpdatetime(LocalDateTime.now());

        //参展标签
        String str[] = registerAudienceDto.getLabelid().split(",");
        List<Integer> labs = new ArrayList<>();
        if(str.length > 0){
            for(String temp : str){
                labs.add(Integer.valueOf(temp));
            }
        }
        ehbAudience.setLabelid(JSONArray.toJSONString(labs));

        if(ehbAudienceService.updateById(ehbAudience)){
            return ok2();
        }else{
            return error(-100,"保存失败！");
        }
    }

    public static boolean isWeb(final String str) {
		String url = "http:/klsfnklnklwnl.csfwfwn.cn?1231=sjkfjkf&sfwfw=";
		String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
		Pattern pattern = Pattern.compile(regex);
		if (pattern.matcher(url).matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isMobile(final String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	public static boolean isEmail(String email) {
		if (null == email || "".equals(email)) {
			return false;
		}
		String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(regEx1);
		Matcher m = p.matcher(email);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPhone(final String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}
    
    /**
     * 展商认证信息提交接口
     * @return
     */
    @ApiOperation(value = "展商认证信息提交接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/perfectExhibitor")
    public BaseResponse perfectExhibitor(RegisterExhibitorDto registerExhibitorDto,String token) {
        String randNum = redisService.get("I"+registerExhibitorDto.getMailbox());
        if(!randNum.equals(registerExhibitorDto.getEmailverificationcode())){
            return setResultError("验证码错误");
        }
        SessionUser sessionUser = sessionState.getCurrentUser(token);
        EhbAudience ehbAudience = ehbAudienceService.getById(sessionUser.getId());
        if(ehbAudience == null){
            return setResultError("参展商没有注册！");
        }
//        if(StringUtils.isEmpty(registerExhibitorDto.getEnterprisename())) {
//        	return setResultError("请输入所属企业");
//        }
//        if(StringUtils.isEmpty(registerExhibitorDto.getName())) {
//        	return setResultError("请输入管理员");
//        }
//        if(StringUtils.isEmpty(registerExhibitorDto.getIdcard())) {
//        	return setResultError("请输入管理员身份证号");
//        }
//        
//        if(!IDUtils.isIDNumber(registerExhibitorDto.getIdcard())) {
//        	return setResultError("请输入一个正确的身份证号码");
//        }
//        
//        if(!isMobile(registerExhibitorDto.getPhone())) {
//        	return setResultError("请输入正确的手机号");
//        }
//        
//        if(!isPhone(registerExhibitorDto.getTel())) {
//        	return setResultError("请输入正确的电话号码");
//        }
        
        EhbExhibitor ehbExhibitor = new EhbExhibitor();
        BeanUtil.copyProperties(registerExhibitorDto,ehbExhibitor);
        ehbExhibitor.setIsdel(false);
        ehbExhibitor.setUpdatetime(LocalDateTime.now());
        ehbExhibitor.setState(2);//审核中

        //展商标签
        String str[] = registerExhibitorDto.getLabelid().split(",");
        List<Integer> labs = new ArrayList<>();
        if(str.length > 0){
            for(String temp : str){
                labs.add(Integer.valueOf(temp));
            }
        }
        ehbExhibitor.setLabelid(JSONArray.toJSONString(labs));

        if(ehbExhibitorService.saveExhibitor(ehbAudience,ehbExhibitor)){
            return setResultSuccess();
        }else{
            return setResultError("保存失败！");
        }
    }

    /**
     * 标签列表接口
     * @return
     */
    @ApiOperation(value = "标签列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个标签id,逗号隔开",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/listLabel")
    public BaseResponse<List<LabelDto>> listLabel(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        QueryWrapper<EhbLabel> ehbLabelQueryWrapper = new QueryWrapper<>();
        ehbLabelQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        ehbLabelQueryWrapper.orderByDesc("createtime");
        ehbLabelQueryWrapper.in(!StringUtils.isEmpty(paramMap.get("ids")),"id",Arrays.asList(paramMap.get("ids").toString().split(",")));
        List<EhbLabel> ehbLabels = ehbLabelService.list(ehbLabelQueryWrapper);
        List<LabelDto> labelDtos = new ArrayList<>();
        for(EhbLabel ehbLabel : ehbLabels){
            labelDtos.add(LabelDto.of(ehbLabel));
        }
        return setResultSuccess(labelDtos);
    }

    /**
     * 用户协议接口
     * @return
     */
    @ApiOperation(value = "用户协议接口")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/userAgreement")
    public BaseResponse userAgreement(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        QueryWrapper<EhbAboutus> ehbAboutusQueryWrapper = new QueryWrapper<>();
        ehbAboutusQueryWrapper.last("limit 1");
        EhbAboutus ehbAboutus = ehbAboutusService.getOne(ehbAboutusQueryWrapper);
        return setResultSuccess(ehbAboutus.getUseragr());
    }

//    /**
//     * 获取邮箱验证码
//     * @param paramMap
//     * @return
//     */
//    @ApiOperation(value = "获取邮箱验证码", notes = "获取邮箱验证码")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "mailbox", value = "邮箱",paramType = "query",required = true)
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功")
//            ,@ApiResponse(code = 401, message = "token为空！")
//            ,@ApiResponse(code = 402, message = "token失效！")
//            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
//            ,@ApiResponse(code = -1, message = "系统异常")
//    })
//    @PostMapping("/emailverificationcode")
//    public BaseResponse<EhbDataUpload> emailverificationcode(@ApiParam(hidden = true) @RequestParam Map paramMap) {
//        if(StringUtils.isEmpty(paramMap.get("mailbox"))){
//            return setResultError("邮箱为空！");
//        }
//        String randNum = ProductNumUtil.getRandNum();
//        boolean b = sendEmail.sendMail(paramMap.get("mailbox").toString(),randNum);
//        if(!b){
//            return setResultError("邮件发送失败！");
//        }
//        boolean flag = redisService.set(randNum,randNum,60*30);
//        if(flag){
//            return setResultSuccess("验证码已发送到邮箱："+paramMap.get("mailbox"));
//        }else{
//            return setResultError("邮件缓存失败！");
//        }
//    }
}