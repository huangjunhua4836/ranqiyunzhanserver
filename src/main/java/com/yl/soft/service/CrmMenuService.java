package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.po.CrmMenu;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-03
 */
public interface CrmMenuService extends IService<CrmMenu> {
    /**
     * 1、删除菜单
     * 2、对应的子菜单
     * 3、菜单对应的角色关系也应该删掉
     * @param id
     */
    void deleteMenu(String id);
}