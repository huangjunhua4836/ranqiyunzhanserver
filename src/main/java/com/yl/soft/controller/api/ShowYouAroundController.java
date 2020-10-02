package com.yl.soft.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.jsoup.select.Collector;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.util.HWPlayFlowAuthUtil;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.EhbLiveBroadcastDto;
import com.yl.soft.dto.EhbLiveBroadcastListDto;
import com.yl.soft.dto.EhbWonderfulAtlasDto;
import com.yl.soft.dto.EhbWonderfulVideoDto;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.EhbLiveBroadcast;
import com.yl.soft.po.EhbWonderfulAtlas;
import com.yl.soft.po.EhbWonderfulVideo;
import com.yl.soft.service.EhbLiveBroadcastService;
import com.yl.soft.service.EhbWonderfulAtlasService;
import com.yl.soft.service.EhbWonderfulVideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "C端模块-带你逛展" })
@RestController
@RequestMapping("/api")
public class ShowYouAroundController extends BaseController {

	@Autowired
	private EhbLiveBroadcastService ehbLiveBroadcastService;
	
	@Autowired
	private EhbWonderfulVideoService ehbWonderfulVideoService;
	
	@Autowired
	private EhbWonderfulAtlasService ehbWonderfulAtlasService;
	
	@Autowired
	private HWPlayFlowAuthUtil hWPlayFlowAuthUtil;

	@ApiOperation(value = "直播/回放列表", notes = "直播/回放列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "size", value = "一页显示条数", required = true, paramType = "query"), })
	@PostMapping("/live_list")
	public ResultItem<List<EhbLiveBroadcastListDto>> live_list(@NotBlank(message = "token不能为空") String token, Integer page,
			Integer size) {
		PageHelper.startPage(page, size, "sort DESC");
		PageInfo<EhbLiveBroadcastListDto> pageInfo = new PageInfo<>(
				ehbLiveBroadcastService.lambdaQuery().eq(EhbLiveBroadcast::getIsdel, 1).list().stream().map(i->{
					EhbLiveBroadcastListDto ehbLiveBroadcastListDto=new EhbLiveBroadcastListDto();
					BeanUtils.copyProperties(i, ehbLiveBroadcastListDto);
					return ehbLiveBroadcastListDto;
				}).collect(Collectors.toList()));
		return ok(pageInfo.getList(), pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}
	
	@ApiOperation(value = "进入直播间", notes = "进入直播间")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "直播id", required = true, paramType = "query"),})
	@PostMapping("/live")
	public ResultItem<EhbLiveBroadcastDto> live(@NotBlank(message = "直播ID不能为空") String id) {
		EhbLiveBroadcast ehbLiveBroadcast=ehbLiveBroadcastService.getById(id);
		EhbLiveBroadcastDto ehbLiveBroadcastDto = new EhbLiveBroadcastDto();
		BeanUtils.copyProperties(ehbLiveBroadcast, ehbLiveBroadcastDto);
//		ehbLiveBroadcastDto.setPullFlowUrl(hWPlayFlowAuthUtil.liveUrl(ehbLiveBroadcast.getFlowName()));
		return ok(ehbLiveBroadcastDto);
	}
	
	@ApiOperation(value = "精彩小视频列表", notes = "精彩小视频列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "size", value = "一页显示条数", required = true, paramType = "query"), })
	@PostMapping("/wonderful_video")
	public ResultItem<List<EhbWonderfulVideoDto>> wonderful_video(@NotBlank(message = "token不能为空") String token, Integer page,
			Integer size) {
		PageHelper.startPage(page, size, "sort DESC");
		PageInfo<EhbWonderfulVideoDto> pageInfo = new PageInfo<>(
				ehbWonderfulVideoService.lambdaQuery().eq(EhbWonderfulVideo::getIsdel, 1).list().stream().map(i->{
					EhbWonderfulVideoDto ehbWonderfulVideoDto=new EhbWonderfulVideoDto();
					BeanUtils.copyProperties(i, ehbWonderfulVideoDto);
					return ehbWonderfulVideoDto;
				}).collect(Collectors.toList()));
		return ok(pageInfo.getList(), pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}
	
	
	@ApiOperation(value = "精彩图集列表", notes = "精彩图集列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", value = "登陆标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "size", value = "一页显示条数", required = true, paramType = "query"), })
	@PostMapping("/wonderful_tlas")
	public ResultItem<List<EhbWonderfulAtlasDto>> wonderful_tlas(@NotBlank(message = "token不能为空") String token, Integer page,
			Integer size) {
		PageHelper.startPage(page, size, "sort DESC");
		PageInfo<EhbWonderfulAtlasDto> pageInfo = new PageInfo<>(
				ehbWonderfulAtlasService.lambdaQuery().eq(EhbWonderfulAtlas::getIsdel, 1).list().stream().map(i->{
					EhbWonderfulAtlasDto ehbWonderfulAtlasDto=new EhbWonderfulAtlasDto();
					BeanUtils.copyProperties(i, ehbWonderfulAtlasDto);
					return ehbWonderfulAtlasDto;
				}).collect(Collectors.toList()));
		return ok(pageInfo.getList(), pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}

}
