package com.yl.soft.common.unified.entity;

import java.util.Date;

public class BaseResponse<T> {

    /**
     * 系统时间
     */
    private Date systemTime;

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 状态值
     */
    private String value;
    /**
     * 消息
     */
    private String desc;
    /**
     * 返回值
     */
    private T data;

    public BaseResponse(){}

    public BaseResponse(Date systemTime, Integer code, String value, String desc, T data) {
        this.systemTime = systemTime;
        this.code = code;
        this.value = value;
        this.desc = desc;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Date systemTime) {
        this.systemTime = systemTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}