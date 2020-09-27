package com.yl.soft.enums;


/**
 * 类说明：数据字典类型枚举类
 * @author jiangxl
 * @date 2020-7-16
 */
public enum DictionaryTypeEnum {
    性别("sex"),
    学历("edu"),
    人员类型("usertype"),
    经济属性("economy"),
    机构类别("orgtype"),
    机构级别("orglevel"),
    机构等次("orggrade"),
    证件类型("cardtype"),
    婴儿与我关系("relation"),
    药品使用频次("frequency");

    private final String value;

    public static String getName(String value) {
        switch (value) {
            case "sex": return "性别";
            case "edu": return "学历";
            case "usertype": return "人员类型";
            case "economy": return "经济属性";
            case "orgtype": return "机构类别";
            case "orglevel": return "机构级别";
            case "orggrade": return "机构等次";
            case "cardtype": return "证件类型";
            case "relation": return "婴儿与我关系";
            case "frequency": return "药品使用频次";
            default: return "";
        }
    }

    public String getValue() {
        return value;
    }

    DictionaryTypeEnum(String value) {
        this.value = value;
    }
}
