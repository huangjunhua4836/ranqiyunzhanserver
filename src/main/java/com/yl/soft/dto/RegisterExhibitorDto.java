package com.yl.soft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="参展商信息")
public class RegisterExhibitorDto {
    @ApiModelProperty(value = "所属企业",required = true)
    private String enterprisename;
    @ApiModelProperty(value = "管理员姓名",required = true)
    private String name;
    @ApiModelProperty(value = "管理员身份证",required = true)
    private String idcard;
    @ApiModelProperty(value = "联系方式",required = true)
    private String phone;
    @ApiModelProperty(value = "座机电话",required = true)
    private String tel;
    @ApiModelProperty(value = "绑定邮箱",required = true)
    private String mailbox;
    @ApiModelProperty(value = "是否参加过：1-参加  2-没参加",required = true)
    private Integer isjoin;
    @ApiModelProperty(value = "行为标签ID，数组",required = true)
    private List<String> labelid;
    @ApiModelProperty(value = "邮箱验证码",required = true)
    private String emailverificationcode;
    @ApiModelProperty(value = "营业执照地址",required = true)
    private String businesslicense;
    @ApiModelProperty(value = "企业授权书地址",required = true)
    private String credentials;
}