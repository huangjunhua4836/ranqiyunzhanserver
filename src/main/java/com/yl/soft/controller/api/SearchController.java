package com.yl.soft.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.AppLoginDTO;
import com.yl.soft.dto.OpportunityDto;
import com.yl.soft.po.EhbArticle;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public BaseResponse<List<EhbHottitle>> hottitleList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
        QueryWrapper<EhbHottitle> ehbHottitleQueryWrapper = new QueryWrapper<>();
        ehbHottitleQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbHottitleQueryWrapper.orderByDesc("releasetime");
        List<EhbHottitle> ehbHottitles = ehbHottitleService.list(ehbHottitleQueryWrapper);
        return setResultSuccess(ehbHottitles);
    }

//    /**
//     * 商机列表-推荐
//     * @return
//     */
//    @ApiOperation(value = "商机列表-推荐")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户登陆后获取token",paramType = "query",required = true)
//            ,@ApiImplicitParam(name = "pageNum", value = "当前页数",  paramType = "query")
//            ,@ApiImplicitParam(name = "enterprisename", value = "企业名称",  paramType = "query")
//            ,@ApiImplicitParam(name = "title", value = "商机名称", paramType = "query")
//            ,@ApiImplicitParam(name = "boothno", value = "展位号", paramType = "query")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功")
//            ,@ApiResponse(code = 401, message = "token为空！")
//            ,@ApiResponse(code = 402, message = "token失效！")
//            ,@ApiResponse(code = 403, message = "参数不合法请检查必填项")
//            ,@ApiResponse(code = -1, message = "系统异常")
//    })
//    @PostMapping("/opportunityRecommendList")
//    public BaseResponse<PageInfo<OpportunityDto>> opportunityRecommendList(@ApiParam(hidden = true) @RequestParam Map paramMap) {
//        if(StringUtils.isEmpty(paramMap.get("pageNum"))){
//            return setResultError(403,"","当前页码不能为空！");
//        }
//        AppLoginDTO appLoginDTO = getCurrAppLogin(paramMap.get("token").toString());
//        Map conditionMap = new HashMap();
//        conditionMap.put("isdel",CommonDict.CORRECT_STATE);
//        conditionMap.put("enterprisename",paramMap.get("enterprisename"));
//        conditionMap.put("title",paramMap.get("title"));
//        conditionMap.put("boothno",paramMap.get("boothno"));
//        conditionMap.put("labelid",appLoginDTO.getLabelid());//行为推荐
//
//        Integer pageParam[] = pageValidParam(paramMap);
//        PageHelper.startPage(pageParam[0], pageParam[1]);
//        List<OpportunityDto> ehbOpportunities = ehbOpportunityService.opportunityList(conditionMap);
//        Collections.shuffle(ehbOpportunities);
//        return setResultSuccess(new PageInfo<>(ehbOpportunities));
//    }
}