package com.yl.soft.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.EhbExhibitorDto;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.service.EhbExhibitorService;

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
	
	
	@ApiOperation(value = "点击展位弹出企业名称logo等信息", notes = "点击展位弹出企业名称logo等信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "fid", value = "蜂鸟云Fid", required = true, paramType = "query"), 
	})
	@PostMapping("/registrationDes")
	public ResultItem<EhbExhibitorDto> registrationDes(String fid) {
		EhbExhibitor eh = ehbExhibitorService.lambdaQuery().eq(EhbExhibitor::getFid, fid).one();
		return ok(EhbExhibitorDto.of(eh));
	}
	
	@ApiOperation(value = "根据企业ID查询企业详情", notes = "根据企业ID查询企业详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "企业id", required = true, paramType = "query"), })
	@PostMapping("/getComp")
	public ResultItem<EhbExhibitorDto> getComp(String id) {
		EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(id);
		return ok(EhbExhibitorDto.of(ehbExhibitor));
	}
}
