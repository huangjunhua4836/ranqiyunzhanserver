package com.yl.soft.controller.api;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.SendEmail;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.RegisterAudienceDto;
import com.yl.soft.dto.RegisterExhibitorDto;
import com.yl.soft.dto.app.LabelDto;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.EhbLabel;
import com.yl.soft.service.CrmFileService;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.EhbLabelService;
import io.swagger.annotations.*;
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

@Api(tags = {"C端模块-燃气云展信息完善"})
@RestController
@RequestMapping("/api")
public class RegisterController extends BaseController {
    @Autowired
    private EhbAudienceService ehbAudienceService;
    @Autowired
    private EhbLabelService ehbLabelService;
    @Autowired
    private EhbExhibitorService ehbExhibitorService;
    @Autowired
    private SessionState sessionState;
    @Autowired
    private SendEmail sendEmail;
    @Autowired
    private CrmFileService crmFileService;
    @Autowired
    private RedisService redisService;
    @Value("${custom.uploadPath}")
    private String uploadPath;

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
    public BaseResponse perfectAudience(RegisterAudienceDto registerAudienceDto,String token) {
        String randNum = redisService.get("I"+registerAudienceDto.getMailbox());
        if(!randNum.equals(registerAudienceDto.getEmailverificationcode())){
            return setResultError("验证码错误");
        }
        SessionUser sessionUser = sessionState.getCurrentUser(token);
		EhbAudience ehbAudience = ehbAudienceService.getById(sessionUser.getId());
		if(ehbAudience == null){
            return setResultError("参展人没有注册！");
        }
        if(!StringUtils.isEmpty(registerAudienceDto.getLabelid())){
            List<Integer> labels = JSONArray.parseArray(registerAudienceDto.getLabelid(),Integer.class);
            String temps = JSONArray.toJSONString(labels);
            registerAudienceDto.setLabelid(temps);
        }
        BeanUtil.copyProperties(registerAudienceDto,ehbAudience);
        ehbAudience.setIsdel(false);
        ehbAudience.setUpdatetime(LocalDateTime.now());
        if(ehbAudienceService.updateById(ehbAudience)){
            return setResultSuccess();
        }else{
            return setResultError("保存失败！");
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
    public BaseResponse perfectExhibitor(RegisterExhibitorDto registerExhibitorDto,String token) {
        String randNum = redisService.get("I"+registerExhibitorDto.getMailbox());
        if(!randNum.equals(registerExhibitorDto.getEmailverificationcode())){
//            return setResultError("验证码错误");
        }
        SessionUser sessionUser = sessionState.getCurrentUser(token);
        EhbAudience ehbAudience = ehbAudienceService.getById(sessionUser.getId());
        if(ehbAudience == null){
            return setResultError("参展商没有注册！");
        }
        if(!StringUtils.isEmpty(registerExhibitorDto.getLabelid())){
            List<Integer> labels = JSONArray.parseArray(registerExhibitorDto.getLabelid(),Integer.class);
            String temps = JSONArray.toJSONString(labels);
            registerExhibitorDto.setLabelid(temps);
        }
        EhbExhibitor ehbExhibitor = new EhbExhibitor();
        BeanUtil.copyProperties(registerExhibitorDto,ehbExhibitor);
        ehbExhibitor.setIsdel(false);
        ehbExhibitor.setUpdatetime(LocalDateTime.now());
        ehbExhibitor.setState(2);//审核中
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