package com.yl.soft.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.mapper.*;
import com.yl.soft.po.*;
import com.yl.soft.service.CrmMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 机构成员/科室 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Service
public class CrmMemberServiceImpl extends ServiceImpl<CrmMemberMapper, CrmMember> implements CrmMemberService {

    @Autowired
    private CrmUserMapper crmUserMapper;

    @Autowired
    private CrmRoleUserMapper crmRoleUserMapper;

    @Autowired
    private CrmPositionUserMapper crmPositionUserMapper;

    @Transactional
    @Override
    public void deleteMember(String id) {
        //删除该科室以及其子科室
        CrmMember deleteMember = new CrmMember();
        deleteMember.setIsdel(true);
        QueryWrapper<CrmMember> deleteMemberWrapper = new QueryWrapper<>();
        deleteMemberWrapper.eq("ID",id).or().eq("PARENT_ID",id);
        baseMapper.update(deleteMember,deleteMemberWrapper);

        //科室下的用户
        Set memberIds = new HashSet();
        List<CrmMember> delMembers = baseMapper.selectList(deleteMemberWrapper);
        for(CrmMember crmMember : delMembers){
            memberIds.add(crmMember.getId());
        }
        CrmUser deleteUser = new CrmUser();
        deleteUser.setIsdel(true);
        QueryWrapper<CrmUser> deleteUserWrapper = new QueryWrapper<>();
        deleteUserWrapper.in("MEMBER_ID",ArrayUtil.toArray(memberIds,Object.class));
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