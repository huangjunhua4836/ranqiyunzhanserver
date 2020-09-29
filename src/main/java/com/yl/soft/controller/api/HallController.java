package com.yl.soft.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yl.soft.controller.base.BaseController;
import com.yl.soft.service.EhbExhibitorService;

import io.swagger.annotations.Api;

@Api(tags = {"C端模块-虚拟展厅"})
@RestController
@RequestMapping("/hall")
public class HallController extends BaseController{

	@Autowired
	public EhbExhibitorService ehbExhibitorService;
	
}
