package com.yl.soft.common.unified.service;

import com.yl.soft.common.constants.BaseApiConstants;
import com.yl.soft.common.unified.entity.BaseResponse;

import java.util.Date;

public class BaseResponseUtil<T> {

    /**
     * 返回系统错误
     *
     * @param msg
     * @return
     */
    public BaseResponse<T> setResultError(String msg) {
        return setResult(BaseApiConstants.ServiceResultCode.SYS_ERROR.getCode()
                , BaseApiConstants.ServiceResultCode.SYS_ERROR.getValue(),msg,null);
    }

    /**
     * 返回定义错误
     *
     * @param code
     * @param msg
     * @return
     */
    public BaseResponse<T> setResultError(Integer code,String value,String msg) {
        return setResult(code, value,msg,null);
    }

    /**
     * 返回成功
     *
     * @return
     */
    public BaseResponse<T> setResultSuccess() {
        return setResult(BaseApiConstants.ServiceResultCode.SUCCESS.getCode()
                , BaseApiConstants.ServiceResultCode.SUCCESS.getValue()
                , "操作成功",null);
    }

    /**
     * 返回成功
     *
     * @param data
     * @return
     */
    public BaseResponse<T> setResultSuccess(T data) {
        return setResult(BaseApiConstants.ServiceResultCode.SUCCESS.getCode()
                , BaseApiConstants.ServiceResultCode.SUCCESS.getValue(),"操作成功",data);
    }

    /**
     * 通用封装类
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    private BaseResponse<T> setResult(Integer code,String value,String msg,T data) {
        return new BaseResponse(new Date(),code,value,msg, data);
    }
}