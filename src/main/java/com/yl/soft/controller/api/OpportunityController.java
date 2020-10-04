package com.yl.soft.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.app.AdvertisingDto;
import com.yl.soft.dto.app.OpportunityAndAdvertisingDto;
import com.yl.soft.dto.app.OpportunityDto;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.EhbAdvertising;
import com.yl.soft.service.EhbAdvertisingService;
import com.yl.soft.service.EhbOpportunityService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Api(tags = {"C端模块-商机"})
@RestController
@RequestMapping("/api")
public class OpportunityController extends BaseController {
    @Autowired
    private EhbOpportunityService ehbOpportunityService;
    @Autowired
    private EhbAdvertisingService ehbAdvertisingService;
    @Autowired
    private SessionState sessionState;

    /**
     * 商机列表-推荐
     * @return
     */
    @ApiOperation(value = "商机列表-推荐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数",  paramType = "query",required = true)
            ,@ApiImplicitParam(name = "key", value = "企业名称,商机名称,展位号",  paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/opportunityRecommendList")
    public BaseResponse<OpportunityAndAdvertisingDto> opportunityRecommendList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        SessionUser appLoginDTO = sessionState.getCurrentUser(paramMap.get("token").toString());
        Map conditionMap = new HashMap();
        conditionMap.put("isdel",CommonDict.CORRECT_STATE);
        conditionMap.put("enterprisename","%"+paramMap.get("key")+"%");
        conditionMap.put("title","%"+paramMap.get("key")+"%");
        conditionMap.put("boothno","%"+paramMap.get("key")+"%");
        conditionMap.put("labelid", JSONArray.parseArray(appLoginDTO.getLabelid(),Integer.class));//行为推荐
        conditionMap.put("type",1);//商机

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], 8);
        List<OpportunityDto> ehbOpportunities = ehbOpportunityService.opportunityList(conditionMap);
        Collections.shuffle(ehbOpportunities);
        //广告
        QueryWrapper<EhbAdvertising> ehbAdvertisingQueryWrapper = new QueryWrapper<>();
        ehbAdvertisingQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        List<EhbAdvertising> ehbAdvertisings = ehbAdvertisingService.list(ehbAdvertisingQueryWrapper);
        List<AdvertisingDto> advertisingDtos = new ArrayList<>();
        for(EhbAdvertising ehbAdvertising : ehbAdvertisings){
            advertisingDtos.add(AdvertisingDto.of(ehbAdvertising));
        }
        Collections.shuffle(advertisingDtos);

        OpportunityAndAdvertisingDto opportunityAndAdvertisingDto = new OpportunityAndAdvertisingDto();
        opportunityAndAdvertisingDto.setOpportunityDtoBasePage(getBasePage(ehbOpportunities,ehbOpportunities));
        opportunityAndAdvertisingDto.setAdvertisingDto(advertisingDtos.get(0));
        return setResultSuccess(opportunityAndAdvertisingDto);
    }

    /**
     * 商机列表-最新
     * @return
     */
    @ApiOperation(value = "商机列表-最新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数",  paramType = "query",required = true)
            ,@ApiImplicitParam(name = "key", value = "企业名称,商机名称,展位号",  paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/opportunityNewList")
    public BaseResponse<OpportunityAndAdvertisingDto> opportunityNewList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        Map conditionMap = new HashMap();
        conditionMap.put("isdel",CommonDict.CORRECT_STATE);
        conditionMap.put("enterprisename","%"+paramMap.get("key")+"%");
        conditionMap.put("title","%"+paramMap.get("key")+"%");
        conditionMap.put("boothno","%"+paramMap.get("key")+"%");
        conditionMap.put("type",1);//商机

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<OpportunityDto> ehbOpportunities = ehbOpportunityService.opportunityList(conditionMap);
        //广告
        QueryWrapper<EhbAdvertising> ehbAdvertisingQueryWrapper = new QueryWrapper<>();
        ehbAdvertisingQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        List<EhbAdvertising> ehbAdvertisings = ehbAdvertisingService.list(ehbAdvertisingQueryWrapper);
        List<AdvertisingDto> advertisingDtos = new ArrayList<>();
        for(EhbAdvertising ehbAdvertising : ehbAdvertisings){
            advertisingDtos.add(AdvertisingDto.of(ehbAdvertising));
        }
        Collections.shuffle(advertisingDtos);

        OpportunityAndAdvertisingDto opportunityAndAdvertisingDto = new OpportunityAndAdvertisingDto();
        opportunityAndAdvertisingDto.setOpportunityDtoBasePage(getBasePage(ehbOpportunities,ehbOpportunities));
        opportunityAndAdvertisingDto.setAdvertisingDto(advertisingDtos.get(0));
        return setResultSuccess(opportunityAndAdvertisingDto);
    }

    /**
     * 商机列表-热门
     * @return
     */
    @ApiOperation(value = "商机列表-热门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数",  paramType = "query",required = true)
            ,@ApiImplicitParam(name = "key", value = "企业名称,商机名称,展位号",  paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/opportunityHotList")
    public BaseResponse<OpportunityAndAdvertisingDto> opportunityHotList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        Map conditionMap = new HashMap();
        conditionMap.put("isdel",CommonDict.CORRECT_STATE);
        conditionMap.put("enterprisename","%"+paramMap.get("key")+"%");
        conditionMap.put("title","%"+paramMap.get("key")+"%");
        conditionMap.put("boothno","%"+paramMap.get("key")+"%");
        conditionMap.put("countthumbs",10);
        conditionMap.put("countbrowse",20);
        conditionMap.put("countcomment",5);
        conditionMap.put("type",1);//商机

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<OpportunityDto> ehbOpportunities = ehbOpportunityService.opportunityList(conditionMap);
        Collections.shuffle(ehbOpportunities);
        //广告
        QueryWrapper<EhbAdvertising> ehbAdvertisingQueryWrapper = new QueryWrapper<>();
        ehbAdvertisingQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        List<EhbAdvertising> ehbAdvertisings = ehbAdvertisingService.list(ehbAdvertisingQueryWrapper);
        List<AdvertisingDto> advertisingDtos = new ArrayList<>();
        for(EhbAdvertising ehbAdvertising : ehbAdvertisings){
            advertisingDtos.add(AdvertisingDto.of(ehbAdvertising));
        }
        Collections.shuffle(advertisingDtos);

        OpportunityAndAdvertisingDto opportunityAndAdvertisingDto = new OpportunityAndAdvertisingDto();
        opportunityAndAdvertisingDto.setOpportunityDtoBasePage(getBasePage(ehbOpportunities,ehbOpportunities));
        opportunityAndAdvertisingDto.setAdvertisingDto(advertisingDtos.get(0));
        return setResultSuccess(opportunityAndAdvertisingDto);
    }

    /**
     * 广告位
     * @return
     */
    @ApiOperation(value = "广告位列表")
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
    @PostMapping("/advertisingList")
    public BaseResponse<List<AdvertisingDto>> advertisingList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        QueryWrapper<EhbAdvertising> ehbAdvertisingQueryWrapper = new QueryWrapper<>();
        ehbAdvertisingQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        List<EhbAdvertising> ehbAdvertisings = ehbAdvertisingService.list(ehbAdvertisingQueryWrapper);
        List<AdvertisingDto> advertisingDtos = new ArrayList<>();
        for(EhbAdvertising ehbAdvertising : ehbAdvertisings){
            advertisingDtos.add(AdvertisingDto.of(ehbAdvertising));
        }
        Collections.shuffle(advertisingDtos);
        return setResultSuccess(advertisingDtos);
    }
}