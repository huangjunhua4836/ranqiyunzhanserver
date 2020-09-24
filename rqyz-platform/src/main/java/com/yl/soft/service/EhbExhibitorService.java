package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;

/**
 * <p>
 * 参展商信息 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-24
 */
public interface EhbExhibitorService extends IService<EhbExhibitor> {
    /**
     * 注册展商用户
     * @param ehbAudience
     * @param ehbExhibitor
     * @return
     */
    boolean saveExhibitor(EhbAudience ehbAudience,EhbExhibitor ehbExhibitor);
}
