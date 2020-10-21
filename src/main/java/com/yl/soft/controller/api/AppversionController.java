package com.yl.soft.controller.api;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.po.Appversion;
import com.yl.soft.service.AppversionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
	
	@ApiOperation(value = "获取最新一条更新", notes = "获取最新一条更新")
	@PostMapping("/getUpdate")
	public BaseResult<Appversion> getUpdate() {
		PageHelper.startPage(1, 1000, "version DESC");
		PageInfo<Appversion> pageInfo = new PageInfo<>(appversionService.list());
		List list = pageInfo.getList();
		System.out.println(list);
		System.out.println(list.size());
		return ok2(pageInfo.getList().size() == 0?null:pageInfo.getList().get(0));
	}
}

