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
import com.yl.soft.po.EhbGuest;
import com.yl.soft.service.CrmRoleService;
import com.yl.soft.service.EhbGuestService;
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
 * 嘉宾前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/guest")
public class GuestController extends BaseController {
    @Autowired
    public EhbGuestService ehbGuestService;

    @GetMapping("/list")
    public String list() {
        return "guest/list";
    }

    /**
     * 查询列表
     * @param page
     * @param limit
     * @param ehbGuest
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbGuest ehbGuest, String startTime, String endTime) {
        QueryWrapper<EhbGuest> ehbGuestQueryWrapper = new QueryWrapper<>();
        ehbGuestQueryWrapper.like(!StringUtils.isEmpty(ehbGuest.getName()),"name",ehbGuest.getName());
        ehbGuestQueryWrapper.like(!StringUtils.isEmpty(ehbGuest.getJob()),"job",ehbGuest.getJob());
        ehbGuestQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime),"createtime",startTime,endTime);
        ehbGuestQueryWrapper.eq("isdel", CommonDict.CORRECT_STATE);
        ehbGuestQueryWrapper.orderByDesc("createtime");
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<EhbGuest> ehbGuests = ehbGuestService.list(ehbGuestQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbGuests);

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
        EhbGuest ehbGuest = new EhbGuest();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbGuest = ehbGuestService.getById(id);
        }
        modelMap.put("ehbGuest",ehbGuest);

        return "guest/input";
    }

    /**
     * 添加或者修改
     * @param ehbGuest
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbGuest ehbGuest) {
        if(StringUtils.isEmpty(ehbGuest.getId())){
            ehbGuest.setCreatetime(LocalDateTime.now());
            ehbGuest.setCreateuser(1);
            ehbGuest.setIsdel(false);
        }else{
            ehbGuest.setUpdatetime(LocalDateTime.now());
            ehbGuest.setUpdateuser(1);
        }
        if(ehbGuestService.saveOrUpdate(ehbGuest)){
            return setResultSuccess();
        }else{
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除
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
        ehbGuestService.removeById(id);
        return setResultSuccess();
    }
}