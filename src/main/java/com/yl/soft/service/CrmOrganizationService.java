package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.po.CrmOrganization;

/**
 * <p>
 * 机构表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-03
 */
public interface CrmOrganizationService extends IService<CrmOrganization> {
    /**
     * 1、删除机构
     * 2、机构下的科室
     * 3、科室下的用户
     * 4、用户对应的角色
     * 5、用户对应的岗位
     *
     * @param id
     */
    void deleteOrg(String id);
}
