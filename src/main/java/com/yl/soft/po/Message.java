package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 消息
 * </p>
 *
 * @author ${author}
 * @since 2020-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Message对象", description="消息")
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "接收人id")
    private Integer ruid;

    @ApiModelProperty(value = "接收人姓名")
    private String runame;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "发送时间")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "状态 0未读 1已读")
    private Integer status;

    @ApiModelProperty(value = "图片地址")
    private String imgurl;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "接收消息类型(0 全部  1 展商 2 普通用户 3 其他用户")
    private Integer usertype;

    @ApiModelProperty(value = "是否删除 0 否 1 是")
    private Integer isdel;


}
