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
import com.yl.soft.po.Problem;
import com.yl.soft.service.ProblemService;
import com.yl.soft.vo.ProblemExcelVo;
import com.yl.soft.vo.ProblemVo;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 问题表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/problem")
public class ProblemController extends BaseController {
    @Autowired
    public ProblemService problemService;

    @GetMapping("/list")
    public String list() {
        return "problem/list";
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param problem
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, Problem problem, String startTime, String endTime) {
        QueryWrapper<Problem> problemQueryWrapper = new QueryWrapper<>();
        problemQueryWrapper.like(!StringUtils.isEmpty(problem.getTitle()),"title",problem.getTitle());
        problemQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        problemQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<Problem> problems = problemService.list(problemQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(problems);

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
        Problem problem = new Problem();
        if("add".equals(type)){

        }else if("update".equals(type)){
            problem = problemService.getById(id);
        }
        modelMap.put("problem",problem);

        return "problem/input";
    }

    /**
     * 添加或者修改
     * @param problem
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(Problem problem) {
        if(StringUtils.isEmpty(problem.getId())){
            problem.setCreatetime(LocalDateTime.now());
        }else{
        }
        if(problemService.saveOrUpdate(problem)){
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
        problemService.removeById(id);
        return setResultSuccess();
    }

    /**
     * 常见问题导出Excel
     * @param response
     */
    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response, String str) {
        ProblemVo problemVo = new ProblemVo();
        if(!StringUtils.isEmpty(str)){
            problemVo = JSON.parseObject(str,ProblemVo.class);
        }
        QueryWrapper<Problem> problemQueryWrapper = new QueryWrapper<>();
        problemQueryWrapper.like(!StringUtils.isEmpty(problemVo.getTitle()),"title",problemVo.getTitle());
        problemQueryWrapper.between(!StringUtils.isEmpty(problemVo.getStartTime()) && !StringUtils.isEmpty(problemVo.getEndTime()),"createtime",problemVo.getStartTime(),problemVo.getEndTime());
        problemQueryWrapper.orderByDesc("createtime");
        List<Problem> problems = problemService.list(problemQueryWrapper);

        List<ProblemExcelVo> problemExcelVos = problems.stream().map(i->{
            ProblemExcelVo problemExcelVo = new ProblemExcelVo();
            BeanUtil.copyProperties(i,problemExcelVo);
            if(!StringUtils.isEmpty(i.getCreatetime())){
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String strDate2 = dtf2.format(i.getCreatetime());
                problemExcelVo.setCreatetime(strDate2);
            }
            return problemExcelVo;
        }).collect(Collectors.toList());

        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("title", "标题");
        writer.addHeaderAlias("content", "内容富文本");
        writer.addHeaderAlias("createtime", "创建时间");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(2, "常见问题表");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(problemExcelVos, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        ServletOutputStream out = null;
        try {
            String name = URLEncoder.encode("常见问题表", "UTF-8");
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