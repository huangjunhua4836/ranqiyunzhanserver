package com.yl.soft.dict;

import java.util.HashMap;
import java.util.Map;

public interface CrmMenuUserDict extends CommonDict{
    /**
     * 叶子节点
     */
    String IS_LEAF_TRUE = "1";
    /**
     * 非叶子节点
     */
    String IS_LEAF_FALSE = "0";
    Map LEAFFLAGMAP = new HashMap() {{
        put(IS_LEAF_TRUE, "叶子节点");
        put(IS_LEAF_FALSE, "非叶子节点");
    }};

}
