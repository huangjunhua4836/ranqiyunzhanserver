package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.service.EhbExhibitorService;
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

/**
 * <p>
 * 展商信息
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/zsInfo")
public class ZSInfoController extends BaseController {
    @Autowired
    public EhbExhibitorService ehbExhibitorService;

    @GetMapping("/list")
    public String list() {
        return "exhibitioninfo/list2";
    }

    /**
     * 列表
     * @param page
     * @param limit
     * @param ehbExhibitor
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbExhibitor ehbExhibitor, String startTime, String endTime) {
        QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
        ehbExhibitorQueryWrapper.eq(!StringUtils.isEmpty(ehbExhibitor.getPhone()),"phone",ehbExhibitor.getPhone());
        ehbExhibitorQueryWrapper.like(!StringUtils.isEmpty(ehbExhibitor.getName()),"name",ehbExhibitor.getName());
        ehbExhibitorQueryWrapper.like(!StringUtils.isEmpty(ehbExhibitor.getEnterprisename()),"enterprisename",ehbExhibitor.getEnterprisename());
        ehbExhibitorQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbExhibitorQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        ehbExhibitorQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbExhibitor> ehbExhibitors = ehbExhibitorService.list(ehbExhibitorQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbExhibitors);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 添加或者修改
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbExhibitor ehbExhibitor = new EhbExhibitor();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbExhibitor = ehbExhibitorService.getById(id);
        }
        modelMap.put("ehbExhibitor",ehbExhibitor);

        return "exhibitioninfo/input2";
    }

    /**
     * 添加或者修改
     * @param ehbExhibitor
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbExhibitor ehbExhibitor) {
        if(StringUtils.isEmpty(ehbExhibitor.getId())){
            ehbExhibitor.setCreatetime(LocalDateTime.now());
            ehbExhibitor.setCreateuser(1);
            ehbExhibitor.setIsdel(false);
        }else{
            ehbExhibitor.setUpdatetime(LocalDateTime.now());
            ehbExhibitor.setUpdateuser(1);
            ehbExhibitor.setState(1);//审核通过
        }
        if(ehbExhibitorService.saveOrUpdate(ehbExhibitor)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }
}