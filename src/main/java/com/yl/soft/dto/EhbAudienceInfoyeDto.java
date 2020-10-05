package com.yl.soft.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbAudienceInfoyeDto  implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "企业名称")
	private String enterprisename;
	
	@ApiModelProperty(value = "头像")
	private String headPortrait;
	
	@ApiModelProperty(value = "联系方式")
	private String phone;
	
	@ApiModelProperty(value="邮箱")
    private String mailbox;
	
	@ApiModelProperty("企业电话")
	private String telphone;
	@ApiModelProperty(value="联系人")
	private String exname;
	
}
