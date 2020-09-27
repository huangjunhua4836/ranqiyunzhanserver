package com.yl.soft.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    /**
     * 记录Info级别的日志
     */
    public static void writeInfoLog(Class targClass,String message) {
        Logger logger = LoggerFactory.getLogger(targClass);
        logger.info(message);
    }

    /**
     * 记录warn级别的日志
     * @param targClass
     * @param message
     */
    public static void writeWarnLog(Class targClass,String message) {
        Logger logger = LoggerFactory.getLogger(targClass);
        logger.warn(message);
    }

    /**
     * 记录error级别的日志
     * @param targClass
     * @param message
     * @param t
     */
    public static void writeErrorLog(Class targClass,String message,Throwable t) {
        Logger logger = LoggerFactory.getLogger(targClass);
        logger.error(message,t);
    }
}
