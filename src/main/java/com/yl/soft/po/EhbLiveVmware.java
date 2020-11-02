package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class EhbLiveVmware implements Serializable {

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

    @ApiModelProperty(value="类型名称")
    private String type;

    @ApiModelProperty(value = "直播封面")//上传封面
    @TableField("live_image_url")
    private String liveImageUrl;

    @ApiModelProperty(value = "直播状态 0  即将开播 1 直播中 2直播结束（回放）")
    @TableField("live_status")
    private Integer liveStatus;

    @ApiModelProperty(value = "H5虚拟直播地址")//服务器加
    private String playback;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value="主播公告")
    private String announcement;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//入参格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//出参格式化
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;
}