package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.dto.AppLoginDTO;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;

import java.util.List;
import java.util.Map;

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
     * 随机参展商列表
     * @param paramMap
     * @return
     */
    List<AppLoginDTO> randExibitionList(Map paramMap);
}
