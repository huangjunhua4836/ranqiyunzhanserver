package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@ApiModel(value="EhbWonderfulVideo对象", description="")
public class EhbWonderfulVideo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "精彩视频ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "直播ID")
    @TableField("live_id")
    private Integer liveId;

    @ApiModelProperty(value = "精彩视频地址")
    @TableField("video_url")
    private String videoUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "封面图")
    @TableField("cover_image")
    private String coverImage;

    @ApiModelProperty(value = "标题")
    private String title;
    
    @ApiModelProperty(value = "删除（1：否，2：是）")
    private Integer isdel;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;

}
