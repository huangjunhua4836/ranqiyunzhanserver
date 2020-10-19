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
import com.yl.soft.po.EhbBanner;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.service.CrmRoleService;
import com.yl.soft.service.EhbBannerService;
import com.yl.soft.service.EhbExhibitorService;
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
 * banner表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/banner")
public class BannerController extends BaseController {
    @Autowired
    public EhbBannerService ehbBannerService;
    @Autowired
    public EhbExhibitorService ehbExhibitorService;

    @GetMapping("/list")
    public String list() {
        return "banner/list";
    }

    /**
     * 查询banner列表
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbBanner ehbBanner, String startTime, String endTime) {
        QueryWrapper<EhbBanner> ehbBannerQueryWrapper = new QueryWrapper<>();
        ehbBannerQueryWrapper.like(!StringUtils.isEmpty(ehbBanner.getName()),"name",ehbBanner.getName());
        ehbBannerQueryWrapper.eq(!StringUtils.isEmpty(ehbBanner.getType()),"type",ehbBanner.getType());
        ehbBannerQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbBannerQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        ehbBannerQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbBanner> ehbBanners = ehbBannerService.list(ehbBannerQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbBanners);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int)pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个banner添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbBanner ehbBanner = new EhbBanner();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbBanner = ehbBannerService.getById(id);
        }
        modelMap.put("ehbBanner",ehbBanner);

        List<EhbExhibitor> ehbExhibitors = ehbExhibitorService.lambdaQuery().select(EhbExhibitor::getId,EhbExhibitor::getEnterprisename).eq(EhbExhibitor::getState,1).eq(EhbExhibitor::getIsdel,0).list();
        modelMap.put("ehbExhibitors",ehbExhibitors);

        return "banner/input";
    }

    /**
     * 添加或者修改banner
     * @param ehbBanner
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbBanner ehbBanner) {
//        StringBuffer buffer = new StringBuffer(ehbBanner.getImgurl());
//        buffer = buffer.deleteCharAt(buffer.lastIndexOf(","));
//        ehbBanner.setImgurl(buffer.toString());
        if(StringUtils.isEmpty(ehbBanner.getId())){
            ehbBanner.setCreatetime(LocalDateTime.now());
            ehbBanner.setCreateuser(1);
            ehbBanner.setIsdel(false);
        }else{
            ehbBanner.setUpdatetime(LocalDateTime.now());
            ehbBanner.setUpdateuser(1);
        }
        if(ehbBannerService.saveOrUpdate(ehbBanner)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除banner
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
        ehbBannerService.removeById(id);
        return setResultSuccess();
    }
}