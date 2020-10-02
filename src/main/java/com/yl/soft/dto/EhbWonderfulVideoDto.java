package com.yl.soft.dto;

import java.io.Serializable;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbWonderfulVideoDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "精彩视频ID")
    private Integer id;

    @ApiModelProperty(value = "直播ID")
    private Integer liveId;

    @ApiModelProperty(value = "精彩视频地址")
    private String videoUrl;

    @ApiModelProperty(value = "封面图")
    private String coverImage;

    @ApiModelProperty(value = "标题")
    private String title;
}
