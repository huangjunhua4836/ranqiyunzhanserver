package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.dto.app.ExhibitorDto;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.vo.ExhibitorVo;

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
    List<ExhibitorDto> randExibitionList(Map paramMap);

    /**
     * 后台展商列表查询
     * @param paramMap
     * @return
     */
    List<ExhibitorVo> selectExhibitorVoList(Map paramMap);

    /**
     * 后台保存或修改展商
     * @param exhibitorVo
     * @return
     */
    boolean saveOrUpdateExhi(ExhibitorVo exhibitorVo);
}
