package com.yl.soft.dto;

import com.yl.soft.po.EhbOpportunity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="商机信息")
public class OpportunityDto extends EhbOpportunity {
    @ApiModelProperty(value = "企业名称")
    private String enterprisename;
}