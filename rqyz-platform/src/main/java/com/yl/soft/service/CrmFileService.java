package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.po.CrmFile;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-24
 */
public interface CrmFileService extends IService<CrmFile> {
    /**
     * 保存文件
     * @param crmFile
     * @return
     */
    CrmFile saveFile(CrmFile crmFile);
}
