package com.yl.soft.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.EhbExhibitorDto;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.EhbUseraction;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.EhbUseractionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"C端模块-在线展厅"})
@RestController
@RequestMapping("/fn")
public class FengNiaoController extends BaseController{

	@Autowired
	private EhbExhibitorService ehbExhibitorService;
	
	@Autowired
	private EhbAudienceService EhbAudienceService;
	@Autowired
	private SessionState sessionState;
	
	@Autowired
	private EhbUseractionService ehbUseractionService;
	
	
	@ApiOperation(value = "点击展位弹出企业名称logo等信息", notes = "点击展位弹出企业名称logo等信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "fid", value = "蜂鸟云Fid", required = true, paramType = "query"), 
	})
	@PostMapping("/registrationDes")
	public ResultItem<EhbExhibitorDto> registrationDes(String fid) {
		EhbExhibitor eh = ehbExhibitorService.lambdaQuery().eq(EhbExhibitor::getFid, fid).one();
		EhbAudience user=EhbAudienceService.lambdaQuery().eq(EhbAudience::getBopie, eh.getId()).last("LIMIT 1").one();
		return ok(EhbExhibitorDto.of(eh,user));
	}
	
	@ApiOperation(value = "根据企业ID查询企业详情", notes = "根据企业ID查询企业详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "企业id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "token", value = "token", required = false, paramType = "query"),
	})
	@PostMapping("/getComp")
	public ResultItem<EhbExhibitorDto> getComp(String id,String token) {
		EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(id);
		EhbAudience user=EhbAudienceService.lambdaQuery().eq(EhbAudience::getBopie, ehbExhibitor.getId()).last("LIMIT 1").one();
		EhbExhibitorDto ed= EhbExhibitorDto.of(ehbExhibitor,user);
		if(!StringUtils.isEmpty(token)) {
			SessionUser sessionUser = sessionState.getCurrentUser(token);
			EhbUseraction ehbUseraction=ehbUseractionService.lambdaQuery().eq(EhbUseraction::getUserid, sessionUser.getId()).eq(EhbUseraction::getRelateid, id).eq(EhbUseraction::getActivetype, 1).last("LIMIT 1").one();
			ed.setIsColl(ehbUseraction==null?0:1);
		}else {
			ed.setIsColl(0);
		}
		return ok(ed);
	}
}
