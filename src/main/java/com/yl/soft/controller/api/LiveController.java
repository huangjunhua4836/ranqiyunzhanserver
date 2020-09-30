package com.yl.soft.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.yl.soft.common.unified.entity.BasePage;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.app.LiveDto;
import com.yl.soft.po.EhbLive;
import com.yl.soft.service.EhbLiveService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Api(tags = {"C端模块-带你逛展"})
@RestController
@RequestMapping("/api")
public class LiveController extends BaseController {
    @Autowired
    private EhbLiveService ehbLiveService;

    /**
     * 推荐直播列表
     * @return
     */
    @ApiOperation(value = "推荐直播列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
            ,@ApiImplicitParam(name = "pageNum", value = "当前页数", required = true, paramType = "query")
            ,@ApiImplicitParam(name = "pageSize", value = "每页数量",  paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            ,@ApiResponse(code = 401, message = "token为空！")
            ,@ApiResponse(code = 402, message = "token失效！")
            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
            ,@ApiResponse(code = -1, message = "系统异常")
    })
    @PostMapping("/recommendLiveList")
    public BaseResponse<BasePage<LiveDto>> recommendLiveList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
            return setResultError(403,"","当前页码不能为空！");
        }
        QueryWrapper<EhbLive> ehbLiveQueryWrapper = new QueryWrapper<>();
        ehbLiveQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbLiveQueryWrapper.orderByDesc("leveltime");

        Integer pageParam[] = pageValidParam(paramMap);
        PageHelper.startPage(pageParam[0], pageParam[1]);
        List<EhbLive> ehbLives = ehbLiveService.list(ehbLiveQueryWrapper);
        List<LiveDto> liveDtos = new ArrayList<>();
        for(EhbLive ehbLive : ehbLives){
            liveDtos.add(LiveDto.of(ehbLive));
        }
        Collections.shuffle(liveDtos);
        return setResultSuccess(getBasePage(ehbLives,liveDtos));
    }
}