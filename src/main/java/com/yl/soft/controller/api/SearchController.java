package com.yl.soft.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.yl.soft.common.unified.entity.BasePage;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.app.*;
import com.yl.soft.po.EhbArticle;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.EhbHottitle;
import com.yl.soft.service.EhbArticleService;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.EhbHottitleService;
import com.yl.soft.service.EhbOpportunityService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Api(tags = {"C端模块-首页-检索"})
@RestController
@RequestMapping("/api")
public class SearchController extends BaseController {
    @Autowired
    private EhbOpportunityService ehbOpportunityService;
    @Autowired
    private EhbExhibitorService ehbExhibitorService;
    @Autowired
    private EhbArticleService ehbArticleService;
    @Autowired
    private EhbHottitleService ehbHottitleService;

    /**
     * 热门词列表
     * @return
     */
    @ApiOperation(value = "热门词列表")
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
    @PostMapping("/hottitleList")
    public BaseResponse<List<HottitleDto>> hottitleList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        QueryWrapper<EhbHottitle> ehbHottitleQueryWrapper = new QueryWrapper<>();
        ehbHottitleQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbHottitleQueryWrapper.orderByDesc("releasetime");
        List<EhbHottitle> ehbHottitles = ehbHottitleService.list(ehbHottitleQueryWrapper);
        List<HottitleDto> hottitleDtos = new ArrayList<>();
        for(EhbHottitle ehbHottitle : ehbHottitles){
            hottitleDtos.add(HottitleDto.of(ehbHottitle));
        }
        return setResultSuccess(hottitleDtos);
    }

    /**
     * 综合检索
     * @return
     */
    @ApiOperation(value = "综合检索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "key", value = "企业名称或者展位号或者商品名或者资讯名",  paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/commonSearch")
    public BaseResponse<CommonInfoDto> commonSearch(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        //展商列表
        QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
        ehbExhibitorQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        if(!StringUtils.isEmpty(paramMap.get("key"))){
            ehbExhibitorQueryWrapper.and(i->i.like("enterprisename",paramMap.get("key"))
                    .or().like("boothno",paramMap.get("key")));
        }
        PageHelper.startPage(1, 5);
        List<EhbExhibitor> ehbExhibitors = ehbExhibitorService.list(ehbExhibitorQueryWrapper);
        List<ExhibitorDto> exhibitorDtos = ehbExhibitors.stream().map(e->ExhibitorDto.of(e)).collect(Collectors.toList());
        Collections.shuffle(exhibitorDtos);
        //商机列表
        Map conditionMap = new HashMap();
        conditionMap.put("isdel",CommonDict.CORRECT_STATE);
        conditionMap.put("title",paramMap.get("key"));
        conditionMap.put("type",1);//商机
        PageHelper.startPage(1, 5);
        List<OpportunityDto> opportunityDtos = ehbOpportunityService.opportunityList(conditionMap);
        Collections.shuffle(opportunityDtos);
        //资讯列表
        QueryWrapper<EhbArticle> ehbArticleQueryWrapper = new QueryWrapper<>();
        ehbArticleQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbArticleQueryWrapper.eq(!StringUtils.isEmpty(paramMap.get("key")),"title",paramMap.get("key"));
        ehbArticleQueryWrapper.orderByDesc("releasetime");
        PageHelper.startPage(1, 5);
        List<EhbArticle> ehbArticles = ehbArticleService.list(ehbArticleQueryWrapper);
        List<ArticleDto> articleDtos = ehbArticles.stream().map(e->ArticleDto.of(e)).collect(Collectors.toList());
        Collections.shuffle(articleDtos);

        CommonInfoDto commonInfoDto = new CommonInfoDto();
        commonInfoDto.setExhibitorDtos(exhibitorDtos);
        commonInfoDto.setOpportunityDtos(opportunityDtos);
        commonInfoDto.setArticleDtos(articleDtos);
        return setResultSuccess(commonInfoDto);
    }

    /**
     * 展商检索
     * @return
     */
    @ApiOperation(value = "展商检索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数",  paramType = "query")
            ,@ApiImplicitParam(name = "key", value = "企业名称或者展位号",  paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/exhibitionSearch")
    public BaseResponse<BasePage<ExhibitorDto>> exhibitionSearch(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
        ehbExhibitorQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        if(!StringUtils.isEmpty(paramMap.get("key"))){
            ehbExhibitorQueryWrapper.and(i->i.eq("enterprisename",paramMap.get("key"))
                    .or().eq("boothno",paramMap.get("key")));
        }
        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbExhibitor> ehbExhibitors = ehbExhibitorService.list(ehbExhibitorQueryWrapper);
        List<ExhibitorDto> exhibitorDtos = ehbExhibitors.stream().map(e->ExhibitorDto.of(e)).collect(Collectors.toList());
        Collections.shuffle(exhibitorDtos);
        return setResultSuccess(getBasePage(ehbExhibitors,exhibitorDtos));
    }

    /**
     * 商机检索
     * @return
     */
    @ApiOperation(value = "商机检索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数",  paramType = "query")
            ,@ApiImplicitParam(name = "key", value = "商品名", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/opportunitySearch")
    public BaseResponse<BasePage<OpportunityDto>> opportunitySearch(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        Map conditionMap = new HashMap();
        conditionMap.put("isdel",CommonDict.CORRECT_STATE);
        conditionMap.put("title",paramMap.get("key"));
        conditionMap.put("type",1);//商机

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<OpportunityDto> opportunityDtos = ehbOpportunityService.opportunityList(conditionMap);
        Collections.shuffle(opportunityDtos);
        return setResultSuccess(getBasePage(opportunityDtos,opportunityDtos));
    }

    /**
     * 资讯检索
     * @return
     */
    @ApiOperation(value = "资讯检索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
            ,@ApiImplicitParam(name = "key", value = "资讯标题", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/articleSearch")
    public BaseResponse<BasePage<ArticleDto>> articleSearch(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        QueryWrapper<EhbArticle> ehbArticleQueryWrapper = new QueryWrapper<>();
        ehbArticleQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbArticleQueryWrapper.eq(!StringUtils.isEmpty(paramMap.get("key")),"title",paramMap.get("key"));
        ehbArticleQueryWrapper.orderByDesc("releasetime");

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbArticle> ehbArticles = ehbArticleService.list(ehbArticleQueryWrapper);
        List<ArticleDto> articleDtos = ehbArticles.stream().map(e->ArticleDto.of(e)).collect(Collectors.toList());
        Collections.shuffle(articleDtos);
        return setResultSuccess(getBasePage(ehbArticles,articleDtos));
    }
}