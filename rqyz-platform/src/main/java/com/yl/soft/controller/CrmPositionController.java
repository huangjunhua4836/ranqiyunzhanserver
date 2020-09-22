package com.yl.soft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.CrmPosition;
import com.yl.soft.service.CrmPositionService;
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
 * 岗位表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/crmPosition")
public class CrmPositionController extends BaseController {
    @Autowired
    public CrmPositionService crmPositionService;

    @GetMapping("/positionList")
    public String positionList() {
        return "position/positionList";
    }

    /**
     * 查询岗位列表
     * @param page
     * @param limit
     * @param crmPosition
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/findPositionBy")
    @ResponseBody
    public TableVo findPositionBy(String page, String limit, CrmPosition crmPosition, String startTime, String endTime) {
        System.out.println("ok");
        QueryWrapper<CrmPosition> crmPositionQueryWrapper = new QueryWrapper<>();
        crmPositionQueryWrapper.eq(!StringUtils.isEmpty(crmPosition.getId()),"ID",crmPosition.getId());
        crmPositionQueryWrapper.like(!StringUtils.isEmpty(crmPosition.getName()),"NAME",crmPosition.getName());
        crmPositionQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"CREATE_TIME",startTime,endTime);
        crmPositionQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
        crmPositionQueryWrapper.orderByDesc("CREATE_TIME");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<CrmPosition> crmOrganizations = crmPositionService.list(crmPositionQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(crmOrganizations);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个岗位添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/positionInput")
    public String positionInput(String id, String type, ModelMap modelMap) {
        CrmPosition crmPosition = new CrmPosition();
        if("add".equals(type)){

        }else if("update".equals(type)){
            crmPosition = crmPositionService.getById(id);
        }
        modelMap.put("crmPosition",crmPosition);

        return "position/positionInput";
    }

    /**
     * 添加或者修改岗位
     * @param crmPosition
     * @return
     */
    @PostMapping("/saveOrUpdatePosition")
    @ResponseBody
    public BaseResponse saveOrUpdatePosition(CrmPosition crmPosition) {
        if(StringUtils.isEmpty(crmPosition.getId())){
            crmPosition.setCreateTime(LocalDateTime.now());
            crmPosition.setCreateUser(1);
            crmPosition.setIsdel(false);
        }else{
            crmPosition.setUpdateTime(LocalDateTime.now());
            crmPosition.setUpdateUser(1);
        }
        if(crmPositionService.saveOrUpdate(crmPosition)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除岗位
     * @return
     */
    @PostMapping("/deletePosition")
    @ResponseBody
    public BaseResponse deletePosition(String id) {
        System.out.println("ok");
        if(StringUtils.isEmpty(id)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"岗位删除ID为空！");
        }
        crmPositionService.deletePosition(id);
        return setResultSuccess();
    }
}