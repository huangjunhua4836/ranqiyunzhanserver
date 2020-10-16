package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

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
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbLiveRecording对象", description="")
public class EhbLiveRecording implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("project_id")
    private String projectId;

    @TableField("publish_domain")
    private String publishDomain;

    @TableField("event_type")
    private String eventType;

    @TableField("app")
    private String app;

    @TableField("stream")
    private String stream;

    @TableField("record_format")
    private String recordFormat;

    @TableField("download_url")
    private String downloadUrl;

    @TableField("asset_id")
    private String assetId;

    @TableField("play_url")
    private String playUrl;

    @TableField("file_size")
    private Integer fileSize;

    @TableField("record_duration")
    private Integer recordDuration;

    @TableField("start_time")
    private String startTime;

    @TableField("end_time")
    private String endTime;

    @TableField("width")
    private Integer width;

    @TableField("height")
    private Integer height;

    @TableField("obs_location")
    private String obsLocation;

    @TableField("obs_bucket")
    private String obsBucket;

    @TableField("obs_object")
    private String obsObject;

    @TableField("error_message")
    private String errorMessage;
    
    private LocalDateTime createtime;
    
    private Integer liveid;


}
