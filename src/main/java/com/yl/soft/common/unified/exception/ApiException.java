package com.yl.soft.common.unified.exception;

/**
 * 自定义api异常
 */
public class ApiException extends RuntimeException{
    private String msg;
    private Exception e;
    private Class sourceClass;

    public ApiException(String msg,Exception e,Class sourceClass){
        this.msg = msg;
        this.e = e;
        this.sourceClass = sourceClass;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public Class getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(Class sourceClass) {
        this.sourceClass = sourceClass;
    }
}