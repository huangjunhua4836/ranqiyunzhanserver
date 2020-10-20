package com.yl.soft.controller.api;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.po.Appversion;
import com.yl.soft.po.Problem;
import com.yl.soft.service.AppversionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * app版本信息 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-10-20
 */
@Api(tags = "C端模块-版本检测")
@RestController
@RequestMapping("/appversion")
public class AppversionController extends BaseController{

	@Autowired
	public AppversionService appversionService;
	
	@ApiOperation(value = "获取最新一条跟新", notes = "获取最新一条跟新")
	@PostMapping("/getUpdate")
	public BaseResult<Appversion> getUpdate() {
		PageHelper.startPage(1, 1000, "version DESC");
		PageInfo<Appversion> pageInfo = new PageInfo<>(appversionService.list());
		return ok2(pageInfo.getList().get(0));
	}
}

