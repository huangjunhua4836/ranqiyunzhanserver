package com.yl.soft.controller.api;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.util.BaseConv;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.EhbOpportunityDto;
import com.yl.soft.dto.app.ArticleDto;
import com.yl.soft.dto.app.EhbCommentDto;
import com.yl.soft.dto.app.OpportunityDetailsDto;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.ResultItem;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.*;
import com.yl.soft.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"C端模块-H5详情"})
@RestController
@RequestMapping("/")
public class HtmlDetails extends BaseController {

    @Autowired
    private EhbArticleService ehbArticleService;
    
    @Autowired
    private EhbOpportunityService ehbOpportunityService;
    
    @Autowired
    private EhbAudienceService ehbAudienceService;
    
    @Autowired
    private EhbCommentService ehbCommentService;
    
    @Autowired
    private EhbUseractionService ehbUseractionService;

    @Autowired
    private EhbExhibitorService ehbExhibitorService;
    @Autowired
    private EhbLabelService ehbLabelService;

    @Autowired
    private SessionState sessionState;
	
	@ApiOperation(value = "资讯详情", notes = "H5页面资讯详情")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "id", value = "咨询id", required = true, paramType = "query"),
	})
	@GetMapping("/zxDetails")
	public BaseResult<ArticleDto> zxDetails(Integer id,String token) {
		EhbArticle ehbArticle=ehbArticleService.getById(id);
		
		SessionUser sessionUser = sessionState.getCurrentUser(token);
		EhbUseraction isZxZan=ehbUseractionService.lambdaQuery()
				.eq(null!=sessionUser,EhbUseraction::getUserid, sessionUser.getId())
				.eq(EhbUseraction::getRelateid, ehbArticle.getId()).eq(EhbUseraction::getActivetype, 2).eq(EhbUseraction::getType, 3).last("LIMIT 1").one();
		
		EhbUseraction isSc=ehbUseractionService.lambdaQuery()
				.eq(null!=sessionUser,EhbUseraction::getUserid, sessionUser.getId())
				.eq(EhbUseraction::getRelateid, ehbArticle.getId()).eq(EhbUseraction::getActivetype, 1).eq(EhbUseraction::getType, 3).last("LIMIT 1").one();

		ArticleDto articleDto=BaseConv.copy(ehbArticle, new ArticleDto());
		articleDto.setIsZan(isZxZan!=null?1:0);//是否点赞
		articleDto.setIsSCZx(isSc!=null?1:0);//是否收藏
		return ok(articleDto);
	}
	
	
	@ApiOperation(value = "商机/商品详情", notes = "H5页面商机/商品详情")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "id", value = "商机/商机id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "token", value = "授权标识", required = false, paramType = "query")
	})
	@GetMapping("/sjDetails")
	public BaseResult<OpportunityDetailsDto> sjDetails(Integer id, String token) {
		EhbOpportunity ehbOpportunity=ehbOpportunityService.getById(id);
		SessionUser sessionUser = sessionState.getCurrentUser(token);
		
		EhbUseraction isSj=ehbUseractionService.lambdaQuery()
			.eq(null!=sessionUser,EhbUseraction::getUserid, sessionUser.getId())
			.eq(EhbUseraction::getRelateid, ehbOpportunity.getId()).eq(EhbUseraction::getType, 2).eq(EhbUseraction::getActivetype, 1).last("LIMIT 1").one();
		
		EhbUseraction isSp=ehbUseractionService.lambdaQuery()
				.eq(null!=sessionUser,EhbUseraction::getUserid, sessionUser.getId())
				.eq(EhbUseraction::getRelateid, ehbOpportunity.getId()).eq(EhbUseraction::getType, 4).eq(EhbUseraction::getActivetype, 1).last("LIMIT 1").one();
		
		EhbUseraction isSjZan=ehbUseractionService.lambdaQuery()
				.eq(null!=sessionUser,EhbUseraction::getUserid, sessionUser.getId())
				.eq(EhbUseraction::getRelateid, ehbOpportunity.getId()).eq(EhbUseraction::getActivetype, 2).eq(EhbUseraction::getType, 2).last("LIMIT 1").one();
		
		EhbUseraction isSpZan=ehbUseractionService.lambdaQuery()
				.eq(null!=sessionUser,EhbUseraction::getUserid, sessionUser.getId())
				.eq(EhbUseraction::getRelateid, ehbOpportunity.getId()).eq(EhbUseraction::getActivetype, 2).eq(EhbUseraction::getType, 4).last("LIMIT 1").one();
		
		
		EhbUseraction isShouc= ehbUseractionService.lambdaQuery()
			.eq(null!=sessionUser,EhbUseraction::getUserid, sessionUser.getId())
			.eq(EhbUseraction::getRelateid, ehbOpportunity.getExhibitorid()).eq(EhbUseraction::getActivetype, 1).eq(EhbUseraction::getType, 1).last("LIMIT 1").one();
		EhbOpportunityDto ehbOpportunityDto=BaseConv.copy(ehbOpportunity, new EhbOpportunityDto());
		ehbOpportunityDto.setIsSCqy(null!=isShouc?1:0);
		ehbOpportunityDto.setIsSCsj(null!=isSj?1:0);
		ehbOpportunityDto.setIsSCsp(null!=isSp?1:0);
		ehbOpportunityDto.setIsSjZan(null!=isSjZan?1:0);
		ehbOpportunityDto.setIsSpZan(null!=isSpZan?1:0);

		//展商信息
		EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(ehbOpportunity.getExhibitorid());
		ehbOpportunityDto.setExhibitorname(ehbExhibitor.getEnterprisename());
		ehbOpportunityDto.setAttestation(ehbExhibitor.getState());
		List<String> labelList = new ArrayList<>();
		if(!StringUtils.isEmpty(ehbExhibitor.getLabelid())){
			QueryWrapper<EhbLabel> ehbLabelQueryWrapper = new QueryWrapper<>();
			ehbLabelQueryWrapper.in(!StringUtils.isEmpty(ehbExhibitor.getLabelid()),"id",JSONArray.parseArray(ehbExhibitor.getLabelid(),Integer.class));
			List<EhbLabel> ehbLabels = ehbLabelService.list(ehbLabelQueryWrapper);
			for(EhbLabel ehbLabel : ehbLabels){
				labelList.add(ehbLabel.getName());
			}
		}
		OpportunityDetailsDto opportunityDetailsDto = new OpportunityDetailsDto();
		BeanUtil.copyProperties(ehbOpportunityDto,opportunityDetailsDto);
		//设置标签
		opportunityDetailsDto.setLabelStrings(labelList);
		//设置发布者id
		EhbAudience ehbAudience = ehbAudienceService.lambdaQuery().eq(EhbAudience::getBopie,ehbExhibitor.getId()).last("limit 1").one();
		opportunityDetailsDto.setUserid(ehbAudience.getId());

		return ok(opportunityDetailsDto);
	}
	
	@ApiOperation(value = "查询评论信息列表", notes = "查询评论信息列表")
	@ApiImplicitParams({ 
		    @ApiImplicitParam(name = "type", value = "1：企业   2：商机  3：资讯", required = true, paramType = "query"),
		    @ApiImplicitParam(name = "id", value = "评论企业，商机，商品的id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "page", value = "分页当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "size", value = "一页显示条数", required = true, paramType = "query"),
	})
	@GetMapping("/commentList")
	public ResultItem<List<EhbCommentDto>> commentList(Integer page,
			Integer size,Integer type,Integer id) {
		
		PageHelper.startPage(page, size, "createtime DESC");
		PageInfo<EhbComment> pageInfo = new PageInfo<>(ehbCommentService.lambdaQuery()
				.eq(EhbComment::getIsdel, 0)
				.eq(null!=type,EhbComment::getType, type)
				.eq(null!=id,EhbComment::getRelateid, id).list());
		List<EhbCommentDto> dataList=pageInfo.getList().stream().map(i->{
			EhbCommentDto ehbCommentDto=new EhbCommentDto();
			BaseConv.copy(i, ehbCommentDto);
			ehbCommentDto.setUser(ehbAudienceService.getById(i.getUserid()));
			return ehbCommentDto;
		}).collect(Collectors.toList());
		return ok(dataList, pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), size);
	}
	
	
	@ApiOperation(value = "发评论", notes = "发评论")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "type", value = "1：企业   2：商机  3：资讯  4：商品", required = true, paramType = "query"),
			@ApiImplicitParam(name = "content", value = "评论内容", required = true, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "评论作品的id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "token", value = "授权标识", required = true, paramType = "query"),
	})
	@PostMapping("/api/addComment")
	public BaseResult addComment(Integer id,Integer type,String content,String token) {
		SessionUser sessionUser = sessionState.getCurrentUser(token);
		EhbComment ehbComment = new EhbComment();
		ehbComment.setContent(content);
		ehbComment.setCreatetime(LocalDateTime.now());
		ehbComment.setIsdel(0);
		ehbComment.setUserid(sessionUser.getId());
		ehbComment.setState(1);
		ehbComment.setType(type);
		ehbComment.setRelateid(id);
		ehbCommentService.save(ehbComment);
		switch (type) {
		case 1:
			
			break;
		case 2: //评论商机
			ehbOpportunityService.lambdaUpdate().setSql("countcomment=countcomment+1").eq(EhbOpportunity::getId, id).update();
			break;
		case 3:  //评论资讯
			ehbArticleService.lambdaUpdate().setSql("countcomment=countcomment+1").eq(EhbArticle::getId, id).update();
			break;
		case 4:  //评论商品
			ehbOpportunityService.lambdaUpdate().setSql("countcomment=countcomment+1").eq(EhbOpportunity::getId, id).update();
			break;
		default:
			break;
		}
		return ok(ehbComment.getId());
	}
	
	@ApiOperation(value = "删除我发的评论", notes = "删除我发的评论")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "id", value = "评论作品的id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "token", value = "授权标识", required = true, paramType = "query"),
	})
	@PostMapping("/api/deleComment")
	public BaseResult deleComment(Integer id,String token) {
		SessionUser sessionUser = sessionState.getCurrentUser(token);
		if(null==id) {
			return error(-100,"系统繁忙请稍后重试");
		}
		EhbComment ehbComment=ehbCommentService.lambdaQuery().eq(EhbComment::getUserid, sessionUser.getId()).eq(EhbComment::getId, id).last("LIMIT 1").one();
		if(null==ehbComment) {
			return error(-101,"该评论不存在");
		}
		switch (ehbComment.getType()) {
		case 2: //评论商机
			ehbOpportunityService.lambdaUpdate().setSql("countcomment=countcomment-1").eq(EhbOpportunity::getId, id).update();
			break;
		case 3:  //评论资讯
			ehbArticleService.lambdaUpdate().setSql("countcomment=countcomment-1").eq(EhbArticle::getId, id).update();
			break;
		case 4:  //评论商品
			ehbOpportunityService.lambdaUpdate().setSql("countcomment=countcomment-1").eq(EhbOpportunity::getId, id).update();
			break;
		}
		ehbCommentService.removeById(ehbComment.getId());
		return ok(ehbComment.getId());
	}
}