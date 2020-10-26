package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.mapper.EhbExhibitorMapper;
import com.yl.soft.mapper.EhbHallMapper;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.EhbHall;
import com.yl.soft.service.EhbHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 虚拟展厅信息表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-28
 */
@Service
public class EhbHallServiceImpl extends ServiceImpl<EhbHallMapper, EhbHall> implements EhbHallService {
    @Autowired
    private EhbExhibitorMapper ehbExhibitorMapper;

    @Transactional
    @Override
    public void deleteEhbHall(String id) {
        EhbHall ehbHall = baseMapper.selectById(id);
        EhbExhibitor ehbExhibitor = ehbExhibitorMapper.selectById(ehbHall.getExhibitorid());
        ehbExhibitor.setHalurl(null);
        ehbExhibitorMapper.updateById(ehbExhibitor);

        baseMapper.deleteById(id);
    }
}
