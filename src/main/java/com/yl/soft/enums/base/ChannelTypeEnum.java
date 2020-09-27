package com.yl.soft.enums.base;


/**
 * 通道类型
 */
public enum ChannelTypeEnum {
    /**
     * 无
     */
    None(-1),
    /**
     * 电脑
     */
    PC(0),

    wxapplet(1);
    private final Integer value;

    public static ChannelTypeEnum getType(int value) {
        switch (value) {
            case -1:
                return None;
            case 0:
                return PC;
            case 1:
                return wxapplet;
        }
        return PC;
    }

    public Integer getValue() {
        return value;
    }

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    ChannelTypeEnum(int value) {
        this.value = value;
    }
}
