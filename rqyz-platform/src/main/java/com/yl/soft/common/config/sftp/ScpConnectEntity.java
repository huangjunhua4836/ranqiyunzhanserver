package com.yl.soft.common.config.sftp;

/**
 * 类说明：sftp连接参数类
 * @auther jiangxl
 * @date 2020-8-3 18:36
 */
public class ScpConnectEntity {
    private String userName;
    private String passWord;
    private String url;
    private String targetPath;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
