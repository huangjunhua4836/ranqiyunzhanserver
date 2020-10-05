package com.yl.soft.controller.api;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.util.BaseConv;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.EhbOpportunityDto;
import com.yl.soft.dto.MessageDto;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbHall;
import com.yl.soft.po.EhbLabel;
import com.yl.soft.po.EhbOpportunity;
import com.yl.soft.po.Message;
import com.yl.soft.service.MessageService;

import cn.hutool.core.util.ReUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 消息 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-10-05
 */
@Api(tags = {"C端模块-消息"})
@RestController
@RequestMapping("/")
public class MessageController extends BaseController{

    @Autowired
    private SessionState sessionState;
    
    @Autowired
    private MessageService messageService;
	
	@ApiOperation(value = "查询我的消息列表", notes = "查询我的消息列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "size", value = "一页显示条数", required = true, paramType = "query"), })
	@PostMapping("/api/messageList")
	public ResultItem<List<MessageDto>> messageList(String token, Integer page,Integer size) {
		SessionUser sessioner = sessionState.getCurrentUser(token);
		PageHelper.startPage(page, size, "createtime DESC");
		PageInfo<Message> pageInfo = new PageInfo<>(messageService.lambdaQuery().eq(Message::getIsdel, 0).eq(Message::getRuid, sessioner.getId()).list());
		List<MessageDto> dataList=pageInfo.getList().stream().map(i->{
			MessageDto messageDto=new MessageDto();
			return BaseConv.copy(i, messageDto);
		}).collect(Collectors.toList());
		return ok(dataList, pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}
	
	@ApiOperation(value = "处理未读消息为已读消息", notes = "处理未读消息为已读消息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
		@ApiImplicitParam(name = "id", value = "消息id，多个id用英文逗号分隔", required = true, paramType = "query") 
	})
	@PostMapping("/api/upMessage")
	public BaseResult upMessage(String token,String id) {
		messageService.lambdaUpdate().setSql("status=1").in(Message::getId, Arrays.asList(id.split(","))).update();
		return ok2();
	}
	
	@ApiOperation(value = "获取当前未读消息总数量", notes = "获取当前未读消息总数量")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query") })
	@PostMapping("/api/getMessage")
	public BaseResult getMessage(String token) {
		SessionUser sessioner = sessionState.getCurrentUser(token);
		return ok2(messageService.lambdaQuery().eq(Message::getRuid, sessioner.getId()).eq(Message::getStatus, 0).count());
	}
	
}

