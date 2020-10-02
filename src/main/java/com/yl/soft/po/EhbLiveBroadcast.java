package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbLiveBroadcast对象", description="")
public class EhbLiveBroadcast implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "主标题")
    @TableField("main_title")
    private String mainTitle;

    @ApiModelProperty(value = "副标题")
    @TableField("sub_title")
    private String subTitle;

    @ApiModelProperty(value = "直播号")
    @TableField("room_num")
    private String roomNum;

    @ApiModelProperty(value = "流名称")
    @TableField("flow_name")
    private String flowName;

    @ApiModelProperty(value = "直播时长")
    @TableField("live_duration")
    private Integer liveDuration;

    @ApiModelProperty(value = "直播结束时间")
    @TableField("live_endTime")
    private LocalDateTime liveEndtime;

    @ApiModelProperty(value = "直播封面")
    @TableField("live_image_url")
    private String liveImageUrl;

    @ApiModelProperty(value = "私密口令")
    @TableField("live_password")
    private String livePassword;

    @ApiModelProperty(value = "直播开始时间")
    @TableField("live_start_time")
    private LocalDateTime liveStartTime;

    @ApiModelProperty(value = "直播状态 0即将开播 1直播中 2直播结束（回放）")
    @TableField("live_status")
    private Integer liveStatus;

    @ApiModelProperty(value = "直播视频下载地址")
    @TableField("video_down_url")
    private String videoDownUrl;

    @ApiModelProperty(value = "推流地址")
    @TableField("push_flow_url")
    private String pushFlowUrl;

    @ApiModelProperty(value = "拉流地址")
    @TableField("pull_Flow_url")
    private String pullFlowUrl;

    @ApiModelProperty(value = "直播预告")
    private String notice;

    @ApiModelProperty(value = "直播回放地址")
    private String playback;

    @ApiModelProperty(value = "全部禁言（1：否，2：是）")
    @TableField("forbidden_words")
    private Integer forbiddenWords;

    
    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deltime;

    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;

    
    @ApiModelProperty(value = "排序")
    private Integer sort;
    
    @JsonIgnore
    @ApiModelProperty(value = "删除（1：否，2：是）")
    private Integer isdel;

    @ApiModelProperty(value = "禁言成员（[用户id1,用户id2]）")
    @TableField("forbidden_member")
    private String forbiddenMember;
    
    @ApiModelProperty(value="类别名称")
    private String type;
    
    @ApiModelProperty(value="主播公告")
    private String announcement;


}
