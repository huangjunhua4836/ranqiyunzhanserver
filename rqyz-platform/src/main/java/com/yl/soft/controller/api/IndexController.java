package com.yl.soft.controller.api;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.AppLoginDTO;
import com.yl.soft.po.EhbArticle;
import com.yl.soft.service.EhbArticleService;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.EhbLabelService;
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
    private EhbAudienceService ehbAudienceService;
    @Autowired
    private EhbLabelService ehbLabelService;
    @Autowired
    private EhbExhibitorService ehbExhibitorService;
    @Autowired
    private EhbArticleService ehbArticleService;


    /**
     * 推荐展商列表
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
    public BaseResponse<List<AppLoginDTO>> randExibitionList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
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
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/articleList")
    public BaseResponse<List<EhbArticle>> articleList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        QueryWrapper<EhbArticle> ehbArticleQueryWrapper = new QueryWrapper<>();
        ehbArticleQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbArticleQueryWrapper.orderByDesc("releasetime");

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbArticle> ehbArticles = ehbArticleService.list(ehbArticleQueryWrapper);
        Collections.shuffle(ehbArticles);
        return setResultSuccess(new PageInfo<>(ehbArticles));
    }

//    /**
//     * 根据芯片ID查询季节免疫信息
//     * @return
//     */
//    @ApiOperation(value = "根据芯片ID查询季节免疫信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "chipId", value = "芯片号",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "season", value = "季节  1:春季  2：秋季",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功")
//            ,@ApiResponse(code = 401, message = "token为空！")
//            ,@ApiResponse(code = 402, message = "token失效！")
//            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
//            ,@ApiResponse(code = -1, message = "系统异常")
//    })
//    @PostMapping("/immunityByChipId")
//    public BaseResponse<PageInfo<TraceImmunity>> immunityByChipId(@ApiParam(hidden = true) @RequestParam Map paramMap) {
//        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
//            return setResultError(403,"","当前页不能为空！");
//        }
//        if(StringUtils.isEmpty(paramMap.get("chipId"))){
//            return setResultError(403,"","芯片号不能为空！");
//        }
//        if(StringUtils.isEmpty(paramMap.get("season"))){
//            return setResultError(403,"","季节不能为空！");
//        }
//        QueryWrapper<TraceImmunity> traceImmunityQueryWrapper = new QueryWrapper<>();
//        traceImmunityQueryWrapper.eq("CHIP_ID",paramMap.get("chipId").toString());
//        traceImmunityQueryWrapper.eq("SEASON",paramMap.get("season").toString());
//        traceImmunityQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
//        Integer pageParam[] = pageValidParam(paramMap);
//        PageHelper.startPage(pageParam[0], pageParam[1]);
//        List<TraceImmunity> traceImmunities = traceImmunityService.list(traceImmunityQueryWrapper);
//        return setResultSuccess(new PageInfo<>(traceImmunities));
//    }
//
//    /**
//     * 根据芯片ID查询诊疗信息
//     * @return
//     */
//    @ApiOperation(value = "根据芯片ID查询诊疗信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "chipId", value = "芯片号",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功")
//            ,@ApiResponse(code = 401, message = "token为空！")
//            ,@ApiResponse(code = 402, message = "token失效！")
//            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
//            ,@ApiResponse(code = -1, message = "系统异常")
//    })
//    @PostMapping("/diagnosisByChipId")
//    public BaseResponse<PageInfo<TraceDiagnosis>> diagnosisByChipId(@ApiParam(hidden = true) @RequestParam Map paramMap) {
//        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
//            return setResultError(403,"","当前页不能为空！");
//        }
//        if(StringUtils.isEmpty(paramMap.get("chipId"))){
//            return setResultError(403,"","芯片号不能为空！");
//        }
//        QueryWrapper<TraceDiagnosis> traceDiagnosisQueryWrapper = new QueryWrapper<>();
//        traceDiagnosisQueryWrapper.eq("CHIP_ID",paramMap.get("chipId").toString());
//        traceDiagnosisQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
//        Integer pageParam[] = pageValidParam(paramMap);
//        PageHelper.startPage(pageParam[0], pageParam[1]);
//        List<TraceDiagnosis> traceDiagnoses = traceDiagnosisService.list(traceDiagnosisQueryWrapper);
//        return setResultSuccess(new PageInfo<>(traceDiagnoses));
//    }
//
//    /**
//     * 根据芯片ID查询消毒信息
//     * @return
//     */
//    @ApiOperation(value = "根据芯片ID查询消毒信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "chipId", value = "芯片号",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功")
//            ,@ApiResponse(code = 401, message = "token为空！")
//            ,@ApiResponse(code = 402, message = "token失效！")
//            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
//            ,@ApiResponse(code = -1, message = "系统异常")
//    })
//    @PostMapping("/disinfectByChipId")
//    public BaseResponse<PageInfo<TraceDisinfect>> disinfectByChipId(@ApiParam(hidden = true) @RequestParam Map paramMap) {
//        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
//            return setResultError(403,"","当前页不能为空！");
//        }
//        if(StringUtils.isEmpty(paramMap.get("chipId"))){
//            return setResultError(403,"","芯片号不能为空！");
//        }
//        QueryWrapper<TraceDisinfect> traceDisinfectQueryWrapper = new QueryWrapper<>();
//        traceDisinfectQueryWrapper.eq("CHIP_ID",paramMap.get("chipId").toString());
//        traceDisinfectQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
//        Integer pageParam[] = pageValidParam(paramMap);
//        PageHelper.startPage(pageParam[0], pageParam[1]);
//        List<TraceDisinfect> traceDisinfects = traceDisinfectService.list(traceDisinfectQueryWrapper);
//        return setResultSuccess(new PageInfo<>(traceDisinfects));
//    }
//
//    /**
//     * 根据芯片ID查询繁育信息
//     * @return
//     */
//    @ApiOperation(value = "根据芯片ID查询繁育信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "chipId", value = "芯片号",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功")
//            ,@ApiResponse(code = 401, message = "token为空！")
//            ,@ApiResponse(code = 402, message = "token失效！")
//            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
//            ,@ApiResponse(code = -1, message = "系统异常")
//    })
//    @PostMapping("/breedByChipId")
//    public BaseResponse<PageInfo<TraceBreed>> breedByChipId(@ApiParam(hidden = true) @RequestParam Map paramMap) {
//        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
//            return setResultError(403,"","当前页不能为空！");
//        }
//        if(StringUtils.isEmpty(paramMap.get("chipId"))){
//            return setResultError(403,"","芯片号不能为空！");
//        }
//        QueryWrapper<TraceBreed> traceBreedQueryWrapper = new QueryWrapper<>();
//        traceBreedQueryWrapper.eq("CHIP_ID",paramMap.get("chipId").toString());
//        traceBreedQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
//        Integer pageParam[] = pageValidParam(paramMap);
//        PageHelper.startPage(pageParam[0], pageParam[1]);
//        List<TraceBreed> traceBreeds = traceBreedService.list(traceBreedQueryWrapper);
//        return setResultSuccess(new PageInfo<>(traceBreeds));
//    }
//
//    /**
//     * 根据芯片ID查询驱虫信息
//     * @return
//     */
//    @ApiOperation(value = "根据芯片ID查询驱虫信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "chipId", value = "芯片号",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功")
//            ,@ApiResponse(code = 401, message = "token为空！")
//            ,@ApiResponse(code = 402, message = "token失效！")
//            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
//            ,@ApiResponse(code = -1, message = "系统异常")
//    })
//    @PostMapping("/dewormingByChipId")
//    public BaseResponse<PageInfo<TraceDeworming>> dewormingByChipId(@ApiParam(hidden = true) @RequestParam Map paramMap) {
//        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
//            return setResultError(403,"","当前页不能为空！");
//        }
//        if(StringUtils.isEmpty(paramMap.get("chipId"))){
//            return setResultError(403,"","芯片号不能为空！");
//        }
//        QueryWrapper<TraceDeworming> traceDewormingQueryWrapper = new QueryWrapper<>();
//        traceDewormingQueryWrapper.eq("CHIP_ID",paramMap.get("chipId").toString());
//        traceDewormingQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
//        Integer pageParam[] = pageValidParam(paramMap);
//        PageHelper.startPage(pageParam[0], pageParam[1]);
//        List<TraceDeworming> traceDewormings = traceDewormingService.list(traceDewormingQueryWrapper);
//        return setResultSuccess(new PageInfo<>(traceDewormings));
//    }
}