package com.yl.soft.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbExhibitorVo对象", description="页面展商VO对象")
public class ExhibitorExcelVo {

    @ApiModelProperty(value = "注册电话")
    private String registerphone;

    @ApiModelProperty(value = "管理人员手机")
    private String phone;

    @ApiModelProperty(value = "管理人员")
    private String name;

    @ApiModelProperty(value = "身份证")
    private String idcard;

    @ApiModelProperty(value = "企业名称")
    private String enterprisename;

    @ApiModelProperty(value = "展商英文名称")
    private String englishname;

    @ApiModelProperty(value = "座机")
    private String tel;

    @ApiModelProperty(value = "展位号")
    private String boothno;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "公司网址")
    private String website;

    @ApiModelProperty(value = "绑定邮箱")
    private String mailbox;

    @ApiModelProperty(value = "认证时间")
    private String certificationtime;

    @ApiModelProperty("注册方式：0：用户注册，1：后台创建")
    private String type;
}