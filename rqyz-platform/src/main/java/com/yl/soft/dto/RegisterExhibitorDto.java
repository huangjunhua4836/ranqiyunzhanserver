package com.yl.soft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="参展商信息")
public class RegisterExhibitorDto {
    @ApiModelProperty(value = "所属企业",required = true)
    private String enterprise;
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
}