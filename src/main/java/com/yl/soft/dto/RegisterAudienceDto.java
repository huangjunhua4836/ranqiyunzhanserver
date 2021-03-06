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
@ApiModel(value="参展用户信息")
public class RegisterAudienceDto {
    @ApiModelProperty(value = "姓名",required = true)
    private String name;
    @ApiModelProperty(value = "手机号",required = true)
    private String phone;
    @ApiModelProperty(value = "所属企业",required = true)
    private String enterprise;
    @ApiModelProperty(value = "邮箱",required = true)
    private String mailbox;
    @ApiModelProperty(value = "行为标签ID，单个标签就一个 比如：1 ;多个标签就用逗号隔开 比如：1,2,3,4,5",required = true)
    private String labelid;
    @ApiModelProperty(value = "邮箱验证码",required = true)
    private String emailverificationcode;
}