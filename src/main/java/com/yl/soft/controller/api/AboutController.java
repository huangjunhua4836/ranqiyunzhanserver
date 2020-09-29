package com.yl.soft.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.po.EhbAbout;
import com.yl.soft.po.EnbOnline;
import com.yl.soft.service.EhbAboutService;
import com.yl.soft.service.EnbOnlineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "C端模块-关于/首页在线招展" })
@RestController
@RequestMapping("/gy")
public class AboutController extends BaseController {

	@Autowired
	public EhbAboutService ehbAboutService;
	
	@Autowired
	public EnbOnlineService enbOnlineService;

	@ApiOperation(value = "获取关于", notes = "获取关于")
	@PostMapping("/getAbout")
	public ResultItem<EhbAbout> getAbout() {
		List<EhbAbout> ehbAbouts = ehbAboutService.lambdaQuery().eq(EhbAbout::getType, 1).list();
		return ok(ehbAbouts.size() > 0 ? ehbAbouts.get(0) : null);
	}
	
	@ApiOperation(value = "在线招展", notes = "在线招展")
	@PostMapping("/getOnline")
	public ResultItem<EnbOnline> getOnline() {
		List<EnbOnline> ehbAbouts = enbOnlineService.lambdaQuery().eq(EnbOnline::getType, 1).list();
		return ok(ehbAbouts.size() > 0 ? ehbAbouts.get(0) : null);
	}
	
}
