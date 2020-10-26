package com.yl.soft.vo;

import com.yl.soft.po.EhbVisitorRegistration;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VisitorRegisVo对象", description="参展预登记VO对象")
public class VisitorRegisVo extends EhbVisitorRegistration {
    private String startTime;
    private String endTime;
}