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
	
}
