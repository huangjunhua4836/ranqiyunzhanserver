package com.yl.soft.controller;

import com.yl.soft.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OtherController extends BaseController {

    /**
     * 欢迎页面设置
     * @return
     */
    @GetMapping("/")
    public String welcome() {
        return "redirect:/platform/login";
    }
}