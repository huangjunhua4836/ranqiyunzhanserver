package com.yl.soft.controller.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yl.soft.common.unified.entity.BasePage;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.app.*;
import com.yl.soft.dto.base.BaseResult;
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
    private EhbAudienceService ehbAudienceService;
    @Autowired
    private EhbUseractionService ehbUseractionService;
    @Autowired
    private SessionState sessionState;
    @Autowired
    private RedisService redisService;


    /**
     * 后台推荐展商列表（首页推荐展商后台直接推荐）
     * @return
     */
    @ApiOperation(value = "首页推荐展商后台直接推荐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query")
            ,@ApiImplicitParam(name = "pageSize", value = "每页数量",  paramType = "query",required = true)
            ,@ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/backstageRecommendExibitionList")
    public BaseResponse<BasePage<ExhibitorDto>> backstageRecommendExibitionList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        if(StringUtils.isEmpty(paramMap.get("pageSize"))){
            return setResultError(403,"","每页数量不能为空！");
        }
        QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
        ehbExhibitorQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbExhibitorQueryWrapper.eq("state",1);
        ehbExhibitorQueryWrapper.eq("isrecommend",true);
        ehbExhibitorQueryWrapper.orderByDesc("sort","id");
        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbExhibitor> ehbExhibitors = ehbExhibitorService.list(ehbExhibitorQueryWrapper);
        List<ExhibitorDto> exhibitorDtos = ehbExhibitors.stream().map(i->{
            ExhibitorDto exhibitorDto = new ExhibitorDto();
            BeanUtil.copyProperties(i,exhibitorDto);
            EhbAudience ehbAudience = ehbAudienceService.lambdaQuery().select(EhbAudience::getHeadPortrait).eq(EhbAudience::getBopie,i.getId()).last("limit 1").one();
            exhibitorDto.setLogo(ehbAudience!=null?ehbAudience.getHeadPortrait():null);
            switch (i.getState()){
                case 0:exhibitorDto.setState_show("未认证");break;
                case 1:exhibitorDto.setState_show("已认证");break;
                default:exhibitorDto.setState_show("未知状态");break;
            }
            if(!StringUtils.isEmpty(paramMap.get("token"))){
                SessionUser sessionUser = sessionState.getCurrentUser(paramMap.get("token")+"");
                Integer count = ehbUseractionService.lambdaQuery().eq(EhbUseraction::getUserid, sessionUser.getId())
                        .eq(EhbUseraction::getType, 1).eq(EhbUseraction::getRelateid, i.getId())
                        .eq(EhbUseraction::getActivetype, 1).count();
                if(count>0){//展商已关注
                    exhibitorDto.setRelateid(1);
                }
            }
            return exhibitorDto;
        }).collect(Collectors.toList());
        return setResultSuccess(getBasePage(ehbExhibitors,exhibitorDtos));
    }

    /**
     * 推荐展商列表（云端橱窗感兴趣的展商-包含行为）
     * @return
     */
    @ApiOperation(value = "推荐展商列表、感兴趣的展商列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query")
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
        List<ExhibitorDto> appLoginDTOS = new ArrayList<>();
        if(!StringUtils.isEmpty(paramMap.get("token"))){
            //自己收藏的展商
            SessionUser appLoginDTO = sessionState.getCurrentUser(paramMap.get("token").toString());
            Map conditionMap = new HashMap();
            conditionMap.put("isdel",CommonDict.CORRECT_STATE);
            conditionMap.put("state",1);
            conditionMap.put("labelid", JSONArray.parseArray(appLoginDTO.getLabelid(),Integer.class));
            conditionMap.put("exhibitorid",appLoginDTO.getBopie());
            conditionMap.put("id",appLoginDTO.getId());

            appLoginDTOS = ehbExhibitorService.randExibitionList(conditionMap);
            appLoginDTOS.stream().forEach(i->{
                EhbAudience ehbAudience = ehbAudienceService.lambdaQuery().select(EhbAudience::getHeadPortrait).eq(EhbAudience::getBopie,i.getId()).last("limit 1").one();
                i.setLogo(ehbAudience!=null?ehbAudience.getHeadPortrait():null);
                switch (i.getState()){
                    case 0:i.setState_show("未认证");break;
                    case 1:i.setState_show("已认证");break;
                    default:i.setState_show("未知状态");break;
                }
            });
        }else{
            appLoginDTOS = ehbExhibitorService.lambdaQuery().eq(EhbExhibitor::getIsdel,CommonDict.CORRECT_STATE).eq(EhbExhibitor::getState,1).list()
                    .stream().map(i->{
                ExhibitorDto exhibitorDto = new ExhibitorDto();
                BeanUtil.copyProperties(i,exhibitorDto);

                EhbAudience ehbAudience = ehbAudienceService.lambdaQuery().select(EhbAudience::getHeadPortrait).eq(EhbAudience::getBopie,i.getId()).last("limit 1").one();
                i.setLogo(ehbAudience!=null?ehbAudience.getHeadPortrait():null);
                switch (i.getState()){
                    case 0:exhibitorDto.setState_show("未认证");break;
                    case 1:exhibitorDto.setState_show("已认证");break;
                    default:exhibitorDto.setState_show("未知状态");break;
                }
                return exhibitorDto;
            }).collect(Collectors.toList());
        }

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
        ehbArticleQueryWrapper.orderByDesc("sort","id");
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
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query")
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
//        ehbArticleQueryWrapper.select("id","title","headpicture");
        ehbArticleQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbArticleQueryWrapper.like(!StringUtils.isEmpty(paramMap.get("title")),"title",paramMap.get("title"));
//        ehbArticleQueryWrapper.orderByDesc("releasetime");
        ehbArticleQueryWrapper.orderByDesc("sort","id");//防止分页数据重复用永远不重复的数据排序

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbArticle> ehbArticles = ehbArticleService.list(ehbArticleQueryWrapper);
        List<ArticleDto> articleDtos = new ArrayList<>();
        for(EhbArticle ehbArticle : ehbArticles){
            articleDtos.add(ArticleDto.of(ehbArticle));
        }
//        Collections.shuffle(articleDtos);
        return setResultSuccess(getBasePage(ehbArticles,articleDtos));
    }

    /**
     * 商品橱窗列表
     * @return
     */
    @ApiOperation(value = "商品橱窗列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query")
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
    public BaseResult<List<OpportunityDto>> goodsList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return error(403,"当前页码不能为空！");
        }
        if(StringUtils.isEmpty(paramMap.get("exhibitorid"))){
            return error(403,"展商ID不能为空！");
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
//        Collections.shuffle(opportunityDtos);
        Page pageInfo = (Page) ehbOpportunities;
        return ok(opportunityDtos, pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getPageSize());
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
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query")
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
        ehbBannerQueryWrapper.orderByDesc("sort");

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
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query")
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