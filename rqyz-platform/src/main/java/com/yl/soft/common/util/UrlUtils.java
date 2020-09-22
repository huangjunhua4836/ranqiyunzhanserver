package com.yl.soft.common.util;

import java.io.UnsupportedEncodingException;

public class UrlUtils {


    //一般国际采用utf-8
    //private final static String ENCODE = "UTF-8";
    //国内标准GBK
    private final static String ENCODE = "GBK";

    /**
     * URL 解码
     *
     * @param str
     * @return
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL 转码
     *
     * @param str
     * @return
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
