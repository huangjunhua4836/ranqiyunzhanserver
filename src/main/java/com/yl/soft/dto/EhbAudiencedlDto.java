package com.yl.soft.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbAudiencedlDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 登录用户id
	@ApiModelProperty(value = "用户id")
	private Integer id;

	@ApiModelProperty(value = "手机账号")
	private String phone;
	
	@ApiModelProperty(value = "昵称")
	private String name;
	
	@ApiModelProperty(value="临时密码")
	private String password;
	
	@ApiModelProperty(value="token")
	private String token;
	
	
}
