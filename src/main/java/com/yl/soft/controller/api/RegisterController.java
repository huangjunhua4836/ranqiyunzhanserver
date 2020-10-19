package com.yl.soft.controller.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.redis.RedisService;
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
import com.yl.soft.service.EhbAboutusService;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.EhbLabelService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
			 return error(-100,"姓名大于30字！");
		}
		registerAudienceDto.setPhone(null);//注册手机号不能修改
//		if(StringUtils.isEmpty(registerAudienceDto.getPhone())){
//            return error(-100,"手机号为空！");
//        }
//        if(!registerAudienceDto.getPhone().matches("^1[0-9]{10}$")){
//            return error(-100,"请输入一个正确的手机号");
//        }
        if(StringUtils.isEmpty(registerAudienceDto.getEnterprise())) {
        	return error(-100,"请输入您的企业名称！");
        }
        if(registerAudienceDto.getEnterprise().length()>50) {
        	return error(-100,"企业名称大于50字！");
        }
        if(StringUtils.isEmpty(registerAudienceDto.getMailbox())){
            return error(-100,"邮箱为空！");
        }
//        if(!registerAudienceDto.getMailbox().matches("^([a-zA-Z]|[0-9])(\\w|\\-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$")){
//            return error(-100,"请输入一个正确的邮箱地址");
//        }

        BeanUtil.copyProperties(registerAudienceDto,ehbAudience,new CopyOptions().ignoreNullValue());
        ehbAudience.setEnabled(1);
        ehbAudience.setIsdel(false);
        ehbAudience.setUpdatetime(LocalDateTime.now());

        //参展标签
        if(!StringUtils.isEmpty(registerAudienceDto.getLabelid())){
            String str[] = registerAudienceDto.getLabelid().split(",");
            List<Integer> labs = new ArrayList<>();
            if(str.length > 0){
                for(String temp : str){
                    labs.add(Integer.valueOf(temp));
                }
            }
            ehbAudience.setLabelid(JSONArray.toJSONString(labs));
        }

        if(ehbAudienceService.updateById(ehbAudience)){
            return ok2();
        }else{
            return error(-100,"保存失败！");
        }
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
    public BaseResult perfectExhibitor(RegisterExhibitorDto registerExhibitorDto,String token) {
        String randNum = redisService.get("I"+registerExhibitorDto.getMailbox());
        if(!randNum.equals(registerExhibitorDto.getEmailverificationcode())){
            return error(-100,"验证码错误");
        }
        SessionUser sessionUser = sessionState.getCurrentUser(token);
        EhbAudience ehbAudience = ehbAudienceService.getById(sessionUser.getId());
        if(ehbAudience == null){
            return error(-100,"参展商没有注册！");
        }
        if(StringUtils.isEmpty(registerExhibitorDto.getEnterprisename())){
            return error(-100,"企业名称为空！");
        }
        if(registerExhibitorDto.getEnterprisename().length()>30){
            return error(-100,"企业名称大于50字！");
        }
        if(StringUtils.isEmpty(registerExhibitorDto.getName())){
            return error(-100,"企业管理人为空！");
        }
        if(registerExhibitorDto.getName().length()>30){
            return error(-100,"企业名称大于30字！");
        }
        if(StringUtils.isEmpty(registerExhibitorDto.getIdcard())){
            return error(-100,"管理人身份证不能为空！");
        }
        if(!registerExhibitorDto.getIdcard().matches("^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$")){
            return error(-100,"请输入一个正确的身份证！");
        }
        if(StringUtils.isEmpty(registerExhibitorDto.getPhone())){
            return error(-100,"管理人手机号为空！");
        }
        if(!registerExhibitorDto.getPhone().matches("^1[0-9]{10}$")){
            return error(-100,"请输入一个正确的管理人手机号！");
        }
        if(StringUtils.isEmpty(registerExhibitorDto.getTel())){
            return error(-100,"座机号为空！");
        }
        if(StringUtils.isEmpty(registerExhibitorDto.getMailbox())){
            return error(-100,"邮箱为空！");
        }
//        if(!registerExhibitorDto.getMailbox().matches("^([a-zA-Z]|[0-9])(\\w|\\-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$")){
//            return error(-100,"请输入一个正确的邮箱地址");
//        }
        if(StringUtils.isEmpty(registerExhibitorDto.getBusinesslicense())){
            return error(-100,"营业执照图片地址为空！");
        }
        if(StringUtils.isEmpty(registerExhibitorDto.getCredentials())){
            return error(-100,"营业执照图片地址为空！");
        }
        EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(ehbAudience.getBopie());
        BeanUtil.copyProperties(registerExhibitorDto,ehbExhibitor);
        ehbExhibitor.setIsdel(false);
        ehbExhibitor.setUpdatetime(LocalDateTime.now());
        ehbExhibitor.setState(2);//审核中
        ehbExhibitor.setTelphone(ehbExhibitor.getTel());
        ehbExhibitor.setCreatetime(LocalDateTime.now());

        //展商标签
        if(!StringUtils.isEmpty(registerExhibitorDto.getLabelid())){
            String str[] = registerExhibitorDto.getLabelid().split(",");
            List<Integer> labs = new ArrayList<>();
            if(str.length > 0){
                for(String temp : str){
                    labs.add(Integer.valueOf(temp));
                }
            }
            ehbExhibitor.setLabelid(JSONArray.toJSONString(labs));
        }
        if(ehbExhibitorService.updateById(ehbExhibitor)){
            return ok2();
        }else{
            return error(-100,"保存失败！");
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


    private static boolean isWeb(final String str) {
        String url = "http:/klsfnklnklwnl.csfwfwn.cn?1231=sjkfjkf&sfwfw=";
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(url).matches()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    private static boolean isEmail(String email) {
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

    private static boolean isPhone(final String str) {
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