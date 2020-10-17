package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.app.ExhibitorDto;
import com.yl.soft.mapper.EhbAudienceMapper;
import com.yl.soft.mapper.EhbExhibitorMapper;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.vo.ExhibitorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * 参展商信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-24
 */
@Service
public class EhbExhibitorServiceImpl extends ServiceImpl<EhbExhibitorMapper, EhbExhibitor> implements EhbExhibitorService {
    @Autowired
    private EhbAudienceMapper ehbAudienceMapper;

    @Autowired
    public EhbAudienceService ehbAudienceService;

    @Transactional
    @Override
    public boolean saveExhibitor(EhbAudience ehbAudience, EhbExhibitor ehbExhibitor) {
        int i = baseMapper.insert(ehbExhibitor);
        if(i<=0){
            return false;
        }
        QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
        ehbExhibitorQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        ehbExhibitorQueryWrapper.eq("enterprisename", ehbExhibitor.getEnterprisename());
        ehbExhibitorQueryWrapper.last("limit 1");
        ehbExhibitorQueryWrapper.orderByDesc("createtime");
        ehbAudience.setBopie(baseMapper.selectOne(ehbExhibitorQueryWrapper).getId());
         i = ehbAudienceMapper.updateById(ehbAudience);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<ExhibitorDto> randExibitionList(Map paramMap) {
        return baseMapper.exibitionList(paramMap);
    }

    @Override
    public List<ExhibitorVo> selectExhibitorVoList(Map paramMap) {
        return baseMapper.selectExhibitorVoList(paramMap);
    }

    @Transactional
    @Override
    public boolean saveOrUpdateExhi(ExhibitorVo exhibitorVo) {
        //线程安全
        synchronized (this){
            if(StringUtils.isEmpty(exhibitorVo.getId())){
                if(baseMapper.insert(exhibitorVo)>0){
                    //查询展商
                    QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
                    ehbExhibitorQueryWrapper.orderByDesc("createtime");
                    ehbExhibitorQueryWrapper.eq("enterprisename",exhibitorVo.getEnterprisename());
                    ehbExhibitorQueryWrapper.last("limit 1");
                    EhbExhibitor one = baseMapper.selectOne(ehbExhibitorQueryWrapper);
                    //添加展商用户
                    EhbAudience ehbAudience = new EhbAudience();
                    ehbAudience.setIsdel(false);
                    ehbAudience.setCreatetime(LocalDateTime.now());
                    ehbAudience.setCreateuser(1);
                    ehbAudience.setPhone(exhibitorVo.getRegisterphone());
                    ehbAudience.setLoginname(exhibitorVo.getRegisterphone());
                    ehbAudience.setPassword(ehbAudienceService.encryptPassword("123456"));
                    ehbAudience.setBopie(one.getId());
                    ehbAudience.setType(1);//后台创建
                    ehbAudience.setEnabled(1);//启用状态
                    ehbAudience.setName(String.valueOf(new Random().nextInt(899999) + 100000));//昵称
                    ehbAudienceService.save(ehbAudience);
                    return true;
                }else{
                    return false;
                }
            }else{
                baseMapper.updateById(exhibitorVo);
                QueryWrapper<EhbAudience> ehbAudienceQueryWrapper = new QueryWrapper<>();
                ehbAudienceQueryWrapper.eq("bopie",exhibitorVo.getId());
                ehbAudienceQueryWrapper.last("limit 1");
                EhbAudience ehbAudience = ehbAudienceService.getOne(ehbAudienceQueryWrapper);
                ehbAudience.setPhone(exhibitorVo.getRegisterphone());
                ehbAudienceService.updateById(ehbAudience);
                return true;
            }
        }
    }
}
