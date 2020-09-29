package com.yl.soft.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.app.ArticleDto;
import com.yl.soft.dto.app.ExhibitorDto;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.*;
import com.yl.soft.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Api(tags = {"C端模块-首页"})
@RestController
@RequestMapping("/api")
public class IndexController extends BaseController {
    @Autowired
    private EhbOpportunityService ehbOpportunityService;
    @Autowired
    private EhbExhibitorService ehbExhibitorService;
    @Autowired
    private EhbArticleService ehbArticleService;
    @Autowired
    private EhbBannerService ehbBannerService;
    @Autowired
    private EhbGuestService ehbGuestService;
    @Autowired
    private SessionState sessionState;


    /**
     * 推荐展商列表（包含行为）
     * @return
     */
    @ApiOperation(value = "推荐展商列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
            ,@ApiImplicitParam(name = "pageSize", value = "每页数量",  paramType = "query",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/randExibitionList")
    public BaseResponse<PageInfo<ExhibitorDto>> randExibitionList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        SessionUser appLoginDTO = sessionState.getCurrentUser(paramMap.get("token").toString());
        Map conditionMap = new HashMap();
        conditionMap.put("isdel",CommonDict.CORRECT_STATE);
        conditionMap.put("state",1);
        conditionMap.put("labelid",appLoginDTO.getLabelid());
        conditionMap.put("id",appLoginDTO.getId());

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<ExhibitorDto> appLoginDTOS = ehbExhibitorService.randExibitionList(conditionMap);
        for(ExhibitorDto exhibitorDto : appLoginDTOS){
            ExhibitorDto.of(exhibitorDto);
        }
        Collections.shuffle(appLoginDTOS);
        return setResultSuccess(new PageInfo<>(appLoginDTOS));
    }

    /**
     * 行业资讯列表
     * @return
     */
    @ApiOperation(value = "行业资讯列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
            ,@ApiImplicitParam(name = "pageSize", value = "每页数量",  paramType = "query",required = true)
            ,@ApiImplicitParam(name = "title", value = "资讯标题", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/articleList")
    public BaseResponse<PageInfo<ArticleDto>> articleList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        QueryWrapper<EhbArticle> ehbArticleQueryWrapper = new QueryWrapper<>();
        ehbArticleQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbArticleQueryWrapper.eq(!StringUtils.isEmpty(paramMap.get("title")),"title",paramMap.get("title"));
        ehbArticleQueryWrapper.orderByDesc("releasetime");

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbArticle> ehbArticles = ehbArticleService.list(ehbArticleQueryWrapper);

//        List<ArticleDto> articleDtos = new ArrayList<>();
//        for(EhbArticle ehbArticle : ehbArticles){
//            articleDtos.add(ArticleDto.of(ehbArticle));
//        }
        Collections.shuffle(ehbArticles);
        return setResultSuccess(new PageInfo<>(ehbArticles));
    }

    /**
     * 商品橱窗列表
     * @return
     */
    @ApiOperation(value = "商品橱窗列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
            ,@ApiImplicitParam(name = "pageSize", value = "每页数量",  paramType = "query",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/goodsList")
    public BaseResponse<PageInfo<EhbOpportunity>> goodsList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        SessionUser appLoginDTO = sessionState.getCurrentUser(paramMap.get("token").toString());
        QueryWrapper<EhbOpportunity> ehbOpportunityQueryWrapper = new QueryWrapper<>();
        ehbOpportunityQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbOpportunityQueryWrapper.eq("createuser",appLoginDTO.getId());//必须是本企业
        ehbOpportunityQueryWrapper.eq("type",2);//类别是商品
        ehbOpportunityQueryWrapper.orderByDesc("releasetime");

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbOpportunity> ehbOpportunities = ehbOpportunityService.list(ehbOpportunityQueryWrapper);
        Collections.shuffle(ehbOpportunities);
        return setResultSuccess(new PageInfo<>(ehbOpportunities));
    }

    /**
     * 云端橱窗列表
     * @return
     */
    @ApiOperation(value = "云端橱窗列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
            ,@ApiImplicitParam(name = "pageSize", value = "每页数量",  paramType = "query",required = true)
            ,@ApiImplicitParam(name = "enterprisename", value = "企业名称",  paramType = "query")
            ,@ApiImplicitParam(name = "boothno", value = "展位号", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/cloudWindowList")
    public BaseResponse<PageInfo<EhbExhibitor>> cloudWindowList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
        ehbExhibitorQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbExhibitorQueryWrapper.eq(!StringUtils.isEmpty(paramMap.get("enterprisename")),"enterprisename",paramMap.get("enterprisename"));
        ehbExhibitorQueryWrapper.eq(!StringUtils.isEmpty(paramMap.get("boothno")),"boothno",paramMap.get("boothno"));

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbExhibitor> ehbExhibitors = ehbExhibitorService.list(ehbExhibitorQueryWrapper);
        Collections.shuffle(ehbExhibitors);
        return setResultSuccess(new PageInfo<>(ehbExhibitors));
    }

    /**
     * 首页banner图列表
     * @return
     */
    @ApiOperation(value = "首页banner图列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "type", value = "banner位置",paramType = "query",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/ehbBannerList")
    public BaseResponse<List<EhbBanner>> ehbBannerList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        QueryWrapper<EhbBanner>  ehbBannerQueryWrapper= new QueryWrapper<>();
        ehbBannerQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbBannerQueryWrapper.eq("type",paramMap.get("type"));

        List<EhbBanner> ehbBanners = ehbBannerService.list(ehbBannerQueryWrapper);
        return setResultSuccess(ehbBanners);
    }

    /**
     * 首页嘉宾列表
     * @return
     */
    @ApiOperation(value = "首页嘉宾列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/guestList")
    public BaseResponse<List<EhbGuest>> guestList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        QueryWrapper<EhbGuest>  ehbGuestQueryWrapper= new QueryWrapper<>();
        ehbGuestQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);

        List<EhbGuest> ehbBanners = ehbGuestService.list(ehbGuestQueryWrapper);
        return setResultSuccess(ehbBanners);
    }
}