package com.yl.soft.controller;


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
import com.yl.soft.service.CrmRoleService;
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
 * 角色表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/demo")
public class DemoController extends BaseController {
    @Autowired
    public CrmRoleService crmRoleService;

    @GetMapping("/list")
    public String list() {
        return "jsp/demo2";
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

    @ResponseBody
    @RequestMapping("/aa")
    public JSONObject aa(String content) throws IOException {
        System.out.println(content);
        JSONObject jsonObject = new JSONObject();
        // 这里必须是小数类型，
        return jsonObject;
    }
}