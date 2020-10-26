package com.yl.soft.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value="FeedbackExcelVo对象", description="反馈")
public class FeedbackExcelVo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "反馈内容")
    private String content;

    @ApiModelProperty(value = "联系方式")
    private String concat;

    @ApiModelProperty(value = "已读标识：1=已读，0=未读")
    private String readed;

    @ApiModelProperty(value = "反馈时间")
    private String feedbackat;

    @ApiModelProperty(value = "创建时间")
    private String createat;
}