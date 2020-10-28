package com.yl.soft.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.util.BaseConv;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.EhbHallDto;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.po.EhbHall;
import com.yl.soft.service.EhbHallService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"C端模块-虚拟展厅"})
@RestController
@RequestMapping("/api")
public class HalljController extends BaseController{
    @Autowired
    private SessionState sessionState;
	
	@Autowired
	private EhbHallService ehbHallService;
	
	@ApiOperation(value = "置顶推荐展厅", notes = "置顶推荐展厅")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
	})
	@PostMapping("/hall")
	public BaseResult<EhbHallDto> hall(String token) {
		return ok2(BaseConv.copy(ehbHallService.lambdaQuery().eq(EhbHall::getIsdel, 0).eq(EhbHall::getRecommend, 1).orderByDesc(EhbHall::getSort).last("LIMIT 1").one(), new EhbHallDto()));
	}
	
	@ApiOperation(value = "浏览", notes = "浏览")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "展商的id", required = true, paramType = "query"),
	})
	@PostMapping("/veiws")
	public BaseResult<EhbHallDto> veiws(String token,String id) {
		if(StringUtils.isEmpty(id)) {
			return error();
		}
		ehbHallService.lambdaUpdate().setSql("views=views+1").eq(EhbHall::getExhibitorid, Integer.parseInt(id)).update();
		return ok2();
	}
	
	@ApiOperation(value = "虚拟展厅列表", notes = "虚拟展厅列表")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "size", value = "一页显示条数", required = true, paramType = "query"),
	})
	@PostMapping("/hallList")
	public ResultItem<List<EhbHallDto>> hallList(String token, Integer page,
			Integer size) {
		PageHelper.startPage(page, size, "sort DESC");
		PageInfo<EhbHall> pageInfo = new PageInfo<>(ehbHallService.lambdaQuery().eq(EhbHall::getIsdel, 0).eq(EhbHall::getRecommend, 0).list());
		List<EhbHallDto> dataList=pageInfo.getList().stream().map(i->{
			EhbHallDto ehbHallDto=new EhbHallDto();
			BaseConv.copy(i, ehbHallDto);
			return ehbHallDto;
		}).collect(Collectors.toList());
		return ok(dataList, pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}
}