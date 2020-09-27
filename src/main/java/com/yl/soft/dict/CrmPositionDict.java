package com.yl.soft.dict;

import java.util.HashMap;
import java.util.Map;

public interface CrmPositionDict extends CommonDict {
     /**
      * 岗位内部规则
      */
     String IN_RULE_SUPERADMIN = "IN_RULE_SUPERADMIN";//超级管理员
     String IN_RULE_DEPTADMIN = "IN_RULE_DEPTADMIN";//部门管理员
     String IN_RULE_GUEST = "IN_RULE_GUEST";//游客
     Map IN_RULE_MAP = new HashMap() {{
        put(IN_RULE_SUPERADMIN, "超级管理员");
        put(IN_RULE_DEPTADMIN, "部门管理员");
        put(IN_RULE_GUEST, "游客");
    }};
}
