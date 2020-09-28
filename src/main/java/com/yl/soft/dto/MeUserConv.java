package com.yl.soft.dto;

import org.springframework.beans.BeanUtils;

import com.yl.soft.po.EhbAudience;


public interface MeUserConv {

	public static EhbAudienceDto do2dto(EhbAudience ehbAudience) {
		EhbAudienceDto ehbAudienceDto = new EhbAudienceDto();
		if(ehbAudience==null){
			return ehbAudienceDto;
		}
		BeanUtils.copyProperties(ehbAudience, ehbAudienceDto);
		return ehbAudienceDto;
	}

}
