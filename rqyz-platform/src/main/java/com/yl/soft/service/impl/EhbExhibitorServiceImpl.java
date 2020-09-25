package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.AppLoginDTO;
import com.yl.soft.mapper.EhbAudienceMapper;
import com.yl.soft.mapper.EhbExhibitorMapper;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 参展商信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-24
 */
@Service
public class EhbExhibitorServiceImpl extends ServiceImpl<EhbExhibitorMapper, EhbExhibitor> implements EhbExhibitorService {
    @Autowired
    private EhbAudienceMapper ehbAudienceMapper;

    @Transactional
    @Override
    public boolean saveExhibitor(EhbAudience ehbAudience, EhbExhibitor ehbExhibitor) {
        int i = ehbAudienceMapper.insert(ehbAudience);
        if(i<=0){
            return false;
        }
        QueryWrapper<EhbAudience> ehbAudienceQueryWrapper = new QueryWrapper<>();
        ehbAudienceQueryWrapper.eq("phone",ehbAudience.getPhone());
        ehbAudienceQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        ehbAudienceQueryWrapper.last("limit 1");
        ehbAudienceQueryWrapper.orderByDesc("createtime");
        ehbAudience = ehbAudienceMapper.selectOne(ehbAudienceQueryWrapper);
        ehbExhibitor.setId(ehbAudience.getId());
        i = baseMapper.insert(ehbExhibitor);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<AppLoginDTO> randExibitionList(Map paramMap) {
        return baseMapper.exibitionList(paramMap);
    }
}
