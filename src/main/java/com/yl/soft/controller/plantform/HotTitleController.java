package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.EhbHottitle;
import com.yl.soft.service.EhbHottitleService;
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
 * 热搜词表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/hottitle")
public class HotTitleController extends BaseController {
    @Autowired
    public EhbHottitleService ehbHottitleService;

    @GetMapping("/list")
    public String list() {
        return "hottitle/list";
    }

    /**
     * 查询热搜词列表
     * @param page
     * @param limit
     * @param ehbHottitle
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbHottitle ehbHottitle, String startTime, String endTime) {
        QueryWrapper<EhbHottitle> ehbHottitleQueryWrapper = new QueryWrapper<>();
        ehbHottitleQueryWrapper.like(!StringUtils.isEmpty(ehbHottitle.getHottitle()),"name",ehbHottitle.getHottitle());
        ehbHottitleQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbHottitleQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        ehbHottitleQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbHottitle> ehbHottitles = ehbHottitleService.list(ehbHottitleQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbHottitles);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }
//
    /**
     * 跳转到单个热搜词添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbHottitle ehbHottitle = new EhbHottitle();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbHottitle = ehbHottitleService.getById(id);
        }
        modelMap.put("ehbHottitle",ehbHottitle);

        return "hottitle/input";
    }

    /**
     * 添加或者修改热搜词
     * @param ehbHottitle
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbHottitle ehbHottitle) {
        if(StringUtils.isEmpty(ehbHottitle.getId())){
            ehbHottitle.setCreatetime(LocalDateTime.now());
            ehbHottitle.setCreateuser(1);
            ehbHottitle.setIsdel(false);
        }else{
            ehbHottitle.setUpdatetime(LocalDateTime.now());
            ehbHottitle.setUpdateuser(1);
        }
        if(ehbHottitleService.saveOrUpdate(ehbHottitle)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除热搜词
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse delete(String id) {
        System.out.println("ok");
        if(StringUtils.isEmpty(id)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"岗位删除ID为空！");
        }
        ehbHottitleService.removeById(id);
        return setResultSuccess();
    }
}