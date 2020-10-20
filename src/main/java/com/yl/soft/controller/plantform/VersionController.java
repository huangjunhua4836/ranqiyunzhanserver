package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.po.Appversion;
import com.yl.soft.service.AppversionService;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 版本表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/version")
public class VersionController extends BaseController {
    @Autowired
    public AppversionService appversionService;

    @GetMapping("/list")
    public String list() {
        return "version/list";
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param appversion
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, Appversion appversion, String startTime, String endTime) {
        QueryWrapper<Appversion> appversionQueryWrapper = new QueryWrapper<>();
        appversionQueryWrapper.eq(!StringUtils.isEmpty(appversion.getVersion()),"version",appversion.getVersion());
        appversionQueryWrapper.orderByDesc("id");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<Appversion> appversions = appversionService.list(appversionQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(appversions);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        Appversion appversion = new Appversion();
        if("add".equals(type)){

        }else if("update".equals(type)){
            appversion = appversionService.getById(id);
        }
        modelMap.put("appversion",appversion);

        return "version/input";
    }

    /**
     * 添加或者修改
     * @param appversion
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(Appversion appversion) {
        if(StringUtils.isEmpty(appversion.getId())){
        }else{
        }
        if(appversionService.saveOrUpdate(appversion)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }
}