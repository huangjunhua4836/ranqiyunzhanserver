package com.yl.soft.enums.base;

/**
 * author 毛俊贤
 * email 258258001@qq.com
 * 创建时间 2020/3/12 16:21
 * 功能
 **/
public enum PubStatusEnum {
    /**
     * 成功
     */
    success(0),
    /**
     * 失败
     */
    failure(1);


    private final Integer value;

    public Integer getValue() {
        return value;
    }

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    PubStatusEnum(int value) {
        this.value = value;
    }
}
