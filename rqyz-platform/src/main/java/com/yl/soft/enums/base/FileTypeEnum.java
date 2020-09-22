package com.yl.soft.enums.base;

public enum FileTypeEnum {
    jpg(1),bmp (2) ,gif(3) , doc(4),png(5) ,other(6),zip(8),xlsx(11),pdf(10),xls(12),ppt(13),pptx(14),docx(9),rar(7),z7(15);
    private final Integer value;

    public Integer getValue() {
        return value;
    }

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    FileTypeEnum(Integer value) {
        this.value = value;
    }
}
