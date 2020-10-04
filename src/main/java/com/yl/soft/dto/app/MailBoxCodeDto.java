package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.yl.soft.po.EhbLabel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MailBoxCodeDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "邮箱")
	private Integer id;
	
	public static MailBoxCodeDto of(EhbLabel po) {
		MailBoxCodeDto dto = new MailBoxCodeDto();
		BeanUtil.copyProperties(po,dto);
		return dto;
	}
}
