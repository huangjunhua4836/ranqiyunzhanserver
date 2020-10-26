package com.yl.soft.vo;

import com.yl.soft.po.Problem;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProblemVo对象", description="")
public class ProblemVo extends Problem {

    private static final long serialVersionUID=1L;

    private String startTime;
    private String endTime;
}