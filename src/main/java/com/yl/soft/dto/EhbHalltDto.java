package com.yl.soft.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbHalltDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="推荐顶部的展商")
	private EhbHallDto ehbHallDto;
	
	@ApiModelProperty(value="展商列表")
	public List<EhbHallDto> ehbHallDtoslist;

}
