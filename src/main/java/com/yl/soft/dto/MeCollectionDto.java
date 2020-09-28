package com.yl.soft.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MeCollectionDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("企业ID")
	private Integer id;
	
	@ApiModelProperty("企业名称")
	private String enterprisename;
	
	@ApiModelProperty("展位")
	private String boothno;
	
	@ApiModelProperty("企业图片")
	private String img;
	
	
}
