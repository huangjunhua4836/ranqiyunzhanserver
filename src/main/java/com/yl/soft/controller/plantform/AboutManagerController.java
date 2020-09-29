package com.yl.soft.controller.plantform;


import cn.hutool.json.JSONObject;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.service.CrmRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public CrmRoleService crmRoleService;

    @GetMapping("/list")
    public String list() {
        return "about/list";
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