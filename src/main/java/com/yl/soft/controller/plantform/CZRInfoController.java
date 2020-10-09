package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 观展人信息
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/czrInfo")
public class CZRInfoController extends BaseController {
    @Autowired
    public EhbAudienceService ehbAudienceService;

    @GetMapping("/list")
    public String list() {
        return "exhibitioninfo/list";
    }

    /**
     * 列表
     * @param page
     * @param limit
     * @param ehbAudience
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbAudience ehbAudience, String startTime, String endTime) {
        QueryWrapper<EhbAudience> ehbAudienceQueryWrapper = new QueryWrapper<>();
        ehbAudienceQueryWrapper.eq(!StringUtils.isEmpty(ehbAudience.getPhone()),"phone",ehbAudience.getPhone());
        ehbAudienceQueryWrapper.like(!StringUtils.isEmpty(ehbAudience.getName()),"name",ehbAudience.getName());
        ehbAudienceQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbAudienceQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        ehbAudienceQueryWrapper.isNull("bopie");//非展商
        ehbAudienceQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbAudience> ehbAudiences = ehbAudienceService.list(ehbAudienceQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbAudiences);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }
}