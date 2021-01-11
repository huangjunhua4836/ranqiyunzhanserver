package com.yl.soft.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VisitorRegisExcelVo对象", description="参展预登记导出Excel对象")
public class VisitorRegisExcelVo {

    @ApiModelProperty(value = "联系人")
    private String name;

    @ApiModelProperty(value="称谓（0=男，1=女）")
    private String appellation;

    @ApiModelProperty(value="职位")
    private String position;

    @ApiModelProperty(value = "公司名称")
    private String compname;

    @ApiModelProperty(value="联系方式")
    private String phone;

    @ApiModelProperty(value = "预展位面积（两个值中间用英文逗号分隔）")
    private String showarea;

    @ApiModelProperty(value = "备注")
    private String remarks;
}