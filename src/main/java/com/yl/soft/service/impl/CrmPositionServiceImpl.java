package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.mapper.CrmPositionMapper;
import com.yl.soft.mapper.CrmPositionUserMapper;
import com.yl.soft.po.CrmPosition;
import com.yl.soft.po.CrmPositionUser;
import com.yl.soft.service.CrmPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Service
public class CrmPositionServiceImpl extends ServiceImpl<CrmPositionMapper, CrmPosition> implements CrmPositionService {
    @Autowired
    private CrmPositionUserMapper crmPositionUserMapper;

    @Transactional
    @Override
    public void deletePosition(String id) {
        //删除岗位
        CrmPosition del = baseMapper.selectById(id);
        del.setIsdel(true);
        baseMapper.updateById(del);

        //删除岗位用户对应关系
        QueryWrapper<CrmPositionUser> delPUWrapper = new QueryWrapper<>();
        delPUWrapper.eq("POSITION_ID",id);
        crmPositionUserMapper.delete(delPUWrapper);
    }
}