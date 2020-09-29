//package com.yl.soft.dto;
//
//import com.baomidou.mybatisplus.annotation.TableField;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.experimental.Accessors;
//
//@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
//@ApiModel(value="App登录返回session信息")
//public class AppLoginDTO {
////----------------观展人信息-----------------------
//    private Integer id;
//    private String name;
//    private String phone;
//    private String province;
//    private String city;
//    private String county;
//    @ApiModelProperty(value = "登录名称")
//    private String loginname;
//    private String enterprise;
//    private String mailbox;
//    private String labelid;
//    @ApiModelProperty(value = "是否展商  0：不是  1：是",hidden = true)
//    @TableField("iszs")
//    private Boolean iszs;
//
////----------------展商信息-----------------------
//    @ApiModelProperty(value = "管理人员")
//    private String managerman;
//
//    @ApiModelProperty(value = "身份证")
//    private String idcard;
//
//    @ApiModelProperty(value = "企业名称")
//    private String enterprisename;
//
//    @ApiModelProperty(value = "座机")
//    private String tel;
//
//    @ApiModelProperty(value = "营业执照")
//    private String businesslicense;
//
//    @ApiModelProperty(value = "企业授权书")
//    private String credentials;
//
//    @ApiModelProperty(value = "曾经是否加入")
//    private Integer isjoin;
//
//    @ApiModelProperty(value = "展位号")
//    private String boothno;
//
//    @ApiModelProperty(value = "地址")
//    private String address;
//}