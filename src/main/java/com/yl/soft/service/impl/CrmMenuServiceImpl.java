package com.yl.soft.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.mapper.CrmMenuMapper;
import com.yl.soft.mapper.CrmMenuRoleMapper;
import com.yl.soft.po.CrmMenu;
import com.yl.soft.po.CrmMenuRole;
import com.yl.soft.service.CrmMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-03
 */
@Service
public class CrmMenuServiceImpl extends ServiceImpl<CrmMenuMapper, CrmMenu> implements CrmMenuService {
   @Autowired
   private CrmMenuRoleMapper crmMenuRoleMapper;

   @Transactional
    @Override
    public void deleteMenu(String id) {
        //查询子菜单
        QueryWrapper<CrmMenu> crmMenuQueryWrapper = new QueryWrapper<>();
        crmMenuQueryWrapper.select("ID");
        crmMenuQueryWrapper.eq("PARENT_ID", id);
        crmMenuQueryWrapper.eq("ISDEL", CommonDict.CORRECT_STATE);
        List<CrmMenu> crmMenuList = baseMapper.selectList(crmMenuQueryWrapper);
        Set ids = new HashSet<>();
        for(CrmMenu crmMenu : crmMenuList){
            ids.add(crmMenu.getId());
        }
        ids.add(id);//添加主菜单
        QueryWrapper<CrmMenu> deleteMenuQueryWrapper = new QueryWrapper<>();
        deleteMenuQueryWrapper.in("ID",ArrayUtil.toArray(ids,Object.class));
        CrmMenu deleteCrmMenu = new CrmMenu();
        deleteCrmMenu.setIsdel(true);
        //主菜单、子菜单删除
        baseMapper.update(deleteCrmMenu,deleteMenuQueryWrapper);
        //删除菜单角色关系
        QueryWrapper<CrmMenuRole> deleteMenuRoleWrapper = new QueryWrapper<>();
        deleteMenuRoleWrapper.in("MENU_ID",ArrayUtil.toArray(ids,Object.class));
        crmMenuRoleMapper.delete(deleteMenuRoleWrapper);
    }
}
