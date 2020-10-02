package com.yl.soft.dto;

import java.io.Serializable;
import java.time.LocalDateTime;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbLiveBroadcastDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "主标题")
    private String mainTitle;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "直播号")
    private String roomNum;

    @ApiModelProperty(value = "流名称")
    private String flowName;

    @ApiModelProperty(value = "直播时长")
    private Integer liveDuration;

    @ApiModelProperty(value = "直播结束时间")
    private LocalDateTime liveEndtime;

    @ApiModelProperty(value = "直播封面")
    private String liveImageUrl;

    @ApiModelProperty(value = "直播开始时间")
    private LocalDateTime liveStartTime;

    @ApiModelProperty(value = "直播状态 0即将开播 1直播中 2直播结束（回放）")
    private Integer liveStatus;

    @ApiModelProperty(value = "直播视频下载地址")
    private String videoDownUrl;

    @ApiModelProperty(value = "拉流地址")
    private String pullFlowUrl;

    @ApiModelProperty(value = "直播预告")
    private String notice;

    @ApiModelProperty(value = "直播回放地址")
    private String playback;

    @ApiModelProperty(value = "全部禁言（1：否，2：是）")
    private Integer forbiddenWords;

    @ApiModelProperty(value = "禁言成员（[用户id1,用户id2]）")
    private String forbiddenMember;
    
    @ApiModelProperty(value="主播公告")
    private String announcement;

}
