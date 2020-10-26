package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.EhbHall;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.EhbHallService;
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
 * 虚拟展厅表 前端控制器（未完成待续）
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/hall")
public class HallController extends BaseController {
    @Autowired
    public EhbHallService ehbHallService;
    @Autowired
    public EhbExhibitorService ehbExhibitorService;

    @GetMapping("/list")
    public String list() {
        return "hall/list";
    }

    /**
     * 查询虚拟展厅列表
     * @param page
     * @param limit
     * @param ehbHall
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbHall ehbHall, String startTime, String endTime) {
        QueryWrapper<EhbHall> ehbHallQueryWrapper = new QueryWrapper<>();
        ehbHallQueryWrapper.like(!StringUtils.isEmpty(ehbHall.getExhibitorname()),"exhibitorname",ehbHall.getExhibitorname());
        ehbHallQueryWrapper.eq(!StringUtils.isEmpty(ehbHall.getBoothno()),"boothno",ehbHall.getBoothno());
        ehbHallQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbHallQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        ehbHallQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbHall> ehbHalls = ehbHallService.list(ehbHallQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbHalls);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个虚拟展厅添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbHall ehbHall  = new EhbHall();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbHall = ehbHallService.getById(id);
        }
        modelMap.put("ehbHall",ehbHall);

        QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
        ehbExhibitorQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbExhibitorQueryWrapper.eq("state",1);
        List<EhbExhibitor> ehbExhibitors = ehbExhibitorService.list(ehbExhibitorQueryWrapper);
        modelMap.put("ehbExhibitors",ehbExhibitors);
        return "hall/input";
    }

    /**
     * 添加或者修改虚拟展厅
     * @param ehbHall
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbHall ehbHall) {
        EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(ehbHall.getExhibitorid());
        ehbExhibitor.setHalurl(ehbHall.getHallurl());
        ehbExhibitorService.updateById(ehbExhibitor);

        ehbHall.setExhibitorname(ehbExhibitor.getEnterprisename());
        ehbHall.setBoothno(ehbExhibitor.getBoothno());
//        ehbHall.setHallurl(ehbExhibitor.getHalurl());
        if(StringUtils.isEmpty(ehbHall.getId())){
            ehbHall.setCreatetime(LocalDateTime.now());
            ehbHall.setCreateuser(1);
            ehbHall.setIsdel(false);
        }else{
            ehbHall.setUpdatetime(LocalDateTime.now());
            ehbHall.setUpdateuser(1);
        }
        if(ehbHallService.saveOrUpdate(ehbHall)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除虚拟展厅
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse delete(String id) {
        System.out.println("ok");
        if(StringUtils.isEmpty(id)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"删除ID为空！");
        }
        ehbHallService.deleteEhbHall(id);
        return setResultSuccess();
    }
}