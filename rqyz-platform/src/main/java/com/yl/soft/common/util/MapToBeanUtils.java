package com.yl.soft.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class MapToBeanUtils {
    /**
     * map转化为javabean
     * @param o
     * @param c
     * @return
     */
    public static Object mapToJavaBean(Object o, Class c){
        JSONObject jsonObject=(JSONObject)JSONObject.toJSON(o);//map转化为json对象
        Object newo=JSONObject.toJavaObject(jsonObject,c);//json对象转化javabean对象
        return newo;
    }

    /**
     * map的list集合转化为javabean的list集合
     * @param list
     * @param c
     * @return
     */
    public static List MapListToJavaBeanList(List list,Class c){
        String listStr = JSON.toJSONString(list);//list转化为json字符串
        List List = JSONArray.parseArray(listStr,c);//json字符串转化为L
        return List;
    }
}
