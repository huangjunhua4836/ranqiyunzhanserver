package com.yl.soft.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbHallDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "展商ID")
    private Integer exhibitorid;

    @ApiModelProperty(value = "展商名称")
    private String exhibitorname;

    @ApiModelProperty(value = "展位号")
    private String boothno;

    @ApiModelProperty(value="vrHTML地址")
    private String hallurl;

    @ApiModelProperty(value="浏览量")
    private Integer views;
	
	

}
