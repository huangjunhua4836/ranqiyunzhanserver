package com.yl.soft.vo;

import com.yl.soft.po.Feedback;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="FeedbackVo对象", description="反馈")
public class FeedbackVo extends Feedback {
    private String startTime;
    private String endTime;
}
