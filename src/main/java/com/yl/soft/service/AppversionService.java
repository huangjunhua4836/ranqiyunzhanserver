package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yl.soft.po.Appversion;

/**
 * <p>
 * app版本信息 服务类
 * </p>
 *
 * @author fengqiuyu
 * @since 2020-04-17
 */
public interface AppversionService extends IService<Appversion> {

    PageInfo<Appversion> getListPage(Integer type, Page p);

    Appversion getByType(Integer type)throws Exception;
}
