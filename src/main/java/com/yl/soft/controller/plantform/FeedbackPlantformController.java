package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.Feedback;
import com.yl.soft.service.FeedbackService;
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
}