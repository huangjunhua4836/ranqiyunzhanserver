package com.yl.soft.common.util;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReflectUtil extends cn.hutool.core.util.ReflectUtil {

    /**
     * 把实体类当中的属性封装成map
     *
     * @param object
     * @param fields
     * @param map
     * @return
     */
    public static Map<String, Object> getFiledMap(Object object, Field fields[], Map<String, Object> map) {
        for (int i = 0; i < fields.length; i++) {// 遍历
            try {
                // 得到属性
                Field field = fields[i];
                // 打开私有访问
                field.setAccessible(true);
                // 获取属性
                String name = field.getName();
                // 获取属性值
                Object value = field.get(object);
                // 一个个赋值
                map.put(name, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 移除掉空的map，得到非空map
     * @param obj
     * @return
     */
    public static Map<String, Object> removeNullMap(Object obj) {
        Map<String, Object> map = new HashMap<String,Object>();
        map = getFiledMap(obj, obj.getClass().getDeclaredFields(), map);
        map = getFiledMap(obj, obj.getClass().getSuperclass().getDeclaredFields(), map);
        map.remove("serialVersionUID");//移除掉序列化属性
        Set<String> keys = map.keySet();
        Set<String> removeKeys = new HashSet<String>();
        for (String key : keys) {
            if(StringUtils.isEmpty(map.get(key))) {
                removeKeys.add(key);
            }
        }
        keys.removeAll(removeKeys);
        return map;
    }
}