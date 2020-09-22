package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.po.CrmMember;

/**
 * <p>
 * 机构成员/科室 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
public interface CrmMemberService extends IService<CrmMember> {
    /**
     * 1、删除科室
     * 2、科室下的子科室
     * 3、科室下的用户
     * 4、用户对应的角色
     * 5、用户对应的岗位
     *
     * @param id
     */
    void deleteMember(String id);
}
