package com.yl.soft.controller.plantform;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.po.EhbVisitorRegistration;
import com.yl.soft.service.EhbVisitorRegistrationService;
import com.yl.soft.vo.TableVo;
import com.yl.soft.vo.VisitorRegisExcelVo;
import com.yl.soft.vo.VisitorRegisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 参展预登记表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/visitpre")
public class visitPreRegistrationController extends BaseController {
    @Autowired
    public EhbVisitorRegistrationService ehbVisitorRegistrationService;

    @GetMapping("/list")
    public String list() {
        return "visitpre/list";
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param ehbVisitorRegistration
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbVisitorRegistration ehbVisitorRegistration, String startTime, String endTime) {
        QueryWrapper<EhbVisitorRegistration> ehbVisitorRegistrationQueryWrapper = new QueryWrapper<>();
        ehbVisitorRegistrationQueryWrapper.like(!StringUtils.isEmpty(ehbVisitorRegistration.getName()),"name",ehbVisitorRegistration.getName());
        ehbVisitorRegistrationQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbVisitorRegistrationQueryWrapper.orderByDesc("createtime","id");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbVisitorRegistration> ehbVisitorRegistrations = ehbVisitorRegistrationService.list(ehbVisitorRegistrationQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbVisitorRegistrations);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

//    /**
//     * 跳转到单个添加或者修改页面
//     * @param id
//     * @return
//     */
//    @GetMapping("/input")
//    public String input(String id, String type, ModelMap modelMap) {
//        CrmRole crmRole = new CrmRole();
//        if("add".equals(type)){
//
//        }else if("update".equals(type)){
//            crmRole = crmRoleService.getById(id);
//        }
//        modelMap.put("crmRole",crmRole);
//
//        return "role/input";
//    }
//
//    /**
//     * 添加或者修改
//     * @param crmRole
//     * @return
//     */
//    @PostMapping("/saveOrUpdate")
//    @ResponseBody
//    public BaseResponse saveOrUpdate(CrmRole crmRole) {
//        if(StringUtils.isEmpty(crmRole.getId())){
//            crmRole.setCreatetime(LocalDateTime.now());
//            crmRole.setCreateuser(1);
//            crmRole.setIsdel(false);
//        }else{
//            crmRole.setUpdatetime(LocalDateTime.now());
//            crmRole.setUpdateuser(1);
//        }
//        if(crmRoleService.saveOrUpdate(crmRole)){
//            return setResultSuccess();
//        }else{
//            return setResultError("操作失败！");
//        }
//    }
//
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
        ehbVisitorRegistrationService.removeById(id);
        return setResultSuccess();
    }

    /**
     * 参展预登记导出Excel
     * @param response
     */
    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response, String str) {
        VisitorRegisVo visitorRegisVo = new VisitorRegisVo();
        if(!StringUtils.isEmpty(str)){
             visitorRegisVo = JSON.parseObject(str,VisitorRegisVo.class);
        }
        QueryWrapper<EhbVisitorRegistration> ehbVisitorRegistrationQueryWrapper = new QueryWrapper<>();
        ehbVisitorRegistrationQueryWrapper.like(!StringUtils.isEmpty(visitorRegisVo.getName()),"name",visitorRegisVo.getName());
        ehbVisitorRegistrationQueryWrapper.between(!StringUtils.isEmpty(visitorRegisVo.getStartTime()) && !StringUtils.isEmpty(visitorRegisVo.getEndTime()),"createtime",visitorRegisVo.getStartTime(),visitorRegisVo.getEndTime());
        List<EhbVisitorRegistration> ehbVisitorRegistrations = ehbVisitorRegistrationService.list(ehbVisitorRegistrationQueryWrapper);


        List<VisitorRegisExcelVo> visitorRegisExcelVos = ehbVisitorRegistrations.stream().map(i->{
            VisitorRegisExcelVo visitorRegisExcelVo = new VisitorRegisExcelVo();
            BeanUtil.copyProperties(i,visitorRegisExcelVo);
            if(!StringUtils.isEmpty(i.getAppellation())){
                visitorRegisExcelVo.setAppellation(i.getAppellation()==0?"男":"女");
            }
            //面积处理
            String showarea = i.getShowarea();
            if(!StringUtils.isEmpty(showarea)){
                visitorRegisExcelVo.setShowarea(showarea.replace(",","至"));
            }

            return visitorRegisExcelVo;
        }).collect(Collectors.toList());

        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("name", "联系人");
        writer.addHeaderAlias("appellation", "称谓");
        writer.addHeaderAlias("position", "职位");
        writer.addHeaderAlias("compname", "公司名称");
        writer.addHeaderAlias("phone", "联系方式");
        writer.addHeaderAlias("showarea", "预展位面积(平方米)");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(5, "参展商预登记表");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(visitorRegisExcelVos, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        ServletOutputStream out = null;
        try {
            String name = URLEncoder.encode("参展商预登记表", "UTF-8");
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
}