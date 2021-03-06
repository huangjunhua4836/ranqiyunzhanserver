package com.yl.soft.controller.plantform;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.im.ImOperator;
import com.yl.soft.common.live.CreateRecTplResponse;
import com.yl.soft.common.live.LiveMain;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.HWPlayFlowAuthUtil;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.po.EhbLiveBroadcast;
import com.yl.soft.service.EhbLiveBroadcastService;
import com.yl.soft.vo.TableVo;

import cn.hutool.core.lang.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 直播表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/live")
public class LiveController extends BaseController {
    @Autowired
    public EhbLiveBroadcastService ehbLiveBroadcastService;
    @Autowired
    public HWPlayFlowAuthUtil hwPlayFlowAuthUtil;
    @Autowired
    private ImOperator imOperator;
    @Value("${custom.flowNamelist}")
    private String flowNamelist;
    
    @Autowired
    private LiveMain liveMain;

    @GetMapping("/list")
    public String list() {
        return "live/list";
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param ehbLiveBroadcast
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbLiveBroadcast ehbLiveBroadcast, String startTime, String endTime) {
        QueryWrapper<EhbLiveBroadcast> ehbLiveBroadcastQueryWrapper = new QueryWrapper<>();
        ehbLiveBroadcastQueryWrapper.like(!StringUtils.isEmpty(ehbLiveBroadcast.getMainTitle()),"main_title",ehbLiveBroadcast.getMainTitle());
        ehbLiveBroadcastQueryWrapper.like(!StringUtils.isEmpty(ehbLiveBroadcast.getSubTitle()),"sub_title",ehbLiveBroadcast.getSubTitle());
        ehbLiveBroadcastQueryWrapper.eq(!StringUtils.isEmpty(ehbLiveBroadcast.getRoomNum()),"room_num",ehbLiveBroadcast.getRoomNum());
        ehbLiveBroadcastQueryWrapper.like(!StringUtils.isEmpty(ehbLiveBroadcast.getFlowName()),"flow_name",ehbLiveBroadcast.getFlowName());
        ehbLiveBroadcastQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbLiveBroadcastQueryWrapper.orderByDesc("createtime");
        ehbLiveBroadcastQueryWrapper.eq("isdel","1");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbLiveBroadcast> ehbLiveBroadcasts = ehbLiveBroadcastService.list(ehbLiveBroadcastQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbLiveBroadcasts);

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
        EhbLiveBroadcast ehbLiveBroadcast = new EhbLiveBroadcast();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbLiveBroadcast = ehbLiveBroadcastService.getById(id);
        }
        modelMap.put("ehbLiveBroadcast",ehbLiveBroadcast);

        return "live/input";
    }

    /**
     * 添加或者修改
     * @param ehbLiveBroadcast
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbLiveBroadcast ehbLiveBroadcast) {
        if(StringUtils.isEmpty(ehbLiveBroadcast.getId())){
            //直播间弹幕聊天id
            Map<String,Object> dataMap=(Map<String, Object>) JSON.parse(imOperator.createLiveGrop(ehbLiveBroadcast.getId()+""));
            String groupId = dataMap.get("GroupId")+"";
            if("10036".equals(dataMap.get("ErrorCode")+"")){
                return setResultError("IM可能欠费！"+dataMap.get("ErrorInfo"));
            }
            if (StringUtils.isEmpty(groupId)){
                return setResultError("group_id为空！");
            }
            ehbLiveBroadcast.setGropid(groupId);

//            List<String> userFlowNames = ehbLiveBroadcastService.lambdaQuery().select(EhbLiveBroadcast::getFlowName).eq(EhbLiveBroadcast::getIsdel,1).in(EhbLiveBroadcast::getLiveStatus,0,1)
//                    .list().stream().map(i->{
//                        return i.getFlowName();
//                    }).collect(toList());
//            List<String> zongflowList = Arrays.asList(flowNamelist.split(","));
//            //去除已用的元素
//            zongflowList = zongflowList.stream().filter(i -> !userFlowNames.contains(i)).collect(toList());
//            if(zongflowList.isEmpty()){
//                return setResultError("流名称已用尽！");
//            }
           String steam= UUID.randomUUID().toString();
           CreateRecTplResponse createRecTplResponse=liveMain.crateSta(steam);
            if(!StringUtils.isEmpty(createRecTplResponse.getError_code())){
            	return setResultError("操作失败！"+createRecTplResponse.getError_msg());
            }
            Map<String,String> map=hwPlayFlowAuthUtil.tiveOrliveAdd(steam);
				

            ehbLiveBroadcast.setFlowName(steam);
            //拉流地址
//            ehbLiveBroadcast.setPullFlowUrl(hwPlayFlowAuthUtil.liveUrl(ehbLiveBroadcast.getFlowName()));
            ehbLiveBroadcast.setPullFlowUrl(map.get("live"));
            //推流地址
//            ehbLiveBroadcast.setPushFlowUrl(hwPlayFlowAuthUtil.tiveUrl(ehbLiveBroadcast.getFlowName()));
            ehbLiveBroadcast.setPushFlowUrl(map.get("tlive"));
            ehbLiveBroadcast.setCreatetime(LocalDateTime.now());
            ehbLiveBroadcast.setLiveStatus(0);//即将开始
        }else{
        }
        if(ehbLiveBroadcastService.saveOrUpdate(ehbLiveBroadcast)){
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
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"删除ID为空！");
        }
//        EhbLiveBroadcast ehbLiveBroadcast = ehbLiveBroadcastService.getById(id);
//        ehbLiveBroadcast.setDeltime(LocalDateTime.now());
//        ehbLiveBroadcast.setIsdel(2);
//        ehbLiveBroadcastService.updateById(ehbLiveBroadcast);
        ehbLiveBroadcastService.removeById(id);
        return setResultSuccess();
    }

    /**
     * 结束直播
     * @return
     */
    @PostMapping("/liveStop")
    @ResponseBody
    public BaseResponse liveStop(String id) {
        System.out.println("ok");
        if(StringUtils.isEmpty(id)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"ID为空！");
        }
        ehbLiveBroadcastService.lambdaUpdate().set(EhbLiveBroadcast::getLiveStatus,2).eq(EhbLiveBroadcast::getId,id).update();
        return setResultSuccess();
    }
}