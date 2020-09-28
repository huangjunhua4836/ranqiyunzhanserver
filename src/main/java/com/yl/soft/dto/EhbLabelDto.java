package com.yl.soft.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.yl.soft.po.EhbLabel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbLabelDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "标签ID")
	private Integer id;
	
	@ApiModelProperty(value = "标签名称")
	private String name;
	

}
