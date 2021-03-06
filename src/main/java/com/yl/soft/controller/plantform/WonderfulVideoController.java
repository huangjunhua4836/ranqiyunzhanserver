package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.CrmRole;
import com.yl.soft.po.EhbLiveBroadcast;
import com.yl.soft.po.EhbWonderfulVideo;
import com.yl.soft.service.CrmRoleService;
import com.yl.soft.service.EhbLiveBroadcastService;
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
 * 精彩视频表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/video")
public class WonderfulVideoController extends BaseController {
    @Autowired
    public EhbWonderfulVideoService ehbWonderfulVideoService;
    @Autowired
    public EhbLiveBroadcastService ehbLiveBroadcastService;

    @GetMapping("/list")
    public String list(ModelMap modelMap) {
        QueryWrapper<EhbLiveBroadcast> ehbLiveBroadcastQueryWrapper = new QueryWrapper<>();
        ehbLiveBroadcastQueryWrapper.eq("isdel",1);
        List<EhbLiveBroadcast> ehbLiveBroadcasts = ehbLiveBroadcastService.list(ehbLiveBroadcastQueryWrapper);
        modelMap.put("ehbLiveBroadcasts",ehbLiveBroadcasts);
        return "video/list";
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param ehbWonderfulVideo
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbWonderfulVideo ehbWonderfulVideo, String startTime, String endTime) {
        QueryWrapper<EhbWonderfulVideo> ehbWonderfulVideoQueryWrapper = new QueryWrapper<>();
        ehbWonderfulVideoQueryWrapper.like(!StringUtils.isEmpty(ehbWonderfulVideo.getTitle()),"title",ehbWonderfulVideo.getTitle());
        ehbWonderfulVideoQueryWrapper.eq(!StringUtils.isEmpty(ehbWonderfulVideo.getLiveId()),"live_id",ehbWonderfulVideo.getLiveId());
        ehbWonderfulVideoQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbWonderfulVideoQueryWrapper.eq("isdel", 1);
        ehbWonderfulVideoQueryWrapper.orderByAsc("sort");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbWonderfulVideo> ehbWonderfulVideos = ehbWonderfulVideoService.list(ehbWonderfulVideoQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbWonderfulVideos);

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
        EhbWonderfulVideo ehbWonderfulVideo = new EhbWonderfulVideo();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbWonderfulVideo = ehbWonderfulVideoService.getById(id);
        }
        modelMap.put("ehbWonderfulVideo",ehbWonderfulVideo);

        QueryWrapper<EhbLiveBroadcast> ehbLiveBroadcastQueryWrapper = new QueryWrapper<>();
        ehbLiveBroadcastQueryWrapper.eq("isdel",1);
        List<EhbLiveBroadcast> ehbLiveBroadcasts = ehbLiveBroadcastService.list(ehbLiveBroadcastQueryWrapper);
        modelMap.put("ehbLiveBroadcasts",ehbLiveBroadcasts);

        return "video/input";
    }

    /**
     * 添加或者修改
     * @param ehbWonderfulVideo
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbWonderfulVideo ehbWonderfulVideo) {
        if(StringUtils.isEmpty(ehbWonderfulVideo.getId())){
            ehbWonderfulVideo.setCreatetime(LocalDateTime.now());
            ehbWonderfulVideo.setIsdel(1);
        }else{
        }
        if(ehbWonderfulVideoService.saveOrUpdate(ehbWonderfulVideo)){
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
        ehbWonderfulVideoService.updateById(ehbWonderfulVideoService.getById(id).setIsdel(2));
        return setResultSuccess();
    }
}