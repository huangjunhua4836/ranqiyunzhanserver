package com.yl.soft.dict;

import java.util.HashMap;
import java.util.Map;

public interface CommonDict {
    //数据状态
    /**
     * 有效
     */
    String CORRECT_STATE="0";
    /**
     * 失效
     */
    String INCORRECT_STATE="1";

    Map ISDELFLAGMAP = new HashMap() {{
        put(CORRECT_STATE, "有效");
        put(INCORRECT_STATE, "失效");
    }};

    //业务级操作类型需要，系统级操作不需要
     String INSERT = "I";
     String UPDATE = "U";
     String DELETE = "D";
    Map OPEARFLAGMAP = new HashMap() {{
        put(INSERT, "新增");
        put(UPDATE, "修改");
        put(DELETE, "删除");
    }};
}