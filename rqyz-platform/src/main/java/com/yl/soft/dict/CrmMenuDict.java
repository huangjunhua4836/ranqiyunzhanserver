package com.yl.soft.dict;

import java.util.HashMap;
import java.util.Map;

public interface CrmMenuDict extends CommonDict{
     String ORDINARY = "0";//普通菜单
     String BUTTON = "1";//按钮菜单
     String NULLTYPE = "2";//空菜单
     Map MENUTYPEMAP = new HashMap() {{
        put(ORDINARY, "普通菜单");
        put(BUTTON, "按钮菜单");
        put(NULLTYPE, "空菜单");
    }};

     String ISMUSTTRUE = "on";//必须菜单
     String ISMUSTFALSE = "off";//非必须菜单
    Map MENUMUSTMAP = new HashMap() {{
        put(ISMUSTTRUE, "必须菜单");
        put(ISMUSTFALSE, "非必须菜单");
    }};

}
