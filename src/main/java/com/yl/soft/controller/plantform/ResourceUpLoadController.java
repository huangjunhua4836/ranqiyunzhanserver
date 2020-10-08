package com.yl.soft.controller.plantform;


import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.po.EhbLabel;
import com.yl.soft.service.EhbLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * <p>
 * 资料上传 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Controller
@RequestMapping("/platform/resourceupload")
public class ResourceUpLoadController extends BaseController {
    @Autowired
    public EhbLabelService ehbLabelService;

    /**
     * 跳转到单个添加或者修改页面
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbLabel ehbLabel = new EhbLabel();
        if("add".equals(type)){

        }else if("update".equals(type)){
            ehbLabel = ehbLabelService.getById(id);
        }
        modelMap.put("ehbLabel",ehbLabel);

        return "resourceupload/input";
    }

    /**
     * 添加或者修改
     * @param ehbLabel
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbLabel ehbLabel) {
        if(StringUtils.isEmpty(ehbLabel.getId())){
            ehbLabel.setCreatetime(LocalDateTime.now());
            ehbLabel.setCreateuser(1);
            ehbLabel.setIsdel(false);
        }else{
            ehbLabel.setUpdatetime(LocalDateTime.now());
            ehbLabel.setUpdateuser(1);
        }
        if(ehbLabelService.saveOrUpdate(ehbLabel)){
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
        ehbLabelService.removeById(id);
        return setResultSuccess();
    }
}