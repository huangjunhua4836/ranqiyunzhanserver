package com.yl.soft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yl.soft.po.Appversion;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * app版本信息 Mapper 接口
 * </p>
 *
 * @author fengqiuyu
 * @since 2020-04-17
 */
public interface AppversionMapper extends BaseMapper<Appversion> {

    List<Appversion> getListPage(@Param("type") Integer type);

    Appversion getByType(@Param("type") Integer type)throws Exception;
}
