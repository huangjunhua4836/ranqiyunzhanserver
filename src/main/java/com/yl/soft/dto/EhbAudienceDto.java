package com.yl.soft.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbAudienceDto implements Serializable  {
	

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="企业")
	private EhbAudienceyeDto ehbAudienceyeDto;
	
	@ApiModelProperty(value="个人")
	private EhbAudiencegrDto AudienceyegrDto;
	
	@ApiModelProperty(value="绑定手机 0：未绑定，1：已绑定")
	private String phoneState;

	@ApiModelProperty(value="绑定微信 0：未绑定，1：已绑定")
	private String wxState;

	@ApiModelProperty(value="绑定qq 0：未绑定，1：已绑定")
	private String qqState;
	
	@ApiModelProperty(value="个人0:false,展商1:true")
	private Boolean userType;
	
}
