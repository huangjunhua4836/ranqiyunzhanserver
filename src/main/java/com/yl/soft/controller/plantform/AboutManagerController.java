package com.yl.soft.controller.plantform;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.CrmRole;
import com.yl.soft.po.EhbAbout;
import com.yl.soft.service.CrmRoleService;
import com.yl.soft.service.EhbAboutService;
import com.yl.soft.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 关于表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/about")
public class AboutManagerController extends BaseController {
    @Autowired
    public EhbAboutService ehbAboutService;

    @GetMapping("/list")
    public String list() {
        return "about/list";
    }

    /**
     * 查询角色列表
     * @param page
     * @param limit
     * @param ehbAbout
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbAbout ehbAbout, String startTime, String endTime) {
        QueryWrapper<EhbAbout> ehbAboutQueryWrapper = new QueryWrapper<>();
        ehbAboutQueryWrapper.like(!StringUtils.isEmpty(ehbAbout.getTitle()),"title",ehbAbout.getTitle());
        ehbAboutQueryWrapper.eq(!StringUtils.isEmpty(ehbAbout.getType()),"type",ehbAbout.getType());
        ehbAboutQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbAboutQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbAbout> ehbAbouts = ehbAboutService.list(ehbAboutQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbAbouts);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbAbout ehbAbout = new EhbAbout();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbAbout = ehbAboutService.getById(id);
        }
        modelMap.put("ehbAbout",ehbAbout);
        return "about/input";
    }

    @ResponseBody
    @RequestMapping("/uploadImg")
    public JSONObject uploadImg(@RequestParam("uploadFile") MultipartFile multipartFile) throws IOException {
        JSONObject jsonObject = new JSONObject();
        // 这里必须是小数类型，
        jsonObject.put("error",0);
        jsonObject.put("url","https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2114569028,998077550&fm=26&gp=0.jpg");
        return jsonObject;
    }

    /**
     * 添加或者修改
     * @param ehbAbout
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbAbout ehbAbout) {
        if(StringUtils.isEmpty(ehbAbout.getId())){
            ehbAbout.setCreatetime(LocalDateTime.now());
        }else{
        }
        if(ehbAboutService.saveOrUpdate(ehbAbout)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

//    /**
//     * 删除角色
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