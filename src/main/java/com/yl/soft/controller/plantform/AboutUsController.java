package com.yl.soft.controller.plantform;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.po.EhbAboutus;
import com.yl.soft.service.EhbAboutusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String input(String type,ModelMap modelMap) {
        QueryWrapper<EhbAboutus> ehbAboutusQueryWrapper = new QueryWrapper<>();
        if("communitynorms".equals(type)){
            ehbAboutusQueryWrapper.select("id","communitynorms");
            ehbAboutusQueryWrapper.last("limit 1");
            EhbAboutus ehbAboutus = ehbAboutusService.getOne(ehbAboutusQueryWrapper);
            modelMap.put("ehbAboutus",ehbAboutus==null?new EhbAboutus():ehbAboutus);
            return "aboutus/communitynorms";
        }else if("privacyclause".equals(type)){
            ehbAboutusQueryWrapper.select("id","privacyclause");
            ehbAboutusQueryWrapper.last("limit 1");
            EhbAboutus ehbAboutus = ehbAboutusService.getOne(ehbAboutusQueryWrapper);
            modelMap.put("ehbAboutus",ehbAboutus==null?new EhbAboutus():ehbAboutus);
            return "aboutus/privacyclause";
        }else if("useragr".equals(type)){
            ehbAboutusQueryWrapper.select("id","useragr");
            ehbAboutusQueryWrapper.last("limit 1");
            EhbAboutus ehbAboutus = ehbAboutusService.getOne(ehbAboutusQueryWrapper);
            modelMap.put("ehbAboutus",ehbAboutus==null?new EhbAboutus():ehbAboutus);
            return "aboutus/useragr";
        }else{
            return "";
        }
    }

    /**
     * 添加或者修改
     * @param ehbAboutus
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbAboutus ehbAboutus) {
        if(ehbAboutusService.saveOrUpdate(ehbAboutus)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }
}