package com.yl.soft.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbAudienceInfoDto  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="个人")
	private EhbAudienceInfogrDto ehbAudienceInfogrDto;
	
	@ApiModelProperty(value="企业")
	private EhbAudienceInfoyeDto ehbAudienceInfoyeDto;
	
	@ApiModelProperty(value="登录标识")
	private String token;
	

}
