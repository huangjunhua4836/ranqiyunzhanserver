package com.yl.soft.dto.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SessionUser {

	@ApiModelProperty(value = "主键ID")
	private Integer id;

	private String name;

	private String describes;

	@ApiModelProperty(value = "头像")
	private String headPortrait;

	private String phone;

	private String province;

	private String city;

	private String county;

	@ApiModelProperty(value = "登录名称")
	private String loginname;

	@ApiModelProperty(value = "密码")
	private String password;

	private String enterprise;

	private String mailbox;

	private String labelid;

	private Integer enabled;

	private String wxOpenid;

	private String qqOpenid;

	@ApiModelProperty("展商ID")
	private Integer bopie;

	/**
	 * 状态码
	 */
	private int code;

}
