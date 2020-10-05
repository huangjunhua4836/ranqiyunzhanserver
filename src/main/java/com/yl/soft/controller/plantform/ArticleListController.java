package com.yl.soft.controller.plantform;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.controller.base.BaseController;
import com.yl.soft.dict.CommonDict;
import com.yl.soft.po.EhbArticle;
import com.yl.soft.service.EhbArticleService;
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
 * Created by hjd on 2020-10-04 21:20
 * --> 行业资讯
 **/
@Controller
@RequestMapping("/platform/article")
public class ArticleListController extends BaseController {
    @Autowired
    private EhbArticleService ehbArticleService;

    @GetMapping("/list")
    public String list() {
        return "article/list";
    }

    /**
     * 查询行业资讯列表
     *
     * @param page
     * @param limit
     * @param ehbarticle
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/initTable")
    @ResponseBody
    public TableVo initTable(String page, String limit, EhbArticle ehbarticle, String startTime, String endTime) {
        LambdaQueryWrapper<EhbArticle> ehbarticleQueryWrapper = new QueryWrapper<EhbArticle>().lambda();
        ehbarticleQueryWrapper.like(!StringUtils.isEmpty(ehbarticle.getTitle()), EhbArticle::getTitle, ehbarticle.getTitle());
        ehbarticleQueryWrapper.between(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime), EhbArticle::getCreatetime, startTime, endTime);
        ehbarticleQueryWrapper.eq(EhbArticle::getIsdel, CommonDict.CORRECT_STATE);
        ehbarticleQueryWrapper.orderByDesc(EhbArticle::getCreatetime);
        PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
        List<EhbArticle> ehbHottitles = ehbArticleService.list(ehbarticleQueryWrapper);
        PageInfo pageInfo = new PageInfo<>(ehbHottitles);

        TableVo tableVo = new TableVo();
        tableVo.setCode(0);
        tableVo.setMsg("");
        tableVo.setCount((int) pageInfo.getTotal());
        tableVo.setData(pageInfo.getList());
        return tableVo;
    }

    /**
     * 跳转到单个行业资讯添加或者修改页面
     *
     * @param id
     * @return
     */
    @GetMapping("/input")
    public String input(String id, String type, ModelMap modelMap) {
        EhbArticle ehbarticle = new EhbArticle();
        if ("add".equals(type)) {

        } else if ("update".equals(type)) {
            ehbarticle = ehbArticleService.getById(id);
        }
        modelMap.put("ehbLabel", ehbarticle);

        return "article/input";
    }

    /**
     * 添加或者修改行业资讯
     *
     * @param ehbarticle
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public BaseResponse saveOrUpdate(EhbArticle ehbarticle) {
        if (StringUtils.isEmpty(ehbarticle.getId())) {
            ehbarticle.setCreatetime(LocalDateTime.now());
            ehbarticle.setCreateuser(1);
            ehbarticle.setIsdel(false);
        } else {
            ehbarticle.setUpdatetime(LocalDateTime.now());
            ehbarticle.setUpdateuser(1);
        }
        if (ehbArticleService.saveOrUpdate(ehbarticle)) {
            return setResultSuccess();
        } else {
            return setResultError("操作失败！");
        }
    }

    /**
     * 删除行业资讯
     *
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse delete(String id) {
        if (StringUtils.isEmpty(id)) {
            return setResultError(BaseApiConstants.ServiceResultCode.ERROR.getCode()
                    , BaseApiConstants.ServiceResultCode.ERROR.getValue(), "岗位删除ID为空！");
        }
        ehbArticleService.removeById(id);
        return setResultSuccess();
    }
}
