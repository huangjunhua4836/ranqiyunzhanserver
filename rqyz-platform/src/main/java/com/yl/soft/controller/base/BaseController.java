package com.yl.soft.controller.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.soft.common.unified.service.BaseResponseUtil;
import com.yl.soft.common.util.StringUtils;
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
}