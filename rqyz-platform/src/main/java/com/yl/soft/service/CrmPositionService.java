package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.po.CrmPosition;

/**
 * <p>
 * 岗位表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
public interface CrmPositionService extends IService<CrmPosition> {
    /**
     * 1、删除岗位
     * 2、删除岗位用户关系
     * @param id
     */
    void deletePosition(String id);
}
