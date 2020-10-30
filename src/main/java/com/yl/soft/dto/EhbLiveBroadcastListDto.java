package com.yl.soft.dto;

import java.io.Serializable;
import java.util.List;

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

    @ApiModelProperty(value="直播回放/播放列表")
    private List<String> payList;

    @ApiModelProperty(value="直播类型  0-真实直播   1-虚拟直播")
    private Integer liveType;

    @ApiModelProperty(value="虚拟直播url")
    private String vmwareUrl;
    
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
