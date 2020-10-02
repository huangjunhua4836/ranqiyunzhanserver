package com.yl.soft.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbLiveBroadcastListDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "主标题")
    private String mainTitle;

    @ApiModelProperty(value = "副标题")
    private String subTitle;
    
    @ApiModelProperty(value = "直播封面")
    private String liveImageUrl;
    
    @ApiModelProperty(value = "直播状态 0即将开播 1直播中 2直播结束（回放）")
    private Integer liveStatus;
    
    @ApiModelProperty(value="类型名称")
    private String type;

}
