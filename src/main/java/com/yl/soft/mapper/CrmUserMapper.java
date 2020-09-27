package com.yl.soft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.CrmUser;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-09-05
 */
public interface CrmUserMapper extends BaseMapper<CrmUser> {

    /**
     * 用户登录
     * @param paramMap
     * @return
     */
    SessionUser loginByUserCode(@Param("paramMap") Map paramMap);
}
