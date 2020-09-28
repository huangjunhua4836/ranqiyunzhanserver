package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.EhbLabel;
import com.yl.soft.service.EhbLabelService;
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
 * 标签表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/label")
public class LabelController extends BaseController {
    @Autowired
    public EhbLabelService ehbLabelService;

    @GetMapping("/list")
    public String list() {
        return "label/list";
    }

    /**
     * 查询标签列表
     * @param page
     * @param limit
     * @param ehbLabel
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbLabel ehbLabel, String startTime, String endTime) {
        QueryWrapper<EhbLabel> ehbLabelQueryWrapper = new QueryWrapper<>();
        ehbLabelQueryWrapper.like(!StringUtils.isEmpty(ehbLabel.getName()),"name",ehbLabel.getName());
        ehbLabelQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbLabelQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        ehbLabelQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbLabel> ehbLabels = ehbLabelService.list(ehbLabelQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbLabels);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个标签添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbLabel ehbLabel = new EhbLabel();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbLabel = ehbLabelService.getById(id);
        }
        modelMap.put("ehbLabel",ehbLabel);

        return "label/input";
    }

    /**
     * 添加或者修改标签
     * @param ehbLabel
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbLabel ehbLabel) {
        if(StringUtils.isEmpty(ehbLabel.getId())){
            ehbLabel.setCreatetime(LocalDateTime.now());
            ehbLabel.setCreateuser(1);
            ehbLabel.setIsdel(false);
        }else{
            ehbLabel.setUpdatetime(LocalDateTime.now());
            ehbLabel.setUpdateuser(1);
        }
        if(ehbLabelService.saveOrUpdate(ehbLabel)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除标签
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
        ehbLabelService.removeById(id);
        return setResultSuccess();
    }
}