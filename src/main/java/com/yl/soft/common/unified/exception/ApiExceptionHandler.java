package com.yl.soft.common.unified.exception;

import com.yl.soft.common.unified.entity.BaseResponse;
import com.yl.soft.common.unified.service.BaseResponseUtil;
import com.yl.soft.common.util.LogUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常处理器
 *
 * @author ieflex
 */
@RestControllerAdvice
@ResponseBody
public class ApiExceptionHandler extends BaseResponseUtil {
    /**
     * 拦截所有ApiException全局异常   
     */
    @ExceptionHandler(ApiException.class)
    public BaseResponse runtimeException(ApiException e) {
        LogUtils.writeErrorLog(e.getSourceClass(),e.getMsg(),e.getE());
        // 返回 JOSN
        return setResultError(e.getMsg() + e.getE().getMessage());
    }

    /**
     * 系统运行时异常捕获处理
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse exception(RuntimeException e) {
        LogUtils.writeErrorLog(this.getClass(),e.getMessage(),e);
        // 返回 JOSN
        return setResultError("未知系统运行时异常，请联系管理员！"+e.getMessage());
    }

    /**
     * 系统异常捕获处理
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse exception(Exception e) {
        LogUtils.writeErrorLog(this.getClass(),e.getMessage(),e);
        // 返回 JOSN
        return setResultError("未知系统异常，请联系管理员！"+e.getMessage());
    }
}