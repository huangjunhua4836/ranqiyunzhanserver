package com.yl.soft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yl.soft.dto.AppLoginDTO;
import com.yl.soft.po.EhbExhibitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 参展商信息 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-09-24
 */
public interface EhbExhibitorMapper extends BaseMapper<EhbExhibitor> {
    /**
     * 参展商列表
     * @param paramMap
     * @return
     */
     List<AppLoginDTO> exibitionList(@Param("paramMap") Map paramMap);
}
