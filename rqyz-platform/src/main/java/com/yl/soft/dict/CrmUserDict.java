package com.yl.soft.dict;

import java.util.HashMap;
import java.util.Map;

public interface CrmUserDict extends CommonDict{
    /**
     * 超级管理员代码
     */
    String SUPERADMIN_CODE = "superadmin";
    Map SUPERADMINMAP = new HashMap() {{
        put(SUPERADMIN_CODE, "超级管理员代码");
    }};

    /**
     * 用户状态
     */
    String USERNORMAL = "1";
    String USERLOCK = "2";
    Map USERSTATEMAP = new HashMap() {{
        put(USERNORMAL, "用户正常");
        put(USERLOCK, "用户锁定");
    }};
}
