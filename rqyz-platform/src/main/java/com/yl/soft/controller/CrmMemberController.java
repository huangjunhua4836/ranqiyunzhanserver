package com.yl.soft.controller;


import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.CrmMember;
import com.yl.soft.po.CrmOrganization;
import com.yl.soft.service.CrmMemberService;
import com.yl.soft.service.CrmOrganizationService;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 机构表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-03
 */
@Controller
@RequestMapping("/crmMember")
public class CrmMemberController extends BaseController {
    @Autowired
    public CrmMemberService crmMemberService;

    @Autowired
    public CrmOrganizationService crmOrganizationService;

    @GetMapping("/memberList")
    public String orgList(ModelMap modelMap) {
        QueryWrapper<CrmOrganization> orgWapper = new QueryWrapper<>();
        orgWapper.eq("ISDEL", CommonDict.CORRECT_STATE);
        List<CrmOrganization> organizationList = crmOrganizationService.list(orgWapper);//机构列表
        modelMap.put("orgList", organizationList);
        return "member/memberList";
    }

    /**
     * 根据机构查询科室
     * @param orgId
     * @return
     */
    @GetMapping("/findMemberByOrg")
    @ResponseBody
    public BaseResponse findMemberByOrg(String orgId, String memberId) {
        if(StringUtils.isEmpty(orgId)){//如果机构ID为空，则直接返回
            return setResultSuccess();
        }
        //查询指定机构下所有科室的集合
        QueryWrapper<CrmMember> memberWrapper = new QueryWrapper<>();
        memberWrapper.eq("ORGANIZATION_ID",orgId);
        memberWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
        List<CrmMember> crmMembers = new ArrayList<>();
        if(!StringUtils.isEmpty(memberId)){
            Set notMemberIds = new HashSet();//科室本身以及其子科室
            notMemberIds.add(memberId);
            memberWrapper.eq("PARENT_ID",memberId);
            List<CrmMember> notMembers = crmMemberService.list(memberWrapper);
            for(CrmMember crmMember : notMembers){
                notMemberIds.add(crmMember.getId());
            }

            //排除科室本身以及子科室之后剩余的科室
            QueryWrapper<CrmMember> resultWapper = new QueryWrapper<>();
            resultWapper.eq("ORGANIZATION_ID",orgId);
            resultWapper.eq("ISDEL", CommonDict.CORRECT_STATE);
            resultWapper.notIn("ID", ArrayUtil.toArray(notMemberIds,Object.class));
            crmMembers = crmMemberService.list(resultWapper);
        }else{
            crmMembers = crmMemberService.list(memberWrapper);
        }
        return setResultSuccess(crmMembers);
    }

    /**
     * 查询科室列表
     * @param page
     * @param limit
     * @param crmMember
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("/findMemberBy")
    @ResponseBody
    public TableVo findMemberBy(String page, String limit, CrmMember crmMember, String startTime, String endTime) {
        QueryWrapper<CrmMember> crmMemberQueryWrapper = new QueryWrapper<>();
        crmMemberQueryWrapper.eq(!StringUtils.isEmpty(crmMember.getId()),"ID",crmMember.getId());
        crmMemberQueryWrapper.like(!StringUtils.isEmpty(crmMember.getMemberName()),"MEMBER_NAME",crmMember.getMemberName());
        crmMemberQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"CREATE_TIME",startTime,endTime);
        crmMemberQueryWrapper.eq(!StringUtils.isEmpty(crmMember.getOrganizationId()),"ORGANIZATION_ID", crmMember.getOrganizationId());
        crmMemberQueryWrapper.eq(!StringUtils.isEmpty(crmMember.getParentId()),"PARENT_ID", crmMember.getParentId());
        crmMemberQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
        crmMemberQueryWrapper.orderByDesc("CREATE_TIME");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<CrmMember> crmMembers = crmMemberService.list(crmMemberQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(crmMembers);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个科室添加或者修改页面
     * @param id
     * @return
     */
    @RequestMapping("/memberInput")
    public String memberInput(String id,String type, ModelMap modelMap) {
        CrmMember crmMember = new CrmMember();
        if("add".equals(type)){

        }else if("update".equals(type)){
            crmMember = crmMemberService.getById(id);
        }
        modelMap.put("crmMember",crmMember);

        //查询机构列表
        QueryWrapper<CrmOrganization> orgWrapper = new QueryWrapper<>();
        orgWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
        List<CrmOrganization> orgList = crmOrganizationService.list(orgWrapper);
        modelMap.put("orgList", orgList);

        return "member/memberInput";
    }

    /**
     * 添加或者修改科室
     * @param crmMember
     * @return
     */
    @PostMapping("/saveOrUpdateMember")
    @ResponseBody
    public BaseResponse saveOrUpdateMember(CrmMember crmMember) {
        if(StringUtils.isEmpty(crmMember.getId())){
            crmMember.setCreateTime(LocalDateTime.now());
            crmMember.setCreateUser(1);
            crmMember.setIsdel(false);
        }else{
            crmMember.setUpdateTime(LocalDateTime.now());
            crmMember.setUpdateUser(1);
        }
        if(crmMemberService.saveOrUpdate(crmMember)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除科室
     * @return
     */
    @PostMapping("/deleteMember")
    @ResponseBody
    public BaseResponse deleteMember(String memberId) {
        if(StringUtils.isEmpty(memberId)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"科室删除ID为空！");
        }
        crmMemberService.deleteMember(memberId);
        return setResultSuccess();
    }
}