package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.po.EhbLiveBroadcast;
import com.yl.soft.po.EhbWonderfulAtlas;
import com.yl.soft.po.EhbWonderfulVideo;
import com.yl.soft.service.EhbLiveBroadcastService;
import com.yl.soft.service.EhbWonderfulAtlasService;
import com.yl.soft.service.EhbWonderfulVideoService;
import com.yl.soft.vo.TableVo;
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
 * 精彩图集表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/atlas")
public class WonderfulAtlasController extends BaseController {
    @Autowired
    public EhbWonderfulAtlasService ehbWonderfulAtlasService;
    @Autowired
    public EhbLiveBroadcastService ehbLiveBroadcastService;

    @GetMapping("/list")
    public String list(ModelMap modelMap) {
        QueryWrapper<EhbLiveBroadcast> ehbLiveBroadcastQueryWrapper = new QueryWrapper<>();
        ehbLiveBroadcastQueryWrapper.eq("isdel",1);
        List<EhbLiveBroadcast> ehbLiveBroadcasts = ehbLiveBroadcastService.list(ehbLiveBroadcastQueryWrapper);
        modelMap.put("ehbLiveBroadcasts",ehbLiveBroadcasts);
        return "atlas/list";
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param ehbWonderfulAtlas
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbWonderfulAtlas ehbWonderfulAtlas, String startTime, String endTime) {
        QueryWrapper<EhbWonderfulAtlas> ehbWonderfulAtlasQueryWrapper = new QueryWrapper<>();
        ehbWonderfulAtlasQueryWrapper.eq(!StringUtils.isEmpty(ehbWonderfulAtlas.getLiveId()),"live_id",ehbWonderfulAtlas.getLiveId());
        ehbWonderfulAtlasQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbWonderfulAtlasQueryWrapper.eq("isdel", 1);
        ehbWonderfulAtlasQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbWonderfulAtlas> ehbWonderfulAtlases = ehbWonderfulAtlasService.list(ehbWonderfulAtlasQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbWonderfulAtlases);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbWonderfulAtlas ehbWonderfulAtlas = new EhbWonderfulAtlas();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbWonderfulAtlas = ehbWonderfulAtlasService.getById(id);
        }
        modelMap.put("ehbWonderfulAtlas",ehbWonderfulAtlas);

        QueryWrapper<EhbLiveBroadcast> ehbLiveBroadcastQueryWrapper = new QueryWrapper<>();
        ehbLiveBroadcastQueryWrapper.eq("isdel",1);
        List<EhbLiveBroadcast> ehbLiveBroadcasts = ehbLiveBroadcastService.list(ehbLiveBroadcastQueryWrapper);
        modelMap.put("ehbLiveBroadcasts",ehbLiveBroadcasts);

        return "atlas/input";
    }

    /**
     * 添加或者修改
     * @param ehbWonderfulAtlas
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbWonderfulAtlas ehbWonderfulAtlas) {
        if(StringUtils.isEmpty(ehbWonderfulAtlas.getId())){
            ehbWonderfulAtlas.setCreatetime(LocalDateTime.now());
            ehbWonderfulAtlas.setIsdel(1);
        }else{
        }
        if(ehbWonderfulAtlasService.saveOrUpdate(ehbWonderfulAtlas)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse delete(String id) {
        System.out.println("ok");
        if(StringUtils.isEmpty(id)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"岗位删除ID为空！");
        }
        ehbWonderfulAtlasService.updateById(ehbWonderfulAtlasService.getById(id).setIsdel(2));
        return setResultSuccess();
    }
}