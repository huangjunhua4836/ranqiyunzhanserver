package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.po.EhbLiveVmware;
import com.yl.soft.service.EhbLiveVmwareService;
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
 * 虚拟直播表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/vmwarelive")
public class LiveVmwareController extends BaseController {
    @Autowired
    public EhbLiveVmwareService ehbLiveVmwareService;

    @GetMapping("/list")
    public String list() {
        return "vmwarelive/list";
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param ehbLiveVmware
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbLiveVmware ehbLiveVmware, String startTime, String endTime) {
        QueryWrapper<EhbLiveVmware> ehbLiveVmwareQueryWrapper = new QueryWrapper<>();
        ehbLiveVmwareQueryWrapper.like(!StringUtils.isEmpty(ehbLiveVmware.getMainTitle()),"main_title",ehbLiveVmware.getMainTitle());
        ehbLiveVmwareQueryWrapper.like(!StringUtils.isEmpty(ehbLiveVmware.getSubTitle()),"sub_title",ehbLiveVmware.getSubTitle());
        ehbLiveVmwareQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbLiveVmwareQueryWrapper.orderByDesc("sort");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbLiveVmware> ehbLiveVmwares = ehbLiveVmwareService.list(ehbLiveVmwareQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbLiveVmwares);

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
        EhbLiveVmware ehbLiveVmware = new EhbLiveVmware();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbLiveVmware = ehbLiveVmwareService.getById(id);
        }
        modelMap.put("ehbLiveVmware",ehbLiveVmware);

        return "vmwarelive/input";
    }

    /**
     * 添加或者修改
     * @param ehbLiveVmware
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbLiveVmware ehbLiveVmware) {
        if(StringUtils.isEmpty(ehbLiveVmware.getId())){
            ehbLiveVmware.setCreatetime(LocalDateTime.now());
            ehbLiveVmware.setLiveStatus(0);//即将开始
        }else{
        }
        if(ehbLiveVmwareService.saveOrUpdate(ehbLiveVmware)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除
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
        ehbLiveVmwareService.removeById(id);
        return setResultSuccess();
    }
}