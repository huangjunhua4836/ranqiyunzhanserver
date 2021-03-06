package com.yl.soft.dto;

import java.io.Serializable;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbAudiencegrDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 登录用户id
	@ApiModelProperty(value = "用户id")
	private Integer id;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "头像")
	private String headPortrait;

	@ApiModelProperty(value = "联系方式")
	private String phone;

	@ApiModelProperty(value = "标签：字符串数组1,2,3")
	private String labelid;
	
	@ApiModelProperty(value="认证状态（0：未认证，1：已认证）")
	private String state;
	
	@ApiModelProperty(value="二维码")
	private String qrcode;
	
	@ApiModelProperty(value="企业名称")
	private String qyname;

}
