package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dict.CrmUserDict;
import com.yl.soft.dto.base.PlatformSessionUser;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.mapper.CrmRoleUserMapper;
import com.yl.soft.mapper.CrmUserMapper;
import com.yl.soft.po.CrmRoleUser;
import com.yl.soft.po.CrmUser;
import com.yl.soft.service.CrmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-05
 */
@Service
public class CrmUserServiceImpl extends ServiceImpl<CrmUserMapper, CrmUser> implements CrmUserService {
    @Autowired
    private CrmRoleUserMapper crmRoleUserMapper;

    @Override
    public PlatformSessionUser loginByUserCode(String userCode) {
        Map paramMap = new HashMap();
        paramMap.put("userCode",userCode);
        paramMap.put("state",CrmUserDict.USERNORMAL);
        paramMap.put("isdel",CommonDict.CORRECT_STATE);
        return baseMapper.loginByUserCode(paramMap);
    }

    @Transactional
    @Override
    public boolean saveOrUpdateUser(CrmUser crmUser, Integer roleId) {
        boolean flag = true;
        int i = 0;
        if(StringUtils.isEmpty(crmUser.getId())){
            i = baseMapper.insert(crmUser);
        }else{
            i = baseMapper.updateById(crmUser);
        }
        if(i>0){
            //查询用户角色
            QueryWrapper<CrmRoleUser> crmRoleUserQueryWrapper = new QueryWrapper<>();
            crmRoleUserQueryWrapper.eq("userId",crmUser.getId());
            crmRoleUserMapper.delete(crmRoleUserQueryWrapper);

            CrmRoleUser crmRoleUser = new CrmRoleUser();
            crmRoleUser.setUserId(crmUser.getId());
            crmRoleUser.setRoleId(roleId);
            crmRoleUserMapper.insert(crmRoleUser);
        }else{
            flag = false;
        }
        return flag;
    }

    @Transactional
    @Override
    public void deleteUser(Integer id) {
        baseMapper.deleteById(id);
        QueryWrapper<CrmRoleUser> crmRoleUserQueryWrapper = new QueryWrapper<>();
        crmRoleUserQueryWrapper.eq("userId",id);
        crmRoleUserMapper.delete(crmRoleUserQueryWrapper);
    }
}
