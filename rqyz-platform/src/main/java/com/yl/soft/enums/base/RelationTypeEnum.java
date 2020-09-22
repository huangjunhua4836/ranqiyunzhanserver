package com.yl.soft.enums.base;

public enum RelationTypeEnum {

	新闻资讯(0),解决方案(1),客户案例(2),帮助中心(3),产品(4),服务(5),用户头像(6);
    private final Integer value;

    public Integer getValue() {
        return value;
    }

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    RelationTypeEnum(Integer value) {
        this.value = value;
    }
}
