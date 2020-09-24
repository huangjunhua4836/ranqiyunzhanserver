package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.mapper.CrmFileMapper;
import com.yl.soft.po.CrmFile;
import com.yl.soft.service.CrmFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-24
 */
@Service
public class CrmFileServiceImpl extends ServiceImpl<CrmFileMapper, CrmFile> implements CrmFileService {

    @Transactional
    @Override
    public CrmFile saveFile(CrmFile crmFile) {
        int i = baseMapper.insert(crmFile);
        if(i > 0){
            QueryWrapper<CrmFile> crmFileQueryWrapper = new QueryWrapper<>();
            crmFileQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
            crmFileQueryWrapper.orderByDesc("createtime");
            crmFileQueryWrapper.eq("name",crmFile.getName());
            crmFileQueryWrapper.eq("type",crmFile.getType());
            crmFileQueryWrapper.eq("path",crmFile.getPath());
            crmFileQueryWrapper.last("limit 1");
            crmFile = baseMapper.selectOne(crmFileQueryWrapper);
        }else{
            crmFile = null;
        }
        return crmFile;
    }
}
