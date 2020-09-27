package com.yl.soft.common.config.sftp;

import java.io.File;

/**
 * 类说明：返回结果类
 * @auther jiangxl
 * @date 2020-8-3 18:34
 */
public class ResultEntity {

    private String code;

    private String message;

    private File file;

    public ResultEntity(){}

    public ResultEntity(String code, String message, File file) {
        super();
        this.code = code;
        this.message = message;
        this.file = file;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
