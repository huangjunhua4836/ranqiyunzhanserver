package com.yl.soft.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dict.CrmMenuDict;
import com.yl.soft.po.CrmMenu;
import com.yl.soft.service.CrmMenuService;
import com.yl.soft.vo.TreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-03
 */
@Controller
@RequestMapping("/crmMenu")
public class CrmMenuController extends BaseController {
    @Autowired
    public CrmMenuService crmMenuService;

    /**
     * 跳转到菜单树页面
     * @return
     */
    @GetMapping("/menuTree")
    public String tree() {
        return "menu/tree";
    }

    /**
     * 刷新菜单树
     * @return
     */
    @GetMapping("/initMenuTree")
    @ResponseBody
    public BaseResponse initMenuTree() {
        QueryWrapper<CrmMenu> crmMenuQueryWrapper = new QueryWrapper<>();
        crmMenuQueryWrapper.eq("ISDEL",CommonDict.CORRECT_STATE);
        crmMenuQueryWrapper.orderByAsc("SORT");//升序排列
        List<CrmMenu> crmMenus = crmMenuService.list(crmMenuQueryWrapper);

        List<TreeVo> totalTreeVos = new ArrayList<>();//总树的集合
        for(CrmMenu crmMenu : crmMenus) {
            TreeVo treeVo = new TreeVo();
            BeanUtil.copyProperties(crmMenu, treeVo, new CopyOptions().ignoreNullValue());
            treeVo.setpId(crmMenu.getParentId());
            treeVo.setOpen(true);//默认全部展开
            totalTreeVos.add(treeVo);
        }
        return setResultSuccess(totalTreeVos);
    }

    /**
     * 查看单个菜单详情
     * @param id
     * @return
     */
    @GetMapping("/findMenuById")
    @ResponseBody
    public BaseResponse findMenuById(String id) {
        CrmMenu crmMenu = crmMenuService.getById(id);
//        crmMenu.setType(Integer.valueOf(CrmMenuDict.MENUTYPEMAP.get(crmMenu.getType())+""));
//        crmMenu.setIsmust(Integer.valueOf(CrmMenuDict.MENUMUSTMAP.get(crmMenu.getIsmust())+""));

//        return JSON.toJSONStringWithDateFormat(setResultSuccess(crmMenu),"yyyy-MM-dd HH:mm:ss");
        return setResultSuccess(crmMenu);
    }

    /**
     * 跳转到单个菜单添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/menuInput")
    public String menuInput(String id,String type, ModelMap modelMap) {
        CrmMenu crmMenu = new CrmMenu();
        if("add".equals(type)){
            crmMenu = new CrmMenu();
            crmMenu.setParentId(StringUtils.isEmpty(id)?null:Integer.valueOf(id));
        }else if("update".equals(type)){
            crmMenu = crmMenuService.getById(id);
        }
        modelMap.put("crmMenu",crmMenu);
        modelMap.put("menuTypeMap",CrmMenuDict.MENUTYPEMAP);//字典当中取出菜单类型
        return "menu/menuInput";
    }

    /**
     * 添加或者修改菜单
     * @param crmMenu
     * @return
     */
    @PostMapping("/saveOrUpdateMenu")
    @ResponseBody
    public BaseResponse saveOrUpdateMenu(CrmMenu crmMenu) {
        if(StringUtils.isEmpty(crmMenu.getId())){
            crmMenu.setCreateTime(LocalDateTime.now());
            crmMenu.setCreateUser(1);
            crmMenu.setIsdel(false);
        }else{
            crmMenu.setUpdateTime(LocalDateTime.now());
            crmMenu.setUpdateUser(1);
        }
        if(crmMenuService.saveOrUpdate(crmMenu)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @PostMapping("/deleteMenu")
    @ResponseBody
    public BaseResponse deleteMenu(String id) {
        System.out.println("ok");
        if(StringUtils.isEmpty(id)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"菜单删除ID为空！");
        }
        crmMenuService.deleteMenu(id);
        return setResultSuccess();
    }
}