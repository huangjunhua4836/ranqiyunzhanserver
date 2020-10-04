package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 反馈
 * </p>
 *
 * @author Chanhs
 * @since 2020-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Feedback对象", description="反馈")
public class Feedback implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "1=已删除，0=未删除")
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createat;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateat;

    @ApiModelProperty(value = "创建人")
    private Integer createby;

    @ApiModelProperty(value = "更新人")
    private Integer updateby;

    @ApiModelProperty(value = "已读标识：1=已读，0=未读")
    private Integer readed;

    @ApiModelProperty(value = "用户ID")    
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "反馈内容")
    private String content;

    @ApiModelProperty(value = "图片地址")
    private String media;

    @ApiModelProperty(value = "联系方式")
    private String concat;

    @ApiModelProperty(value = "反馈时间")
    private LocalDateTime feedbackat;


}
