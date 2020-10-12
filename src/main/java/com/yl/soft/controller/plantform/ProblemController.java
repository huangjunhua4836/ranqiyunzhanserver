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
import com.yl.soft.po.Problem;
import com.yl.soft.service.CrmRoleService;
import com.yl.soft.service.ProblemService;
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
//    /**
//     * 删除
//     * @return
//     */
//    @PostMapping("/delete")
//    @ResponseBody
//    public BaseResponse delete(String id) {
//        System.out.println("ok");
//        if(StringUtils.isEmpty(id)){
//            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
//                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(),"岗位删除ID为空！");
//        }
//        crmRoleService.deleteRole(id);
//        return setResultSuccess();
//    }
}