//package com.yl.soft.controller.app;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.yl.soft.common.entity.BaseResponse;
//import com.yl.soft.controller.base.BaseController;
//import com.yl.soft.dict.CommonDict;
//import com.yl.soft.dict.TraceChipDict;
//import com.yl.soft.dto.information.ChipTypeDto;
//import com.yl.soft.utils.StringUtils;
//import io.swagger.annotations.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//@Api(tags = {"C端模块-免疫信息追溯模块"})
//@RestController
//@RequestMapping("/app/api")
//public class ViewController extends BaseController {
//    @Autowired
//    private TraceImmunityService traceImmunityService;
//
//    @Autowired
//    private TraceDiagnosisService traceDiagnosisService;
//
//    @Autowired
//    private TraceDisinfectService traceDisinfectService;
//
//    @Autowired
//    private TraceBreedService traceBreedService;
//
//    @Autowired
//    private TraceDewormingService traceDewormingService;
//
//    @Autowired
//    private TraceChipService traceChipService;
//
//    /**
//     * 芯片类型接口
//     * @return
//     */
//    @ApiOperation(value = "芯片类型接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功")
//            ,@ApiResponse(code = 401, message = "token为空！")
//            ,@ApiResponse(code = 402, message = "token失效！")
//            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
//            ,@ApiResponse(code = -1, message = "系统异常")
//    })
//    @PostMapping("/chipType")
//    public BaseResponse<ChipTypeDto> chipType(@ApiParam(hidden = true) @RequestParam Map paramMap) {
//        List<ChipTypeDto> chipTypeDtos = new ArrayList<>();
//        Map map = TraceChipDict.TRACETYPE;
//        Set<String> keys = map.keySet();
//        for(String key :keys){
//            ChipTypeDto chipTypeDto = new ChipTypeDto();
//            chipTypeDto.setTypeKey(key);
//            chipTypeDto.setTypeValue(map.get(key).toString());
//            chipTypeDtos.add(chipTypeDto);
//        }
//        return setResultSuccess(chipTypeDtos);
//    }
//
//    /**
//     * 芯片列表接口
//     * @return
//     */
//    @ApiOperation(value = "芯片列表接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "herdsmenId", value = "牧户ID(登录用户ID)",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "type", value = "芯片类型 牛：1 马：2 羊：3 骆驼：4",paramType = "query")
//            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "chipId", value = "芯片号", paramType = "query")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功")
//            ,@ApiResponse(code = 401, message = "token为空！")
//            ,@ApiResponse(code = 402, message = "token失效！")
//            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
//            ,@ApiResponse(code = -1, message = "系统异常")
//    })
//    @PostMapping("/listChip")
//    public BaseResponse<PageInfo<TraceChip>> listChip(@ApiParam(hidden = true) @RequestParam Map paramMap) {
//        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
//            return setResultError(403,"","当前页不能为空！");
//        }
//        if(StringUtils.isEmpty(paramMap.get("herdsmenId"))){
//            return setResultError(403,"","牧户ID不能为空！");
//        }
//        QueryWrapper<TraceChip> traceChipQueryWrapper = new QueryWrapper<>();
//        traceChipQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
//        traceChipQueryWrapper.eq("HERDSMEN_ID", paramMap.get("herdsmenId").toString());
//        traceChipQueryWrapper.eq(!StringUtils.isEmpty(paramMap.get("type").toString()),"TYPE", paramMap.get("type").toString());
//        traceChipQueryWrapper.eq(!StringUtils.isEmpty(paramMap.get("chipId").toString()),"CHIPID", paramMap.get("chipId").toString());
//
//        Integer pageParam[] = pageValidParam(paramMap);
//        PageHelper.startPage(pageParam[0], pageParam[1]);
//        List<TraceChip> traceChips = traceChipService.list(traceChipQueryWrapper);
//        return setResultSuccess(new PageInfo<>(traceChips));
//    }
//
//    /**
//     * 根据芯片ID查询芯片基本信息
//     * @return
//     */
//    @ApiOperation(value = "根据芯片ID查询芯片基本信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "chipId", value = "芯片号",paramType = "query",required = true)
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功")
//            ,@ApiResponse(code = 401, message = "token为空！")
//            ,@ApiResponse(code = 402, message = "token失效！")
//            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
//            ,@ApiResponse(code = -1, message = "系统异常")
//    })
//    @PostMapping("/chipDetailByChipId")
//    public BaseResponse<TraceChip> chipDetailByChipId(@ApiParam(hidden = true) @RequestParam Map paramMap) {
//        if(StringUtils.isEmpty(paramMap.get("chipId"))){
//            return setResultError(403,"","芯片号不能为空！");
//        }
//        QueryWrapper<TraceChip> traceChipQueryWrapper = new QueryWrapper<>();
//        traceChipQueryWrapper.eq("CHIPID",paramMap.get("chipId").toString());
//        TraceChip traceChip = traceChipService.getOne(traceChipQueryWrapper);
//        return setResultSuccess(traceChip);
//    }
//
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
//}