package com.yl.soft.controller.plantform;


import com.yl.soft.controller.base.BaseController;
import com.yl.soft.po.EhbAboutus;
import com.yl.soft.service.EhbAboutusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * 个人中心-关于我们 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/aboutus")
public class AboutUsController extends BaseController {
    @Autowired
    public EhbAboutusService ehbAboutusService;

    @GetMapping("/list")
    public String list() {
        return "aboutus/list";
    }

    @GetMapping("/input")
    public String input(ModelMap modelMap) {
        EhbAboutus ehbAboutus = new EhbAboutus();
        List<EhbAboutus> ehbAboutuses = ehbAboutusService.list();

        modelMap.put("ehbAboutus",ehbAboutus);
        return "aboutus/input";
    }

//    /**
//     * 添加或者修改
//     * @param ehbAbout
//     * @return
//     */
//    @PostMapping("/saveOrUpdate")
//    @ResponseBody
//    public BaseResponse saveOrUpdate(EhbAbout ehbAbout) {
//        if(StringUtils.isEmpty(ehbAbout.getId())){
//            ehbAbout.setCreatetime(LocalDateTime.now());
//        }else{
//        }
//        if(ehbAboutService.saveOrUpdate(ehbAbout)){
//            return setResultSuccess();
//        }else{
//            return setResultError("操作失败！");
//        }
//    }
//
//    /**
//     * 删除关于
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
//        ehbAboutService.removeById(id);
//        return setResultSuccess();
//    }
}