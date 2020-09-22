package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.CrmRoleUser;
import com.yl.soft.po.CrmUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-05
 */
public interface CrmUserService extends IService<CrmUser> {
    /**
     * 用户登录
     * @param userCode
     * @return
     */
    SessionUser loginByUserCode(String userCode);

    /**
     * 保存用户、用户角色关系
     * @param crmUser
     * @param crmUser
     * @return
     */
    boolean saveOrUpdateUser(CrmUser crmUser, Integer roleId);

    void deleteUser(Integer id);
}
