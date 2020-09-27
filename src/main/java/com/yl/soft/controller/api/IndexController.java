package com.yl.soft.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.AppLoginDTO;
import com.yl.soft.po.EhbArticle;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.EhbOpportunity;
import com.yl.soft.service.EhbArticleService;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.EhbOpportunityService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    /**
     * 推荐展商列表（包含行为）
     * @return
     */
    @ApiOperation(value = "推荐展商列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/randExibitionList")
    public BaseResponse<PageInfo<AppLoginDTO>> randExibitionList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        AppLoginDTO appLoginDTO = getCurrAppLogin(paramMap.get("token").toString());
        Map conditionMap = new HashMap();
        conditionMap.put("isdel",CommonDict.CORRECT_STATE);
        conditionMap.put("state",2);
        conditionMap.put("labelid",appLoginDTO.getLabelid());
        conditionMap.put("id",appLoginDTO.getId());

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<AppLoginDTO> appLoginDTOS = ehbExhibitorService.randExibitionList(conditionMap);
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
    public BaseResponse<PageInfo<EhbArticle>> articleList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
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
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/opportunityList")
    public BaseResponse<PageInfo<EhbOpportunity>> opportunityList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        AppLoginDTO appLoginDTO = getCurrAppLogin(paramMap.get("token").toString());
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
}