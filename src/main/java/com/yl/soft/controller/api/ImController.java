package com.yl.soft.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yl.soft.common.im.ImOperator;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.SessionState;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.EhbLiveBroadcast;
import com.yl.soft.service.EhbLiveBroadcastService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "聊天接口")
@RestController
@RequestMapping("/api/im")
public class ImController extends BaseController {

	@Autowired
	private ImOperator imOperator;
	@Autowired
	private EhbLiveBroadcastService ehbLiveBroadcastService;
	

    @Autowired
    private SessionState sessionState;

    @ApiOperation(value = "获取用户sig", notes = "获取sig用于腾讯im")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录标识", required = true, paramType = "query")
    })
    @ApiResponses({@ApiResponse(code = 0, message = "成功！"),
            @ApiResponse(code = -401, message = "请先登录"),
            @ApiResponse(code = 500, message = "未知异常,请联系管理员")})
    @GetMapping("/sig")
    public BaseResult<String> sig(String token) {
    	SessionUser sessionUser= sessionState.getCurrentUser(token);
        return ok2(imOperator.getUserSig(sessionUser.getId()+""));
    }
    
    @ApiOperation(value = "创建直播群", notes = "创建直播群")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录标识",paramType = "query",required = true),
            @ApiImplicitParam(name = "liveid", value = "直播间id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "gropName", value = "群组名字", required = true, paramType = "query"),
    })
    @ApiResponses({@ApiResponse(code = 0, message = "成功！"),
            @ApiResponse(code = -401, message = "请先登录"),
            @ApiResponse(code = 500, message = "未知异常,请联系管理员")})
    @PostMapping("/createLiveGrop")
    public BaseResult createLiveGrop(String token,String liveid,String gropName){
    	Map<String,Object> dataMap=(Map<String, Object>) JSON.parse(imOperator.createLiveGrop(gropName));
    	if(Integer.parseInt(dataMap.get("ErrorCode")+"")==0) {
    		ehbLiveBroadcastService.lambdaUpdate().setSql("gropid='"+dataMap.get("GroupId")+"'").eq(EhbLiveBroadcast::getId, liveid).update();
    		return ok2(dataMap.get("GroupId"));
    	}else {
    		return ok2(dataMap);
    	}
    	
    }
}
