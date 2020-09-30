package com.yl.soft.common.unified.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="基础分页信息")
public class BasePage<T> {
    @ApiModelProperty(value = "当前页码")
    private Integer pageNum;
    @ApiModelProperty(value = "每页显示数量")
    private Integer pageSize;
    @ApiModelProperty(value = "总页数")
    private Integer pages;
    @ApiModelProperty(value = "总条数")
    private Long total;
    @ApiModelProperty(value = "数据")
    private T data;

    public BasePage(Integer pageNum, Integer pageSize, Integer pages, Long total,T data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
        this.total = total;
        this.data = data;
    }
}