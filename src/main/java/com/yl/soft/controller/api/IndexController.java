package com.yl.soft.controller.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.yl.soft.common.unified.entity.BasePage;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.app.*;
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
import java.util.stream.Collectors;

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
    private EhbLabelService ehbLabelService;
    @Autowired
    private SessionState sessionState;
    @Autowired
    private RedisService redisService;


    /**
     * 推荐展商列表（包含行为）
     * @return
     */
    @ApiOperation(value = "推荐展商列表、感兴趣的展商列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "number", value = "显示数量",paramType = "query",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/randExibitionList")
    public BaseResponse<List<ExhibitorDto>> randExibitionList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("number"))){
            return setResultError("number不能为空！");

        }
        SessionUser appLoginDTO = sessionState.getCurrentUser(paramMap.get("token").toString());
        Map conditionMap = new HashMap();
        conditionMap.put("isdel",CommonDict.CORRECT_STATE);
        conditionMap.put("state",1);
        conditionMap.put("labelid", JSONArray.parseArray(appLoginDTO.getLabelid(),Integer.class));
        conditionMap.put("id",appLoginDTO.getId());

        List<ExhibitorDto> appLoginDTOS = ehbExhibitorService.randExibitionList(conditionMap);
        appLoginDTOS.stream().forEach(i->{
            switch (i.getState()){
                case 0:i.setState_show("未认证");break;
                case 1:i.setState_show("已认证");break;
                default:i.setState_show("未知状态");break;
            }
        });

//        for(ExhibitorDto exhibitorDto : appLoginDTOS){
//            if(exhibitorDto.getState() == 0){
//                exhibitorDto.setState_show("未认证");
//            }else if(exhibitorDto.getState() == 1){
//                exhibitorDto.setState_show("已认证");
//            }else{
//                exhibitorDto.setState_show("未知状态");
//            }
//        }
        Collections.shuffle(appLoginDTOS);
        int number = Integer.parseInt(paramMap.get("number")+"");
        appLoginDTOS = appLoginDTOS.subList(0,appLoginDTOS.size()>number?number:appLoginDTOS.size());
        return setResultSuccess(appLoginDTOS);
    }

    /**
     * 热门资讯集合
     * @return
     */
    @ApiOperation(value = "热门资讯集合")
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
    @PostMapping("/hotArticleList")
    public BaseResponse<List<ArticleDto>> hotArticleList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        QueryWrapper<EhbArticle> ehbArticleQueryWrapper = new QueryWrapper<>();
        ehbArticleQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbArticleQueryWrapper.eq("isrecommend",1);
        ehbArticleQueryWrapper.orderByDesc("releasetime");
        List<EhbArticle> ehbArticles = ehbArticleService.list(ehbArticleQueryWrapper);
        List<ArticleDto> articleDtos = new ArrayList<>();
        for(EhbArticle ehbArticle : ehbArticles){
            articleDtos.add(ArticleDto.of(ehbArticle));
        }
        Collections.shuffle(articleDtos);
        return setResultSuccess(articleDtos);
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
    public BaseResponse<BasePage<ArticleDto>> articleList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
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
        List<ArticleDto> articleDtos = new ArrayList<>();
        for(EhbArticle ehbArticle : ehbArticles){
            articleDtos.add(ArticleDto.of(ehbArticle));
        }
        Collections.shuffle(articleDtos);
        return setResultSuccess(getBasePage(ehbArticles,articleDtos));
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
            ,@ApiImplicitParam(name = "exhibitorid", value = "展商ID",  paramType = "query",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/goodsList")
    public BaseResponse<BasePage<OpportunityDto>> goodsList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        if(StringUtils.isEmpty(paramMap.get("exhibitorid"))){
            return setResultError(403,"","展商ID不能为空！");
        }
        QueryWrapper<EhbOpportunity> ehbOpportunityQueryWrapper = new QueryWrapper<>();
        ehbOpportunityQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbOpportunityQueryWrapper.eq("exhibitorid",paramMap.get("exhibitorid"));
        ehbOpportunityQueryWrapper.eq("type",2);//类别是商品
        ehbOpportunityQueryWrapper.orderByDesc("releasetime");

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbOpportunity> ehbOpportunities = ehbOpportunityService.list(ehbOpportunityQueryWrapper);
        List<OpportunityDto> opportunityDtos = new ArrayList<>();
        for(EhbOpportunity ehbOpportunity : ehbOpportunities){
            OpportunityDto opportunityDto = OpportunityDto.of(ehbOpportunity);
            if(!StringUtils.isEmpty(opportunityDto.getLabel())){
                QueryWrapper<EhbLabel> ehbLabelQueryWrapper = new QueryWrapper<>();
                ehbLabelQueryWrapper.in("id",JSONArray.parseArray(opportunityDto.getLabel(),Integer.class));
                ehbLabelQueryWrapper.in("isdel",CommonDict.CORRECT_STATE);
                List<String> labelList = ehbLabelService.list(ehbLabelQueryWrapper).stream().map(i->{
                    return i.getName();
                }).collect(Collectors.toList());
                opportunityDto.setLabelList(labelList);
            }
            opportunityDtos.add(opportunityDto);
        }
        Collections.shuffle(opportunityDtos);
        return setResultSuccess(getBasePage(ehbOpportunities,opportunityDtos));
    }

    /**
     * 云端橱窗全部展商列表
     * @return
     */
    @ApiOperation(value = "云端橱窗全部展商列表")
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
    @PostMapping("/cloudWindowList")
    public BaseResponse<List<CloudWindowDto>> cloudWindowList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        List<CloudWindowDto> cloudwindowdtos= new ArrayList<>();
        for(int i=65;i<91;i++) {
            String az = StrUtil.toString((char)i);
            CloudWindowDto cdto = new CloudWindowDto();
            Set<String> jsondtos = redisService.sGet(az);
            List<ExhibitorDto> dtos = jsondtos.stream().map(e -> JSONUtil.toBean(e, ExhibitorDto.class)).collect(Collectors.toList());
            cloudwindowdtos.add(cdto.setWord(az).setExhibitorDtos(dtos));
        }
        return setResultSuccess(cloudwindowdtos);
    }

    /**
     * 首页banner图列表
     * @return
     */
    @ApiOperation(value = "首页banner图列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "type", value = "banner位置类型（1：首页顶部，2：首页大会预告）",paramType = "query",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/ehbBannerList")
    public BaseResponse<List<BannerDto>> ehbBannerList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        QueryWrapper<EhbBanner>  ehbBannerQueryWrapper= new QueryWrapper<>();
        ehbBannerQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbBannerQueryWrapper.eq("type",paramMap.get("type"));

        List<EhbBanner> ehbBanners = ehbBannerService.list(ehbBannerQueryWrapper);
        List<BannerDto> bannerDtos = new ArrayList<>();
        for(EhbBanner ehbBanner : ehbBanners){
            bannerDtos.add(BannerDto.of(ehbBanner));
        }
        return setResultSuccess(bannerDtos);
    }

    /**
     * 首页嘉宾列表
     * @return
     */
    @ApiOperation(value = "首页嘉宾列表")
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
    @PostMapping("/guestList")
    public BaseResponse<BasePage<GuestDto>> guestList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }

        QueryWrapper<EhbGuest>  ehbGuestQueryWrapper= new QueryWrapper<>();
        ehbGuestQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbGuest> ehbGuests = ehbGuestService.list(ehbGuestQueryWrapper);
        List<GuestDto> guestDtos = new ArrayList<>();
        for(EhbGuest ehbGuest : ehbGuests){
            guestDtos.add(GuestDto.of(ehbGuest));
        }
        return setResultSuccess(getBasePage(ehbGuests,guestDtos));
    }
}