/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yl.soft.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ResultItem对象", description = "响应结果")
public class ResultItem<T> extends BaseResult<T> {

	@ApiModelProperty(value = "当前页码")
	private Integer pageIndex;
	@ApiModelProperty(value = "每页显示数量")
	private Integer pageLimit;
	@ApiModelProperty(value = "总页数")
	private Integer pageTotal;
	@ApiModelProperty(value = "总条数")
	private Long total;

}
