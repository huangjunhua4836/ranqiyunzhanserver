package com.yl.soft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.CrmOrganization;
import com.yl.soft.service.CrmOrganizationService;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 机构表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-03
 */
@Controller
@RequestMapping("/crmOrganization")
public class CrmOrganizationController extends BaseController {
    @Autowired
    CrmOrganizationService crmOrganizationService;

    @GetMapping("/orgList")
    public String orgList() {
        return "org/orgList";
    }

    /**
     * 查询机构列表
     * @param page
     * @param limit
     * @param crmOrganization
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/findOrgBy")
    @ResponseBody
    public TableVo findOrgBy(String page, String limit, CrmOrganization crmOrganization, String startTime,String endTime) {
        System.out.println("ok");
        QueryWrapper<CrmOrganization> crmOrganizationQueryWrapper = new QueryWrapper<>();
        crmOrganizationQueryWrapper.eq(!StringUtils.isEmpty(crmOrganization.getId()),"ID",crmOrganization.getId());
        crmOrganizationQueryWrapper.like(!StringUtils.isEmpty(crmOrganization.getOrgName()),"ORG_NAME",crmOrganization.getOrgName());
        crmOrganizationQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"CREATE_TIME",startTime,endTime);
        crmOrganizationQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
        crmOrganizationQueryWrapper.orderByDesc("CREATE_TIME");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<CrmOrganization> crmOrganizations = crmOrganizationService.list(crmOrganizationQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(crmOrganizations);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个机构添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/orgInput")
    public String orgInput(String id, String type, ModelMap modelMap) {
        CrmOrganization crmOrganization = new CrmOrganization();
        if("add".equals(type)){

        }else if("update".equals(type)){
            crmOrganization = crmOrganizationService.getById(id);
        }
        modelMap.put("crmOrganization",crmOrganization);
        return "org/orgInput";
    }

    /**
     * 添加或者修改机构
     * @param crmOrganization
     * @return
     */
    @PostMapping("/saveOrUpdateOrg")
    @ResponseBody
    public BaseResponse saveOrUpdateOrg(CrmOrganization crmOrganization) {
        if(StringUtils.isEmpty(crmOrganization.getId())){
            crmOrganization.setCreateTime(LocalDateTime.now());
            crmOrganization.setCreateUser(1);
            crmOrganization.setIsdel(false);
        }else{
            crmOrganization.setUpdateTime(LocalDateTime.now());
            crmOrganization.setUpdateUser(1);
        }
        if(crmOrganizationService.saveOrUpdate(crmOrganization)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除机构
     * @param id
     * @return
     */
    @PostMapping("/deleteOrg")
    @ResponseBody
    public BaseResponse deleteOrg(String id) {
        System.out.println("ok");
        if(StringUtils.isEmpty(id)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"机构删除ID为空！");
        }
        //要删除的机构ID列表
        crmOrganizationService.deleteOrg(id);
        return setResultSuccess();
    }

//    /**
//     * 跳转到机构树页面
//     * @return
//     */
//    @GetMapping("/orgTree")
//    public String orgTree() {
//        return "org/orgTree";
//    }
//
//    /**
//     * 刷新机构树
//     * @return
//     */
//    @GetMapping("/initOrgTree")
//    @ResponseBody
//    public String initOrgTree() {
//        BaseResponse<OutputObject> memberListBaseResponse =  memberFeign.list(new InputObject());
//        if(!isServiceBack(memberListBaseResponse)){
//            return JSON.toJSONString(memberListBaseResponse);
//        }
//
//        List memberList = memberListBaseResponse.getData().getList();
//        memberList = MapToBeanUtils.MapListToJavaBeanList(memberList, CrmMemberDO.class);
//
//        List<TreeVo> titleTreeVos = new ArrayList<>();//总树的集合
//        for(Object obj : memberList) {
//            CrmMemberDO crmMemberDO = (CrmMemberDO) obj;
//            TreeVo treeVo = new TreeVo();
//            treeVo.setId(crmMemberDO.getId());
//            treeVo.setTitle(crmMemberDO.getMember_name());
//            treeVo.setParentId(crmMemberDO.getParent_id());
//            treeVo.setSpread(true);//默认全部展开
//            treeVo.setUrl(crmMemberDO.getOrganization_id());//科室设置机构代码
//            titleTreeVos.add(treeVo);
//        }
//        List<TreeVo> memberTreeVos = createLayuiTree(titleTreeVos);//顶级科室前端json串组装
//
//        List<TreeVo> treeVos = new ArrayList<>();//机构树集合
//        //机构列表
//        BaseResponse<OutputObject> orgListBaseResponse = orgFeign.list(new InputObject());
//        if(!isServiceBack(orgListBaseResponse)){
//            return JSON.toJSONString(orgListBaseResponse);
//        }
//        List orgList = MapToBeanUtils.MapListToJavaBeanList(orgListBaseResponse.getData().getList(), CrmOrganizationDO.class);
//        for(Object obj : orgList){
//            List<TreeVo> childTreeVos = new ArrayList<>();
//            CrmOrganizationDO crmOrganizationDO = (CrmOrganizationDO) obj;
//            TreeVo treeVo = new TreeVo();//每个机构是一个节点
//            treeVo.setId(crmOrganizationDO.getId());
//            treeVo.setTitle(crmOrganizationDO.getOrg_name());
//            treeVo.setSpread(true);
//            for(TreeVo memberTreeVo : memberTreeVos){
//                //顶级科室按照机构进行分类存放
//                if(memberTreeVo.getUrl().equals(crmOrganizationDO.getId())){
//                    childTreeVos.add(memberTreeVo);
//                }
//            }
//            treeVo.setChildren(childTreeVos);//每个机构下加入顶级科室
//            treeVos.add(treeVo);
//        }
//        return JSON.toJSONString(new BaseServiceImpl().setResultSuccess(treeVos));
//    }
//
//    /**
//     * 根据ID查询机构/科室详情
//     * @param id
//     * @param type
//     * @return
//     */
//    @RequestMapping("/findOrgOrMemberTreeById")
//    @ResponseBody
//    public String findOrgOrMemberTreeById(String id,String type) {
//        if(StringUtils.isEmpty(type)){
//            BaseResponse baseResponse = orgFeign.find(id);
//            return JSON.toJSONString(baseResponse);
//        }else{
//            BaseResponse baseResponse = memberFeign.find(id);
//            return JSON.toJSONString(baseResponse);
//        }
//    }
}