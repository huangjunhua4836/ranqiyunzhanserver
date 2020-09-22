package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.po.CrmMenu;
import com.yl.soft.po.CrmRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-05
 */
public interface CrmRoleService extends IService<CrmRole> {

    /**
     * 通过角色查找菜单集合
     * @param roleId
     * @return
     */
    List<CrmMenu> getMenusByRoleId(Integer roleId);

    /**
     * 1、删除角色
     * 2、删除该角色用户关系
     * 3、删除该角色菜单关系
     * @param roleId
     */
    void deleteRole(String roleId);
}
