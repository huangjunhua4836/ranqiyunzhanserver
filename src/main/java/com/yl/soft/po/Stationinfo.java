package com.yl.soft.po;

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
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Stationinfo对象", description="")
public class Stationinfo implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    @ApiModelProperty(value = "通知内容标题")
    private String notificationTitle;

    @ApiModelProperty(value = "消息内容标题")
    private String msgTitle;

    @ApiModelProperty(value = "消息内容")
    private String msgContent;

    @ApiModelProperty(value = "扩展字段")
    private String extrasparam;

    @ApiModelProperty(value = "1-成功  0-失败")
    private Integer issuccess;

    private LocalDateTime createtime;

    @ApiModelProperty(value = "设备标识")
    @TableField("registrationId")
    private String registrationId;

    @ApiModelProperty(value = "别名")
    private String bieming;

    @ApiModelProperty(value = "0-正常  1-删除")
    private Integer isdel;
}
