package com.yl.soft.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.unified.service.BaseResponseUtil;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.dto.AppLoginDTO;
import com.yl.soft.po.CrmCity;
import com.yl.soft.po.CrmCounty;
import com.yl.soft.po.CrmProvince;
import com.yl.soft.service.CrmCityService;
import com.yl.soft.service.CrmCountyService;
import com.yl.soft.service.CrmProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @description:
 * @author: zds
 * @Date: 2020-06-17 16:57
 */
public class BaseController extends BaseResponseUtil {
    @Autowired
    private CrmProvinceService crmProvinceService;
    @Autowired
    private CrmCityService crmCityService;
    @Autowired
    private CrmCountyService crmCountyService;
    @Autowired
    private RedisService redisService;

    protected Map<String, String> getParameterMap(Map<String, String[]> paramMap) {
        Map<String, String> map = new HashMap<String, String>();
        paramMap.forEach((k, v) -> map.put(k, v.length > 0 ? v[0] : null));
        return map;
    }

    /**
     * 省集合
     * @param code
     * @return
     */
    protected List<CrmProvince> getProvice(String code) {
        QueryWrapper<CrmProvince> crmProvinceQueryWrapper = new QueryWrapper<>();
        crmProvinceQueryWrapper.eq(!StringUtils.isEmpty(code),"code",code);
        return crmProvinceService.list(crmProvinceQueryWrapper);
    }

    /**
     * 市集合
     * @param code
     * @param pcode
     * @return
     */
    protected List<CrmCity> getCity(String code,String pcode) {
        QueryWrapper<CrmCity> crmCityQueryWrapper = new QueryWrapper<>();
        crmCityQueryWrapper.eq(!StringUtils.isEmpty(code),"code",code);
        crmCityQueryWrapper.eq(!StringUtils.isEmpty(pcode),"pcode",pcode);
        return crmCityService.list(crmCityQueryWrapper);
    }

    /**
     * 县集合
     * @param code
     * @param ccode
     * @return
     */
    protected List<CrmCounty> getCounty(String code, String ccode) {
        QueryWrapper<CrmCounty> crmCountyQueryWrapper = new QueryWrapper<>();
        crmCountyQueryWrapper.eq(!StringUtils.isEmpty(code),"code",code);
        crmCountyQueryWrapper.eq(!StringUtils.isEmpty(ccode),"ccode",ccode);
        return crmCountyService.list(crmCountyQueryWrapper);
    }

    /**
     * 分页条件处理公共类
     * @param paramMap
     * @return
     */
    public Integer[] pageValidParam(Map paramMap){
        Integer[] pageParam = new Integer[2];
        int pageNum = 0;
        int pageSize = 0;
        if(paramMap!=null){
            pageNum = 1;
            pageSize = 20;
        }
        if(paramMap.get("pageNum")!=null && paramMap.get("pageSize") !=null){
            pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
            pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        }
        if(paramMap.get("pageNum")!=null){
            pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
            pageSize = 20;
        }
        pageNum = pageNum <= 0 ? 1 : pageNum;
        pageSize = pageSize <= 0 ? 20 : pageSize;
        pageParam[0] = pageNum;
        pageParam[1] = pageSize;
        return pageParam;
    }

    /**
     * 取得APP登录的信息
     * @param token
     * @return
     */
    protected AppLoginDTO getCurrAppLogin(String token){
        return JSONObject.parseObject(redisService.get(token),AppLoginDTO.class);
    }
}