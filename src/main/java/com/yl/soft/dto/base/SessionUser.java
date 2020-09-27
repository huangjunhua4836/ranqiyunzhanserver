package com.yl.soft.dto.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SessionUser {

	@ApiModelProperty(value = "主键ID")
	private Integer id;

	@ApiModelProperty(value = "积分余额")
    private Integer points;

    @ApiModelProperty(value = "QQopenID")
    private String qqOpenid;

    @ApiModelProperty(value = "微信openID")
    private String wxOpenid;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "头像URL")
    private String avatarUrl;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "员工标识：1=内部员工，0=非员工")
    private Integer employee;

    @ApiModelProperty(value = "资质标识：1=已认证，0=未认证")
    private Integer qualification;

    @ApiModelProperty(value = "手机验证标识：1=已验证，0=未验证")
    private Integer verifyPhone;
    
    private String password;
    
    private String roleId;

	/**
	 * 状态码
	 */
	private int code;

}
