package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.mapper.AppversionMapper;
import com.yl.soft.po.Appversion;
import com.yl.soft.service.AppversionService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * app版本信息 服务实现类
 * </p>
 *
 * @author fengqiuyu
 * @since 2020-04-17
 */
@Service
public class AppversionServiceImpl extends ServiceImpl<AppversionMapper, Appversion> implements AppversionService {

    @Override
    public PageInfo<Appversion> getListPage(Integer type, Page page) {
        if (StringUtils.isNoneBlank(page.getOrderBy())) {
            PageHelper.orderBy(page.getOrderBy());
        }
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Appversion> list = baseMapper.getListPage(type);
        return new PageInfo<>(list);
    }

    @Override
    public Appversion getByType(Integer type) throws Exception {
        return baseMapper.getByType(type);
    }
}
