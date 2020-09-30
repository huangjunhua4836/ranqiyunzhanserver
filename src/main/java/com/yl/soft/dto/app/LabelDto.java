package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.yl.soft.po.EhbLabel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LabelDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "标签ID")
	private Integer id;
	
	@ApiModelProperty(value = "标签名称")
	private String name;

	public static LabelDto of(EhbLabel po) {
		LabelDto dto = new LabelDto();
		BeanUtil.copyProperties(po,dto);
		return dto;
	}
}
