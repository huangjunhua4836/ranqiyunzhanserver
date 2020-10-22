package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.po.EhbDataUpload;
import com.yl.soft.service.EhbDataUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 资料上传 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/resourceupload")
public class ResourceUpLoadController extends BaseController {
    @Autowired
    public EhbDataUploadService ehbDataUploadService;

    /**
     * 跳转到单个添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        QueryWrapper<EhbDataUpload> ehbDataUploadQueryWrapper = null;
        //企业授权书模板
        ehbDataUploadQueryWrapper = new QueryWrapper<>();
        ehbDataUploadQueryWrapper.eq("title","企业授权书模板");
        ehbDataUploadQueryWrapper.orderByDesc("createtime");
        ehbDataUploadQueryWrapper.last("limit 1");
        List<EhbDataUpload> qysqsmbs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
        if(qysqsmbs!=null && !qysqsmbs.isEmpty()){
            modelMap.put("qysqsmb",qysqsmbs.get(0)==null?new EhbDataUpload():qysqsmbs.get(0));
        }

        //展会中英文邀请函
        ehbDataUploadQueryWrapper = new QueryWrapper<>();
        ehbDataUploadQueryWrapper.eq("title","展会中英文邀请函");
        ehbDataUploadQueryWrapper.orderByDesc("createtime");
        ehbDataUploadQueryWrapper.last("limit 1");
        List<EhbDataUpload> zhzywyqhs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
        if(zhzywyqhs!=null && !zhzywyqhs.isEmpty()){
            modelMap.put("zhzywyqh",zhzywyqhs.get(0)==null?new EhbDataUpload():zhzywyqhs.get(0));
        }

        //展商合约
        ehbDataUploadQueryWrapper = new QueryWrapper<>();
        ehbDataUploadQueryWrapper.eq("title","展商合约");
        ehbDataUploadQueryWrapper.orderByDesc("createtime");
        ehbDataUploadQueryWrapper.last("limit 1");
        List<EhbDataUpload> zshys = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
        if(zshys!=null && !zshys.isEmpty()){
            modelMap.put("zshy",zshys.get(0)==null?new EhbDataUpload():zshys.get(0));
        }

        //参展商手册
        ehbDataUploadQueryWrapper = new QueryWrapper<>();
        ehbDataUploadQueryWrapper.eq("title","参展商手册");
        ehbDataUploadQueryWrapper.orderByDesc("createtime");
        ehbDataUploadQueryWrapper.last("limit 1");
        List<EhbDataUpload> czsscs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
        if(czsscs!=null && !czsscs.isEmpty()){
            modelMap.put("czssc",czsscs.get(0)==null?new EhbDataUpload():czsscs.get(0));
        }

        //搭建商手册
        ehbDataUploadQueryWrapper = new QueryWrapper<>();
        ehbDataUploadQueryWrapper.eq("title","搭建商手册");
        ehbDataUploadQueryWrapper.orderByDesc("createtime");
        ehbDataUploadQueryWrapper.last("limit 1");
        List<EhbDataUpload> djsscs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
        if(djsscs!=null && !djsscs.isEmpty()){
            modelMap.put("djssc",djsscs.get(0)==null?new EhbDataUpload():djsscs.get(0));
        }

        //团体观众登记表
        ehbDataUploadQueryWrapper = new QueryWrapper<>();
        ehbDataUploadQueryWrapper.eq("title","团体观众登记表");
        ehbDataUploadQueryWrapper.orderByDesc("createtime");
        ehbDataUploadQueryWrapper.last("limit 1");
        List<EhbDataUpload> ttgzdjbs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
        if(ttgzdjbs!=null && !ttgzdjbs.isEmpty()){
            modelMap.put("ttgzdjb",ttgzdjbs.get(0)==null?new EhbDataUpload():ttgzdjbs.get(0));
        }

        //观众邀请券
        ehbDataUploadQueryWrapper = new QueryWrapper<>();
        ehbDataUploadQueryWrapper.eq("title","观众邀请券");
        ehbDataUploadQueryWrapper.orderByDesc("createtime");
        ehbDataUploadQueryWrapper.last("limit 1");
        List<EhbDataUpload> gzyqqs = ehbDataUploadService.list(ehbDataUploadQueryWrapper);
        if(gzyqqs!=null && !gzyqqs.isEmpty()){
            modelMap.put("gzyqq",gzyqqs.get(0)==null?new EhbDataUpload():gzyqqs.get(0));
        }
        return "resourceupload/input";
    }

    /**
     * 添加或者修改
     * @param ehbDataUpload
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbDataUpload ehbDataUpload) {
        ehbDataUpload.setCreatetime(LocalDateTime.now());
        if(ehbDataUploadService.saveOrUpdate(ehbDataUpload)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }
}