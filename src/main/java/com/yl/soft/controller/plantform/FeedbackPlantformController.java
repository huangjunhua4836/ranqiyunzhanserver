package com.yl.soft.controller.plantform;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.Feedback;
import com.yl.soft.service.EhbAudienceService;
import com.yl.soft.service.FeedbackService;
import com.yl.soft.vo.FeedbackExcelVo;
import com.yl.soft.vo.FeedbackVo;
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
 * 反馈表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/feedback")
public class FeedbackPlantformController extends BaseController {
    @Autowired
    public FeedbackService feedbackService;
    @Autowired
    public EhbAudienceService ehbAudienceService;

    @GetMapping("/list")
    public String list() {
        return "feedback/list";
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param feedback
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, Feedback feedback, String startTime, String endTime) {
        QueryWrapper<Feedback> feedbackQueryWrapper = new QueryWrapper<>();
        feedbackQueryWrapper.eq(!StringUtils.isEmpty(feedback.getConcat()),"concat",feedback.getConcat());
        feedbackQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createat",startTime,endTime);
        feedbackQueryWrapper.eq("deleted", CommonDict.CORRECT_STATE);
        feedbackQueryWrapper.orderByDesc("createat");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<Feedback> feedbacks = feedbackService.list(feedbackQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(feedbacks);

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
        Feedback feedback = new Feedback();
        if("add".equals(type)){

        }else if("update".equals(type)){
            feedback = feedbackService.getById(id);
        }
        modelMap.put("feedback",feedback);

        return "feedback/input";
    }

    /**
     * 添加或者修改
     * @param feedback
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(Feedback feedback) {
        feedback.setUpdateby(1);
        feedback.setUpdateat(LocalDateTime.now());
        feedback.setFeedbackat(LocalDateTime.now());
        if(feedbackService.saveOrUpdate(feedback)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 反馈意见导出Excel
     * @param response
     */
    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response, String str) {
        FeedbackVo feedbackVo = new FeedbackVo();
        if(!StringUtils.isEmpty(str)){
            feedbackVo = JSON.parseObject(str,FeedbackVo.class);
        }
        QueryWrapper<Feedback> feedbackQueryWrapper = new QueryWrapper<>();
        feedbackQueryWrapper.eq(!StringUtils.isEmpty(feedbackVo.getConcat()),"concat",feedbackVo.getConcat());
        feedbackQueryWrapper.between(!StringUtils.isEmpty(feedbackVo.getStartTime()) && !StringUtils.isEmpty(feedbackVo.getEndTime()),"createat",feedbackVo.getStartTime(),feedbackVo.getEndTime());
        feedbackQueryWrapper.eq("deleted", CommonDict.CORRECT_STATE);
        feedbackQueryWrapper.orderByDesc("createat");
        List<Feedback> feedbacks = feedbackService.list(feedbackQueryWrapper);

        List<FeedbackExcelVo> feedbackExcelVos = feedbacks.stream().map(i->{
            FeedbackExcelVo feedbackExcelVo = new FeedbackExcelVo();
            BeanUtil.copyProperties(i,feedbackExcelVo);
            if(!StringUtils.isEmpty(i.getReaded())){
                feedbackExcelVo.setReaded(i.getReaded()==1?"已解决":"未解决");
            }
            EhbAudience ehbAudience = ehbAudienceService.getById(i.getUserId());
            if(ehbAudience != null){
                feedbackExcelVo.setUserId(ehbAudience.getName());
            }
            if(!StringUtils.isEmpty(i.getCreateat())){
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String strDate2 = dtf2.format(i.getCreateat());
                feedbackExcelVo.setCreateat(strDate2);
            }
            if(!StringUtils.isEmpty(i.getFeedbackat())){
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String strDate2 = dtf2.format(i.getFeedbackat());
                feedbackExcelVo.setFeedbackat(strDate2);
            }
            return feedbackExcelVo;
        }).collect(Collectors.toList());

        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("userId", "用户");
        writer.addHeaderAlias("content", "反馈内容");
        writer.addHeaderAlias("concat", "联系方式");
        writer.addHeaderAlias("readed", "已读标识");
        writer.addHeaderAlias("feedbackat", "反馈时间");
        writer.addHeaderAlias("createat", "创建时间");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(5, "反馈记录表");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(feedbackExcelVos, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        ServletOutputStream out = null;
        try {
            String name = URLEncoder.encode("反馈记录表", "UTF-8");
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