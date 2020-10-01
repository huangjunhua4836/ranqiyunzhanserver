package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.dto.app.OpportunityDto;
import com.yl.soft.po.EhbOpportunity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商机表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
public interface EhbOpportunityService extends IService<EhbOpportunity> {
    /**
     * 通过条件查询商机
     * @param paramMap
     * @return
     */
    List<OpportunityDto> opportunityList(Map paramMap);
}
