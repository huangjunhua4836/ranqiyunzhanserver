package com.yl.soft.po;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Stationinfo对象", description="")
public class Stationinfo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "通知内容标题")
    @TableField("notification_title")
    private String notificationTitle;

    @ApiModelProperty(value = "消息内容标题")
    @TableField("msg_title")
    private String msgTitle;

    @ApiModelProperty(value = "消息内容")
    @TableField("msg_content")
    private String msgContent;

    @ApiModelProperty(value = "扩展字段")
    private String extrasparam;

    @ApiModelProperty(value = "1-成功  0-失败  2-未发送")
    private Integer issuccess;

    private LocalDateTime createtime;

    @ApiModelProperty(value = "设备标识")
    @TableField("registrationId")
    private String registrationId;

    @ApiModelProperty(value = "别名")
    private String bieming;

    @ApiModelProperty(value = "0-正常  1-删除")
    private Boolean isdel;

    @ApiModelProperty(value = "1-安卓  2-IOS  3-ALL")
    private Integer sendtype;

    @ApiModelProperty(value = "图片地址")
    private String imgurl;

    @ApiModelProperty(value = "链接地址")
    private String url;
}
