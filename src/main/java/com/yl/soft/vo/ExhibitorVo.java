package com.yl.soft.vo;

import com.yl.soft.po.EhbExhibitor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbExhibitorVo对象", description="页面展商VO对象")
public class ExhibitorVo extends EhbExhibitor {

    @ApiModelProperty(value = "注册电话")
    private String registerphone;

    @ApiModelProperty(value = "登录名称")
    private String loginname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty("注册方式：0：用户注册，1：后台创建")
    private Integer type;

    @ApiModelProperty("首次登录:0:是，1：否")
    private Integer isnew;

    @ApiModelProperty(value = "个人头像或者企业logo")
    private String headPortrait;
}