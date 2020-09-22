package com.yl.soft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.service.BaseResponseUtil;
import com.yl.soft.common.util.MD5Util;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.*;
import com.yl.soft.service.*;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/crmUser")
public class CrmUserController extends BaseResponseUtil {
    @Autowired
    public CrmUserService crmUserService;

    @Autowired
    public CrmOrganizationService crmOrganizationService;

    @Autowired
    public CrmPositionService crmPositionService;

    @Autowired
    public CrmRoleService crmRoleService;

    @Autowired
    public CrmRoleUserService crmRoleUserService;

    @RequestMapping("/userList")
    public String userList(ModelMap modelMap) {
        //机构列表
        QueryWrapper<CrmOrganization> orgWrapper = new QueryWrapper<>();
        orgWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        List<CrmOrganization> orgList = crmOrganizationService.list(orgWrapper);
        modelMap.put("orgList", orgList);

        //岗位列表
        QueryWrapper<CrmPosition> positionWrapper = new QueryWrapper<>();
        positionWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        List<CrmPosition> positionList = crmPositionService.list(positionWrapper);
        modelMap.put("positionList", positionList);

        //角色列表
        QueryWrapper<CrmRole> roleWrapper = new QueryWrapper<>();
        roleWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        List<CrmRole> roleList = crmRoleService.list(roleWrapper);
        modelMap.put("roleList", roleList);

        return "user/userList";
    }

    /**
     * 查询用户列表
     * @param page
     * @param limit
     * @param crmUser
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("/findUserBy")
    @ResponseBody
    public TableVo findUserBy(String page, String limit, CrmUser crmUser, String startTime, String endTime) {
        System.out.println("ok");
        QueryWrapper<CrmUser> crmUserQueryWrapper = new QueryWrapper<>();
        crmUserQueryWrapper.eq(!StringUtils.isEmpty(crmUser.getUserCode()),"userCode",crmUser.getUserCode());
        crmUserQueryWrapper.like(!StringUtils.isEmpty(crmUser.getNickname()),"nickname",crmUser.getNickname());
        crmUserQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createTime",startTime,endTime);
        crmUserQueryWrapper.eq(!StringUtils.isEmpty(crmUser.getOrganizationId()),"organizationId",crmUser.getOrganizationId());
        crmUserQueryWrapper.eq(!StringUtils.isEmpty(crmUser.getMemberId()),"memberId",crmUser.getMemberId());
        crmUserQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        crmUserQueryWrapper.orderByDesc("createTime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<CrmUser> crmUsers = crmUserService.list(crmUserQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(crmUsers);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个用户添加或者修改页面
     * @param id
     * @return
     */
    @RequestMapping("/userInput")
    public String userInput(String id,String type, ModelMap modelMap) {
        //角色列表
        QueryWrapper<CrmRole> crmRoleQueryWrapper = new QueryWrapper<>();
        crmRoleQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        List<CrmRole> crmRoles = crmRoleService.list(crmRoleQueryWrapper);
        modelMap.put("crmRoles",crmRoles);

        CrmUser crmUser = new CrmUser();
        if("add".equals(type)){

        }else if("update".equals(type)){
            crmUser = crmUserService.getById(id);
            //查询用户角色
            QueryWrapper<CrmRoleUser> crmRoleUserQueryWrapper = new QueryWrapper<>();
            crmRoleUserQueryWrapper.eq("userId",id);
            CrmRoleUser crmRoleUser = crmRoleUserService.getOne(crmRoleUserQueryWrapper);
            modelMap.put("crmRoleUser",crmRoleUser);
        }
        modelMap.put("crmUser",crmUser);
        return "user/userInput";
    }

    /**
     * 添加或者修改用户
     * @param crmUser
     * @return
     */
    @PostMapping("/saveOrUpdateUser")
    @ResponseBody
    public BaseResponse saveOrUpdateUser(CrmUser crmUser, Integer roleId) {
        if(StringUtils.isEmpty(crmUser.getId())){
            crmUser.setCreateTime(LocalDateTime.now());
            crmUser.setCreateUser(1);
            crmUser.setIsdel(false);
            crmUser.setPassword(MD5Util.MD5("123456"));
        }else{
            crmUser.setUpdateTime(LocalDateTime.now());
            crmUser.setUpdateUser(1);
        }
        if(crmUserService.saveOrUpdateUser(crmUser,roleId)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @PostMapping("/deleteUser")
    @ResponseBody
    public BaseResponse deleteUser(Integer userId) {
        System.out.println("ok");

        if(StringUtils.isEmpty(userId)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"用户删除ID为空！");
        }
        crmUserService.deleteUser(userId);
        return setResultSuccess();
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @PostMapping("/restPassword")
    @ResponseBody
    public BaseResponse restPassword(Integer userId) {
        System.out.println("ok");

        if(StringUtils.isEmpty(userId)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"用户ID为空！");
        }
        CrmUser crmUser = crmUserService.getById(userId);
        crmUser.setPassword(MD5Util.MD5("123456"));
        crmUserService.updateById(crmUser);
        return setResultSuccess();
    }
//
//    /**
//     * 跳转到权限树页面
//     * @return
//     */
//    @RequestMapping("/auz")
//    public String auz(String userId,ModelMap modelMap,HttpServletResponse response) {
//        BaseResponse baseResponse = userFeign.findUserLeafsAuzs(userId);
//        if(!isServiceBack(baseResponse)){
//            IOUtil.printJsonToPage(response,baseResponse);
//            return null;
//        }
//        List<CrmMenuUserDO> list = MapToBeanUtils.MapListToJavaBeanList((List)baseResponse.getData(),CrmMenuUserDO.class);
//        StringBuffer checkedIds = new StringBuffer("[");
//        for(CrmMenuUserDO crmMenuUserDO : list){
//            checkedIds.append("\""+crmMenuUserDO.getMenu_id()+"\"");
//            checkedIds.append(",");
//        }
//        int index = checkedIds.lastIndexOf(",");
//        if(index > 0){
//            checkedIds.deleteCharAt(index);
//        }
//        checkedIds.append("]");
//        modelMap.put("checkedIds",checkedIds);
//        modelMap.put("userId",userId);
//        return "user/auzTree";
//    }
//
//    /**
//     * 操作用户权限关系
//     * @param userId
//     * @param checkedMenus
//     * @return
//     */
//    @PostMapping("/auzUserOpear")
//    @ResponseBody
//    public String auzUserOpear(String userId,String checkedMenus) {
//        List<CrmMenuUserDO> list = new ArrayList<>();//总权限集合
//        JSONArray jsonArray = JSON.parseArray(checkedMenus);//转成json数组对象
//        Map<String,List<JSONObject>> map = gettrunks_Leafs(new ArrayList<JSONObject>(),new ArrayList<JSONObject>(),jsonArray);//取出叶子节点集合
//        //得到所有的叶子节点
//        List<JSONObject> leafs = map.get("leafs");
//        for(JSONObject jsonObject : leafs){
//            CrmMenuUserDO crmMenuUserDO = new CrmMenuUserDO();
//            crmMenuUserDO.setUser_id(userId);
//            crmMenuUserDO.setMenu_id(jsonObject.getString("id"));
//            crmMenuUserDO.setIs_leaf(CrmMenuUserDict.IS_LEAF_TRUE);
//            list.add(crmMenuUserDO);
//        }
//        //得到所有的树干节点
//        List<JSONObject> trunks = map.get("trunks");
//        for(JSONObject jsonObject : trunks){
//            CrmMenuUserDO crmMenuUserDO = new CrmMenuUserDO();
//            crmMenuUserDO.setUser_id(userId);
//            crmMenuUserDO.setMenu_id(jsonObject.getString("id"));
//            crmMenuUserDO.setIs_leaf(CrmMenuUserDict.IS_LEAF_FALSE);
//            list.add(crmMenuUserDO);
//        }
//        BaseResponse baseResponse = userFeign.auzUserOpear(list);
//        return JSON.toJSONString(baseResponse);
//    }
//
//    /**
//     * 复制授权页面
//     * @param userId
//     * @return
//     */
//    @RequestMapping("/auzCopyInput")
//    public String auzCopyInput(String userId,ModelMap modelMap) {
//        modelMap.put("userId",userId);
//        return "user/auzCopyInput";
//    }
//
//    /**
//     * 授权复制用户列表穿梭框
//     * @return
//     */
//    @RequestMapping("/auzCopyUsers")
//    @ResponseBody
//    public String auzCopyUsers(String userId) {
//        BaseResponse<OutputObject> baseResponse =  userFeign.list(new InputObject());
//        if(!isServiceBack(baseResponse)){
//            return JSON.toJSONString(baseResponse);
//        }
//        List list = MapToBeanUtils.MapListToJavaBeanList(baseResponse.getData().getList(),CrmUserDTO.class);
//        List<TransferVo> transferVos = new ArrayList<>();
//        for(Object o : list){
//            CrmUserDTO crmUserDTO = (CrmUserDTO) o;
//            if(userId.equals(crmUserDTO.getId()) || CrmUserDict.SUPERADMIN_CODE.equals(crmUserDTO.getId())){
//                continue;
//            }
//            TransferVo transferVo = new TransferVo();
//            transferVo.setValue(crmUserDTO.getId());
//            transferVo.setTitle(crmUserDTO.getUser_code());
//            transferVos.add(transferVo);
//        }
//        return JSON.toJSONString(new BaseServiceImpl().setResultSuccess(transferVos));
//    }
//
//    /**
//     * 授权复制用户操作
//     * @return
//     */
//    @PostMapping("/auzCopyOpear")
//    @ResponseBody
//    public String auzCopyOpear(String userId,String checkUserData) {
//        JSONArray jsonArray = JSON.parseArray(checkUserData);//转成json数组对象
//        List ids = new ArrayList();
//        ids.add(userId);//添加目标用户ID
//        for(int i=0;i<jsonArray.size();i++){
//            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//            ids.add(jsonObject.getString("value"));//添加被复制用户ID
//        }
//        BaseResponse baseResponse = userFeign.auzCopyOpear(ids);
//        return JSON.toJSONString(baseResponse);
//    }
}