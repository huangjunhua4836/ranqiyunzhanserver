package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.CrmRole;
import com.yl.soft.service.CrmRoleService;
import com.yl.soft.service.StationinfoService;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 站内信表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/stationinfo")
public class StationinfoController extends BaseController {
    @Autowired
    public StationinfoService stationinfoService;

    @GetMapping("/list")
    public String list() {
        return "stationinfo/list";
    }

//    /**
//     * 查询列表
//     * @param page
//     * @param limit
//     * @param crmRole
//     * @param startTime
//     * @param endTime
//     * @return
//     */
//    @GetMapping("/initTable")
//    @ResponseBody
//    public TableVo initTable(String page, String limit, CrmRole crmRole, String startTime, String endTime) {
//        QueryWrapper<CrmRole> crmRoleQueryWrapper = new QueryWrapper<>();
//        crmRoleQueryWrapper.eq(!StringUtils.isEmpty(crmRole.getId()),"ID",crmRole.getId());
//        crmRoleQueryWrapper.like(!StringUtils.isEmpty(crmRole.getName()),"NAME",crmRole.getName());
//        crmRoleQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"CREATE_TIME",startTime,endTime);
//        crmRoleQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
//        crmRoleQueryWrapper.orderByDesc("CREATE_TIME");
//        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
//        List<CrmRole> crmRoles = crmRoleService.list(crmRoleQueryWrapper);
//        PageInfo pageInfo = new PageInfo<>(crmRoles);
//
//        TableVo tableVo = new TableVo();
//        tableVo.setCode(0);
//        tableVo.setMsg("");
//        tableVo.setCount((int)pageInfo.getTotal());
//        tableVo.setData(pageInfo.getList());
//        return tableVo;
//    }

//    /**
//     * 跳转到单个添加或者修改页面
//     * @param id
//     * @return
//     */
//    @GetMapping("/input")
//    public String input(String id, String type, ModelMap modelMap) {
//        CrmRole crmRole = new CrmRole();
//        if("add".equals(type)){
//
//        }else if("update".equals(type)){
//            crmRole = crmRoleService.getById(id);
//        }
//        modelMap.put("crmRole",crmRole);
//
//        return "role/input";
//    }
//
//    /**
//     * 添加或者修改
//     * @param crmRole
//     * @return
//     */
//    @PostMapping("/saveOrUpdate")
//    @ResponseBody
//    public BaseResponse saveOrUpdate(CrmRole crmRole) {
//        if(StringUtils.isEmpty(crmRole.getId())){
//            crmRole.setCreatetime(LocalDateTime.now());
//            crmRole.setCreateuser(1);
//            crmRole.setIsdel(false);
//        }else{
//            crmRole.setUpdatetime(LocalDateTime.now());
//            crmRole.setUpdateuser(1);
//        }
//        if(crmRoleService.saveOrUpdate(crmRole)){
//            return setResultSuccess();
//        }else{
//            return setResultError("操作失败！");
//        }
//    }
//
//    /**
//     * 删除
//     * @return
//     */
//    @PostMapping("/delete")
//    @ResponseBody
//    public BaseResponse delete(String id) {
//        System.out.println("ok");
//        if(StringUtils.isEmpty(id)){
//            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
//                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"岗位删除ID为空！");
//        }
//        crmRoleService.deleteRole(id);
//        return setResultSuccess();
//    }
}