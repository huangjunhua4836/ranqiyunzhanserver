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
 * 参展信息
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/exhibitionInfo")
public class ExhibitionInfoController extends BaseController {
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

    @GetMapping("/list2")
    public String list2() {
        return "exhibitioninfo/list2";
    }

//    /**
//     * 添加或者修改页面
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