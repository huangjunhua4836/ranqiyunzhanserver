/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yl.soft.dto.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * 返回的结果的父类
 *
 * @param <T>
 * @author Administrator
 * @version $Id: ResultItem.java, v 0.1 2016年2月25日 下午1:51:33 Administrator Exp $
 */
public class BaseResult<T> {

    private String startTime;
    /**
     * 参数校验失败 注解(-xxx-msg)
     *	ApiResponse(code = -1XX, message = "请输入密码"),
     * 用户逻辑错误
     *	ApiResponse(code = -2XX, message = "密码错误"),
     * 服务逻辑错误
     *	ApiResponse(code = -3XX, message = "保存失败"),
     * 固定值
     *	ApiResponse(code = -401, message = "请先登录！"),
     *	ApiResponse(code = -900, message = "系统异常")
     *	ApiResponse(code = 200, message = "成功")
     *	ApiResponse(code = 500, message = "未知异常,请联系管理员")
     */
    private int code;
    private String desc;
    private T data;


    public BaseResult() {
        desc = "";
        code = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        startTime = df.format(new Date());// new Date()为获取当前系统时间
    }

    /**
     * Getter method for property <tt>data</tt>.
     *
     * @return property value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     *
     * @param data value to be assigned to property data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Getter method for property <tt>startTime</tt>.
     *
     * @return property value of startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Setter method for property <tt>startTime</tt>.
     *
     * @param startTime value to be assigned to property startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>desc</tt>.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for property <tt>desc</tt>.
     *
     * @param desc value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}
