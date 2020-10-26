package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.po.EhbHall;

/**
 * <p>
 * 虚拟展厅信息表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-28
 */
public interface EhbHallService extends IService<EhbHall> {
    /**
     * 删除虚拟展厅
     * @param id
     * @return
     */
    void deleteEhbHall(String id);
}