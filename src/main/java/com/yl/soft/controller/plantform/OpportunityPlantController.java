package com.yl.soft.controller.plantform;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.EhbArticle;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.EhbOpportunity;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.EhbOpportunityService;
import com.yl.soft.vo.OpportunityVo;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hjd on 2020-10-04 21:20
 * --> 商机列表
 **/
@Controller
@RequestMapping("/platform/opportunity")
public class OpportunityPlantController extends BaseController {
    @Autowired
    private EhbOpportunityService ehbOpportunityService;
    @Autowired
    private EhbExhibitorService ehbExhibitorService;

    @GetMapping("/list")
    public String list() {
        return "opportunity/list";
    }

    /**
     * 查询行业资讯列表
     *
     * @param page
     * @param limit
     * @param ehbOpportunity
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbOpportunity ehbOpportunity, String startTime, String endTime) {
        PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
        List<EhbOpportunity> ehbOpportunities = ehbOpportunityService.lambdaQuery().like(!StringUtils.isEmpty(ehbOpportunity.getTitle()),EhbOpportunity::getTitle,ehbOpportunity.getTitle())
                .eq(!StringUtils.isEmpty(ehbOpportunity.getExhibitorid()),EhbOpportunity::getExhibitorid,ehbOpportunity.getExhibitorid())
                .eq(EhbOpportunity::getIsdel,0)
                .between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),EhbOpportunity::getReleasetime,startTime,endTime)
                .list().stream().map(i->{
            OpportunityVo opportunityVo = new OpportunityVo();
            BeanUtil.copyProperties(i,opportunityVo);
            opportunityVo.setExhiName(ehbExhibitorService.getById(i.getExhibitorid()).getEnterprisename());
            return opportunityVo;
        }).collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo<>(ehbOpportunities);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int) pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个行业资讯添加或者修改页面
     *
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbOpportunity ehbOpportunity = new EhbOpportunity();
        if ("add".equals(type)) {

        } else if ("update".equals(type)) {
            ehbOpportunity = ehbOpportunityService.getById(id);
        }
        modelMap.put("ehbOpportunity", ehbOpportunity);

        return "opportunity/input";
    }

    /**
     * 添加或者修改行业资讯
     *
     * @param ehbOpportunity
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbOpportunity ehbOpportunity) {
        if (StringUtils.isEmpty(ehbOpportunity.getId())) {
            ehbOpportunity.setCreatetime(LocalDateTime.now());
            ehbOpportunity.setCreateuser(1);
            ehbOpportunity.setIsdel(false);
        } else {
            ehbOpportunity.setUpdatetime(LocalDateTime.now());
            ehbOpportunity.setUpdateuser(1);
        }
        if (ehbOpportunityService.saveOrUpdate(ehbOpportunity)) {
            return setResultSuccess();
        } else {
            return setResultError("操作失败！");
        }
    }
}