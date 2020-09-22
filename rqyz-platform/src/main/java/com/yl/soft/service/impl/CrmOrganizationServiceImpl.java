package com.yl.soft.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.mapper.*;
import com.yl.soft.po.*;
import com.yl.soft.service.CrmOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 机构表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-03
 */
@Service
public class CrmOrganizationServiceImpl extends ServiceImpl<CrmOrganizationMapper, CrmOrganization> implements CrmOrganizationService {
    @Autowired
    private CrmMemberMapper crmMemberMapper;

    @Autowired
    private CrmUserMapper crmUserMapper;

    @Autowired
    private CrmRoleUserMapper crmRoleUserMapper;

    @Autowired
    private CrmPositionUserMapper crmPositionUserMapper;

    @Transactional
    @Override
    public void deleteOrg(String id) {
        //删除机构
        CrmOrganization deleteOrg = baseMapper.selectById(id);
        deleteOrg.setIsdel(true);
        baseMapper.updateById(deleteOrg);

        //机构下的科室
        CrmMember deleteMember = new CrmMember();
        deleteMember.setIsdel(true);
        QueryWrapper<CrmMember> deleteMemberWrapper = new QueryWrapper<>();
        deleteMemberWrapper.eq("ORGANIZATION_ID",id);
        crmMemberMapper.update(deleteMember,deleteMemberWrapper);

        //科室下的用户
        CrmUser deleteUser = new CrmUser();
        deleteUser.setIsdel(true);
        QueryWrapper<CrmUser> deleteUserWrapper = new QueryWrapper<>();
        deleteUserWrapper.eq("ORGANIZATION_ID",id);
        crmUserMapper.update(deleteUser,deleteUserWrapper);

        //用户对应的角色记录
        Set userIds = new HashSet();
        List<CrmUser> crmUsers = crmUserMapper.selectList(deleteUserWrapper);
        for(CrmUser crmUser : crmUsers){
            userIds.add(crmUser.getId());
        }
        if(!userIds.isEmpty()){//判断机构下是否有用户
            QueryWrapper<CrmRoleUser> deleteRoleUserWrapper = new QueryWrapper<>();
            deleteRoleUserWrapper.in("USER_ID", ArrayUtil.toArray(userIds,Object.class));
            crmRoleUserMapper.delete(deleteRoleUserWrapper);

            //用户对应的岗位记录
            QueryWrapper<CrmPositionUser> deletePositionUserWrapper = new QueryWrapper<>();
            deletePositionUserWrapper.in("USER_ID", ArrayUtil.toArray(userIds,Object.class));
            crmPositionUserMapper.delete(deletePositionUserWrapper);
        }
    }
}