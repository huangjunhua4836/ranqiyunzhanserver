package com.yl.soft.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbBannerDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片地址")
    private String imgurl;

    @ApiModelProperty(value = "链接地址")
    private String linkurl;

}
