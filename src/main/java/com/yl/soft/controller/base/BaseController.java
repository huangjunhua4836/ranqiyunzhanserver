package com.yl.soft.controller.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.yl.soft.common.unified.entity.BasePage;
import com.yl.soft.common.unified.redis.RedisService;
import com.yl.soft.common.unified.service.BaseResponseUtil;
import com.yl.soft.common.util.StringUtils;
import com.yl.soft.dto.base.BaseResult;
import com.yl.soft.dto.base.ResultItem;
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

	protected <T> BaseResult<T> error(Integer code, String msg) {
		BaseResult<T> r = new BaseResult<>();
		r.setDesc(msg);
		r.setCode(code);
		return r;
	}

	protected <T> BaseResult<T> error() {
		return error(500, "服务器错误，请稍后再试");
	}
	
	protected <T> ResultItem<T> ok(String msg, T data, Integer pageIndex, Long total, Integer pageTotal, Integer pageLimit) {
		ResultItem<T> r = new ResultItem<T>();
		r.setDesc(msg);
		r.setData(data);
		r.setPageIndex(pageIndex);
		r.setTotal(total);
		r.setCode(200);
		r.setPageTotal(pageTotal);
		r.setPageLimit(pageLimit);
		return r;
	}

	protected <T> ResultItem<T> ok(T data, Integer pageIndex, Long maxRow, Integer page, Integer pageSize) {
		return ok("成功", data, pageIndex, maxRow, page, pageSize);
	}

	protected <T> ResultItem<T> ok(T data) {
		ResultItem<T> r = new ResultItem<T>();
		r.setData(data);
		r.setCode(200);
		r.setDesc("成功");
		return r;
	}
	
	protected <T> BaseResult<T> ok2(T data) {
		BaseResult<T> r = new BaseResult<T>();
		r.setData(data);
		r.setCode(200);
		r.setDesc("成功");
		return r;
	}
	
	protected <T> BaseResult<T> ok2() {
		BaseResult<T> r = new BaseResult<T>();
		r.setCode(200);
		r.setDesc("成功");
		return r;
	}

	protected <T> ResultItem<T> ok() {
		return ok(null);
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
        if(paramMap == null){
            pageNum = 1;
            pageSize = 20;
        }
        if(paramMap.get("pageNum")!=null){
            pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
            pageSize = 20;
        }
        if(paramMap.get("pageNum")!=null && paramMap.get("pageSize") !=null){
            pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
            pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
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
//    protected AppLoginDTO getCurrAppLogin(String token){
//        if("123456".equals(token)){
//            AppLoginDTO appLoginDTO = new AppLoginDTO();
//            appLoginDTO.setId(1);
//            appLoginDTO.setLabelid("1,2,3,4");
//            return appLoginDTO;
//        }
//        return JSONObject.parseObject(redisService.get(token),AppLoginDTO.class);
//    }

    /**
     * 接口分页封装
     * @param listPo
     * @param listDto
     * @return
     */
    protected BasePage getBasePage(List listPo, List listDto){
        Page page = (Page) listPo;
        return new BasePage(page.getPageNum(),page.getPageSize(),page.getPages(),page.getTotal(),listDto);
    }
}