package com.yl.soft.controller.plantform;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.util.PhoneUtils;
import com.yl.soft.common.util.PinyinUtil;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.dto.app.ExhibitorDto;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.Fengniaoboothno;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.EhbExhibitorService;
import com.yl.soft.service.EhbHallService;
import com.yl.soft.service.FengniaoboothnoService;
import com.yl.soft.vo.ExhibitorExcelVo;
import com.yl.soft.vo.ExhibitorVo;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    public FengniaoboothnoService fengniaoboothnoService;

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
        paramMap.put("boothno",exhibitorVo.getBoothno());//展位号
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

        }else{
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
            exhibitorVo.setHeadPortrait(ehbAudience.getHeadPortrait());
        }
        modelMap.put("exhibitorVo",exhibitorVo);

        List<Fengniaoboothno> fengniaoboothnos = fengniaoboothnoService.list();
        modelMap.put("fengniaoboothnos",fengniaoboothnos);

        if("shenhe".equals(type)){
            return "exhibitioninfo/shenhe";
        }
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
        String firstWord = null;
        if(exhibitorVo.getEnterprisename().startsWith("重庆")){
            firstWord = "C";
        }else{
            firstWord = PinyinUtil.getPinYinHeadChar(exhibitorVo.getEnterprisename()).toUpperCase().charAt(0)+"";
        }
        if(StringUtils.isEmpty(exhibitorVo.getId())){
            QueryWrapper<EhbAudience> zswrapper = new QueryWrapper<>();
            zswrapper.eq("phone",exhibitorVo.getRegisterphone());
            if(ehbAudienceService.count(zswrapper)>0){
                return setResultError("该手机号已注册！");
            }
            exhibitorVo.setCreatetime(LocalDateTime.now());
            exhibitorVo.setCreateuser(1);
            exhibitorVo.setIsdel(false);
            exhibitorVo.setState(0);//待认证
        }else{
            exhibitorVo.setUpdatetime(LocalDateTime.now());
            exhibitorVo.setUpdateuser(1);
            //保存云端橱窗内存
            ExhibitorDto exhibitorDto = new ExhibitorDto();
            exhibitorDto.setId(exhibitorVo.getId());
            exhibitorDto.setEnterprisename(exhibitorVo.getEnterprisename());
            exhibitorDto.setLogo(exhibitorVo.getHeadPortrait());
            exhibitorDto.setBoothno(exhibitorVo.getBoothno());
            exhibitorDto.setEnglishname(exhibitorVo.getEnglishname());
            if(exhibitorVo.getState() != null){
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
                    //先删除要审核的
                    Set<String> strList = redisService.sGet(firstWord);
                    for(String str:strList){
                        ExhibitorDto eht = JSONObject.parseObject(str,ExhibitorDto.class);
                        if(eht.getId() == exhibitorVo.getId()){
                            redisService.setRemove(firstWord,JSONObject.toJSONString(eht));
                        }
                    }
                    redisService.sSet(firstWord, JSONObject.toJSONString(exhibitorDto));
                    exhibitorVo.setCertificationtime(LocalDateTime.now());//认证时间
                }
            }
        }
        exhibitorVo.setFirstletter(firstWord);//名称首字母设置
        exhibitorVo.setTelphone(exhibitorVo.getTel());

        if(ehbExhibitorService.saveOrUpdateExhi(exhibitorVo)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
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
            String firstWord = null;
            if(i.getEnterprisename().startsWith("重庆")){
                firstWord = "C";
            }else{
                firstWord = PinyinUtil.getPinYinHeadChar(i.getEnterprisename()).toUpperCase().charAt(0)+"";
            }
            ExhibitorDto exhibitorDto = new ExhibitorDto();
            exhibitorDto.setId(i.getId());
            exhibitorDto.setEnterprisename(i.getEnterprisename());
            EhbAudience ehbAudience = ehbAudienceService.lambdaQuery().select(EhbAudience::getHeadPortrait).eq(EhbAudience::getBopie,i.getId()).last("limit 1").one();
            exhibitorDto.setLogo(ehbAudience!=null?ehbAudience.getHeadPortrait():null);
            exhibitorDto.setBoothno(i.getBoothno());
            exhibitorDto.setEnglishname(i.getEnglishname());
            redisService.sSet(firstWord, JSONObject.toJSONString(exhibitorDto));
        });
        return setResultSuccess();
    }

    /**
     * 获取虚拟手机号码
     * @return
     */
    @PostMapping("/getVmwarePhone")
    @ResponseBody
    public BaseResponse getVmwarePhone() {
        return setResultSuccess(PhoneUtils.getVmwarePhone());
    }

    /**
     * 展商导出Excel
     * @param response
     */
    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response,String str) {
        ExhibitorVo exhibitorVo = new ExhibitorVo();
        if(!StringUtils.isEmpty(str)){
             exhibitorVo = JSON.parseObject(str,ExhibitorVo.class);
        }
        Map paramMap = new HashMap();
        paramMap.put("registerphone",exhibitorVo.getRegisterphone());//注册手机号
        paramMap.put("name",exhibitorVo.getName());//管理人者姓名
        paramMap.put("enterprisename",exhibitorVo.getEnterprisename());//企业名称
        paramMap.put("state",exhibitorVo.getState());//认证状态
        paramMap.put("boothno",exhibitorVo.getBoothno());//展位号
        paramMap.put("startTime",exhibitorVo.getStartTime());//开始时间
        paramMap.put("endTime",exhibitorVo.getEndTime());//结束时间
        List<ExhibitorVo> exhibitorVos = ehbExhibitorService.selectExhibitorVoList(paramMap);

        List<ExhibitorExcelVo> exhibitorExcelVos = exhibitorVos.stream().map(i->{
            ExhibitorExcelVo exhibitorExcelVo = new ExhibitorExcelVo();
            BeanUtil.copyProperties(i,exhibitorExcelVo);
            if(!StringUtils.isEmpty(i.getCertificationtime())){
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String strDate2 = dtf2.format(i.getCertificationtime());
                exhibitorExcelVo.setCertificationtime(strDate2);
            }
            if(!StringUtils.isEmpty(i.getType())){
                exhibitorExcelVo.setType(i.getType()==1?"后台创建":"用户注册");
            }
            return exhibitorExcelVo;
        }).collect(Collectors.toList());

        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("registerphone", "注册电话");
        writer.addHeaderAlias("phone", "管理人员手机");
        writer.addHeaderAlias("name", "管理人员");
        writer.addHeaderAlias("idcard", "身份证");
        writer.addHeaderAlias("enterprisename", "企业名称");
        writer.addHeaderAlias("englishname", "展商英文名称");
        writer.addHeaderAlias("tel", "座机");
        writer.addHeaderAlias("boothno", "展位号");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("website", "公司网址");
        writer.addHeaderAlias("mailbox", "绑定邮箱");
        writer.addHeaderAlias("certificationtime", "认证时间");
        writer.addHeaderAlias("type", "类型");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(12, "参展商信息");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(exhibitorExcelVos, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        ServletOutputStream out = null;
        try {
            String name = URLEncoder.encode("参展商信息", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    /**
     * 推荐和排序
     * @param id
     * @return
     */
    @GetMapping("/isrecommend")
    public String isrecommend(String id,ModelMap modelMap) {
        String str = ehbAudienceService.encryptPassword("123321");
        System.out.println(str);

        EhbExhibitor ehbExhibitor = ehbExhibitorService.getById(id);
        modelMap.put("ehbExhibitor",ehbExhibitor);

        return "exhibitioninfo/isrecommend";
    }

    /**
     * 修改推荐与排序
     * @param ehbExhibitor
     * @return
     */
    @PostMapping("/saveRecommend")
    @ResponseBody
    public BaseResponse saveRecommend(EhbExhibitor ehbExhibitor) {
        ehbExhibitorService.updateById(ehbExhibitor);
        return setResultSuccess();
    }

    /**
     * 删除展商
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    @Transactional
    public BaseResponse delete(String id) {
        System.out.println("ok");
        if(StringUtils.isEmpty(id)){
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"删除ID为空！");
        }
        ehbAudienceService.lambdaUpdate().set(EhbAudience::getEnabled,0)//设置不可用
                .set(EhbAudience::getIsdel,1)//设置删除
                .eq(EhbAudience::getBopie,id).update();
        ehbExhibitorService.lambdaUpdate().set(EhbExhibitor::getIsdel,1).eq(EhbExhibitor::getId,id).update();
        return setResultSuccess();
    }
}