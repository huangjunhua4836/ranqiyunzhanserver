package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.dto.OpportunityDto;
import com.yl.soft.mapper.EhbOpportunityMapper;
import com.yl.soft.po.EhbOpportunity;
import com.yl.soft.service.EhbOpportunityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商机表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
@Service
public class EhbOpportunityServiceImpl extends ServiceImpl<EhbOpportunityMapper, EhbOpportunity> implements EhbOpportunityService {
    @Override
    public List<OpportunityDto> opportunityList(Map paramMap) {
        return baseMapper.opportunityList(paramMap);
    }
}
