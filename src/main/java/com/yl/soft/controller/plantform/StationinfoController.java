package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.jpush.JpushUtil;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.Message;
import com.yl.soft.po.Stationinfo;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.MessageService;
import com.yl.soft.service.StationinfoService;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 站内信表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/stationinfo")
public class StationinfoController extends BaseController {
    @Autowired
    public StationinfoService stationinfoService;
    @Autowired
    public MessageService messageService;
    @Autowired
    public EhbAudienceService ehbAudienceService;
    @Autowired
    public EhbExhibitorService ehbExhibitorService;

    @GetMapping("/list")
    public String list() {
        return "stationinfo/list";
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param stationinfo
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, Stationinfo stationinfo, String startTime, String endTime) {
        QueryWrapper<Stationinfo> stationinfoQueryWrapper = new QueryWrapper<>();
        stationinfoQueryWrapper.like(!StringUtils.isEmpty(stationinfo.getNotificationTitle()),"notification_title",stationinfo.getNotificationTitle());
        stationinfoQueryWrapper.like(!StringUtils.isEmpty(stationinfo.getMsgTitle()),"msg_title",stationinfo.getMsgTitle());
        stationinfoQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        stationinfoQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        stationinfoQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<Stationinfo> stationinfos = stationinfoService.list(stationinfoQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(stationinfos);

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
        Stationinfo stationinfo = new Stationinfo();
        if("add".equals(type)){

        }else if("update".equals(type)){
            stationinfo = stationinfoService.getById(id);
        }
        modelMap.put("stationinfo",stationinfo);

        return "stationinfo/input";
    }

    /**
     * 添加或者修改
     * @param stationinfo
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(Stationinfo stationinfo) {
        if(StringUtils.isEmpty(stationinfo.getId())){
            stationinfo.setCreatetime(LocalDateTime.now());
            stationinfo.setIsdel(false);
        }else{
        }
        if(stationinfoService.saveOrUpdate(stationinfo)){
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
        stationinfoService.updateById(stationinfoService.getById(id).setIsdel(true));
        return setResultSuccess();
    }

    /**
     * 信息发送
     * @return
     */
    @PostMapping("/sendOut")
    @ResponseBody
    public BaseResponse sendOut(String id) {
        System.out.println("ok");
        if(StringUtils.isEmpty(id)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"发送信息ID为空！");
        }
        Stationinfo stationinfo = stationinfoService.getById(id);
        QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
        ehbExhibitorQueryWrapper.ne("state",1);
        List<EhbExhibitor> ehbExhibitors = ehbExhibitorService.list(ehbExhibitorQueryWrapper);
        List<Integer> ids = ehbExhibitors.stream().map(i->{
           return i.getId();
        }).collect(Collectors.toList());

        QueryWrapper<EhbAudience> ehbAudienceQueryWrapper = new QueryWrapper<>();
        ehbAudienceQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbAudienceQueryWrapper.notIn("isdel",ids);
        List<EhbAudience> ehbAudiences = ehbAudienceService.list(ehbAudienceQueryWrapper);
        List<Message> messages = new ArrayList<>();
        for(EhbAudience ehbAudience : ehbAudiences){
            Message message = new Message();
            message.setRuid(ehbAudience.getId());
            message.setRuname(ehbAudience.getName());
            message.setTitle(stationinfo.getMsgTitle());
            message.setContent(stationinfo.getMsgContent());
            message.setCreatetime(LocalDateTime.now());
            message.setStatus(0);//未读
            message.setImgurl(stationinfo.getImgurl());
            message.setUrl(stationinfo.getUrl());
            messages.add(message);
            message.setUsertype(0);//全部类型
            message.setIsdel(0);
            message.setStationinfoid(stationinfo.getId());
            messages.add(message);
        }
        messageService.saveBatch(messages);

        int i = 0;
        if(1 == stationinfo.getSendtype()){
            i = JpushUtil.sendToAllAndroid(stationinfo.getNotificationTitle(),stationinfo.getMsgTitle()
                    ,stationinfo.getMsgContent(),new HashMap<>());
        }else if(2 == stationinfo.getSendtype()){
            i = JpushUtil.sendToAllIos(stationinfo.getNotificationTitle(),stationinfo.getMsgTitle()
                    ,stationinfo.getMsgContent(),"");
        }else if(3 == stationinfo.getSendtype()){
            i = JpushUtil.sendToAll(stationinfo.getNotificationTitle(),stationinfo.getMsgTitle()
                    ,stationinfo.getMsgContent(),new HashMap<>());
        }
        if(i > 0){
            stationinfo.setIssuccess(true);
            stationinfoService.updateById(stationinfo);
            return setResultSuccess();
        }else{
            return setResultError("推送失败！");
        }
    }
}