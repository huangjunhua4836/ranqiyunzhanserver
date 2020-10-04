package com.yl.soft.dto;

import java.io.Serializable;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbAudienceInfogrDto  implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "姓名")
	private String name;
//	@ApiModelProperty(value = "联系方式")
//	private String phone;
	@ApiModelProperty(value="企业名称")
	private String qyname;
	@ApiModelProperty(value = "头像")
	private String headPortrait;
	
}
