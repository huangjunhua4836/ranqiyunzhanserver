package com.yl.soft.controller.plantform;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.PinyinUtil;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.app.ExhibitorDto;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.EhbHallService;
import com.yl.soft.vo.ExhibitorVo;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 展商信息
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/zsInfo")
public class ZSInfoController extends BaseController {
    @Autowired
    public EhbExhibitorService ehbExhibitorService;
    @Autowired
    public EhbAudienceService ehbAudienceService;
    @Autowired
    public EhbHallService ehbHallService;
    @Autowired
    public RedisService redisService;

    @GetMapping("/list")
    public String list() {
        return "exhibitioninfo/list2";
    }

    /**
     * 列表
     * @param page
     * @param limit
     * @param exhibitorVo
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, ExhibitorVo exhibitorVo, String startTime, String endTime) {
//        QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
//        ehbExhibitorQueryWrapper.eq(!StringUtils.isEmpty(ehbExhibitor.getPhone()),"phone",ehbExhibitor.getPhone());
//        ehbExhibitorQueryWrapper.like(!StringUtils.isEmpty(ehbExhibitor.getName()),"name",ehbExhibitor.getName());
//        ehbExhibitorQueryWrapper.like(!StringUtils.isEmpty(ehbExhibitor.getEnterprisename()),"enterprisename",ehbExhibitor.getEnterprisename());
//        ehbExhibitorQueryWrapper.eq(!StringUtils.isEmpty(ehbExhibitor.getState()),"state",ehbExhibitor.getState());
//        ehbExhibitorQueryWrapper.eq(!StringUtils.isEmpty(ehbExhibitor.getFid()),"fid",ehbExhibitor.getFid());
//        ehbExhibitorQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
//        ehbExhibitorQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
//        ehbExhibitorQueryWrapper.orderByDesc("createtime");
//        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
//        List<EhbExhibitor> ehbExhibitors = ehbExhibitorService.list(ehbExhibitorQueryWrapper);
//        PageInfo pageInfo = new PageInfo<>(ehbExhibitors);

        Map paramMap = new HashMap();
        paramMap.put("registerphone",exhibitorVo.getRegisterphone());//注册手机号
        paramMap.put("name",exhibitorVo.getName());//管理人者姓名
        paramMap.put("enterprisename",exhibitorVo.getEnterprisename());//企业名称
        paramMap.put("state",exhibitorVo.getState());//认证状态
        paramMap.put("fid",exhibitorVo.getFid());//fid
        paramMap.put("startTime",startTime);//开始时间
        paramMap.put("endTime",endTime);//结束时间

        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<ExhibitorVo> exhibitorVos = ehbExhibitorService.selectExhibitorVoList(paramMap);
        PageInfo pageInfo = new PageInfo<>(exhibitorVos);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 添加或者修改
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        ExhibitorVo exhibitorVo = new ExhibitorVo();
        if("add".equals(type)){

        }else if("update".equals(type)){
            EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(id);
            BeanUtil.copyProperties(ehbExhibitor,exhibitorVo);
            QueryWrapper<EhbAudience> ehbAudienceQueryWrapper = new QueryWrapper<>();
            ehbAudienceQueryWrapper.eq("bopie",id);
            ehbAudienceQueryWrapper.last("limit 1");
            EhbAudience ehbAudience = ehbAudienceService.getOne(ehbAudienceQueryWrapper);
            exhibitorVo.setRegisterphone(ehbAudience.getPhone());
            exhibitorVo.setIsnew(ehbAudience.getIsnew());
            exhibitorVo.setLoginname(ehbAudience.getLoginname());
            exhibitorVo.setPassword(ehbAudience.getPassword());
            exhibitorVo.setType(ehbAudience.getType());
        }

        modelMap.put("exhibitorVo",exhibitorVo);

        return "exhibitioninfo/input2";
    }

    /**
     * 添加或者修改
     * @param exhibitorVo
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(ExhibitorVo exhibitorVo) {
        String firstWord = PinyinUtil.getPinYinHeadChar(exhibitorVo.getEnterprisename()).toUpperCase().charAt(0)+"";
        if(StringUtils.isEmpty(exhibitorVo.getId())){
            QueryWrapper<EhbAudience> zswrapper = new QueryWrapper<>();
            zswrapper.eq("phone",exhibitorVo.getRegisterphone());
            if(ehbAudienceService.count(zswrapper)>0){
                return setResultError("该手机号已注册！");
            }

            exhibitorVo.setCreatetime(LocalDateTime.now());
            exhibitorVo.setCreateuser(1);
            exhibitorVo.setIsdel(false);
        }else{
            exhibitorVo.setUpdatetime(LocalDateTime.now());
            exhibitorVo.setUpdateuser(1);
            ExhibitorDto exhibitorDto = new ExhibitorDto();
            exhibitorDto.setId(exhibitorVo.getId());
            exhibitorDto.setName(exhibitorVo.getEnterprisename());
            exhibitorDto.setLogo(exhibitorVo.getLogo());
            exhibitorDto.setBoothno(exhibitorVo.getBoothno());
            exhibitorDto.setEnglishname(exhibitorVo.getEnglishname());
            if(exhibitorVo.getState() != null){
                //先删除要审核的
                Set<String> strList = redisService.sGet(firstWord);
                for(String str:strList){
                    ExhibitorDto eht = JSONObject.parseObject(str,ExhibitorDto.class);
                    if(eht.getId() == exhibitorVo.getId()){
                        redisService.setRemove(firstWord,JSONObject.toJSONString(eht));
                    }
                }
                if(exhibitorVo.getState() == 3){//审核不通过
                    if(StringUtils.isEmpty(exhibitorVo.getFailreason())){
                        return setResultError("审核原因不能为空！");
                    }
                }else if(exhibitorVo.getState() == 1){//已审核
                    if(StringUtils.isEmpty(exhibitorVo.getBusinesslicense())){
                        return setResultError("营业执照未上传！");
                    }
                    if(StringUtils.isEmpty(exhibitorVo.getCredentials())){
                        return setResultError("企业授权书未上传！");
                    }
                    redisService.sSet(firstWord, JSONObject.toJSONString(exhibitorDto));
                    exhibitorVo.setCertificationtime(LocalDateTime.now());//认证时间
                }
            }
        }
        exhibitorVo.setFirstletter(firstWord);//名称首字母设置
        exhibitorVo.setTelphone(exhibitorVo.getTel());
        //线程安全
        synchronized (this){
            if(StringUtils.isEmpty(exhibitorVo.getId())){
                if(ehbExhibitorService.save(exhibitorVo)){
                    //查询展商
                    QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
                    ehbExhibitorQueryWrapper.orderByDesc("createtime");
                    ehbExhibitorQueryWrapper.eq("enterprisename",exhibitorVo.getEnterprisename());
                    ehbExhibitorQueryWrapper.last("limit 1");
                    EhbExhibitor one = ehbExhibitorService.getOne(ehbExhibitorQueryWrapper);
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
                    ehbAudienceService.save(ehbAudience);
                    return setResultSuccess();
                }else{
                    return setResultError("操作失败！");
                }
            }else{
                ehbExhibitorService.updateById(exhibitorVo);
                QueryWrapper<EhbAudience> ehbAudienceQueryWrapper = new QueryWrapper<>();
                ehbAudienceQueryWrapper.eq("bopie",exhibitorVo.getId());
                ehbAudienceQueryWrapper.last("limit 1");
                EhbAudience ehbAudience = ehbAudienceService.getOne(ehbAudienceQueryWrapper);
                ehbAudience.setPhone(exhibitorVo.getRegisterphone());
                ehbAudienceService.updateById(ehbAudience);
                return setResultSuccess();
            }
        }
    }

    /**
     * 刷新redis云端橱窗
     * @return
     */
    @PostMapping("/refreshCloudWindows")
    @ResponseBody
    public BaseResponse refreshCloudWindows() {
        String keys[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"
                        ,"R","S","T","U","V","W","X","Y","Z"};
        redisService.del(keys);
        QueryWrapper<EhbExhibitor> ehbExhibitorQueryWrapper = new QueryWrapper<>();
        ehbExhibitorQueryWrapper.eq("isdel",CommonDict.CORRECT_STATE);
        ehbExhibitorQueryWrapper.eq("state",1);
        ehbExhibitorService.list(ehbExhibitorQueryWrapper).stream().forEach(i->{
            String firstWord = PinyinUtil.getPinYinHeadChar(i.getEnterprisename()).toUpperCase().charAt(0)+"";
            ExhibitorDto exhibitorDto = new ExhibitorDto();
            exhibitorDto.setId(i.getId());
            exhibitorDto.setName(i.getEnterprisename());
            exhibitorDto.setLogo(i.getLogo());
            exhibitorDto.setBoothno(i.getBoothno());
            exhibitorDto.setEnglishname(i.getEnglishname());
            redisService.sSet(firstWord, JSONObject.toJSONString(exhibitorDto));
        });
        return setResultSuccess();
    }
}