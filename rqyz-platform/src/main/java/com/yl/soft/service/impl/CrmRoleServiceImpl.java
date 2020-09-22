package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dict.CrmUserDict;
import com.yl.soft.mapper.CrmMenuMapper;
import com.yl.soft.mapper.CrmMenuRoleMapper;
import com.yl.soft.mapper.CrmRoleMapper;
import com.yl.soft.mapper.CrmRoleUserMapper;
import com.yl.soft.po.CrmMenu;
import com.yl.soft.po.CrmMenuRole;
import com.yl.soft.po.CrmRole;
import com.yl.soft.po.CrmRoleUser;
import com.yl.soft.service.CrmRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-05
 */
@Service
public class CrmRoleServiceImpl extends ServiceImpl<CrmRoleMapper, CrmRole> implements CrmRoleService {
    @Autowired
    private CrmMenuMapper crmMenuMapper;

    @Autowired
    private CrmRoleUserMapper crmRoleUserMapper;

    @Autowired
    private CrmMenuRoleMapper crmMenuRoleMapper;

    @Override
    public List<CrmMenu> getMenusByRoleId(Integer roleId) {
        QueryWrapper<CrmMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id","SELECT menuId FROM crm_menu_role WHERE roleId = "+roleId);
        queryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        List<CrmMenu> list  = crmMenuMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public void deleteRole(String roleId) {
        //删除该角色
        CrmRole delRole = baseMapper.selectById(roleId);
        delRole.setIsdel(true);
        baseMapper.updateById(delRole);

        //删除该角色用户关系
        QueryWrapper<CrmRoleUser> delRUWrapper = new QueryWrapper<>();
        delRUWrapper.eq("RULE_ID",roleId);
        crmRoleUserMapper.delete(delRUWrapper);

        //删除该角色菜单关系
        QueryWrapper<CrmMenuRole> delMRWrapper = new QueryWrapper<>();
        delMRWrapper.eq("ROLE_ID",roleId);
        crmMenuRoleMapper.delete(delMRWrapper);
    }
}