package com.yl.soft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yl.soft.dto.OpportunityDto;
import com.yl.soft.po.EhbOpportunity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商机表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
public interface EhbOpportunityMapper extends BaseMapper<EhbOpportunity> {
    /**
     * 通过条件查询商机
     * @param paramMap
     * @return
     */
    List<OpportunityDto> opportunityList(@Param("paramMap") Map paramMap);
}
