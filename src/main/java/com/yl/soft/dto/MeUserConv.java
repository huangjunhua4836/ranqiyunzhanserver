package com.yl.soft.dto;

import org.springframework.beans.BeanUtils;

import com.yl.soft.po.EhbAudience;
import com.yl.soft.po.EhbExhibitor;


public interface MeUserConv {

	public static EhbAudienceyeDto do2dto(EhbAudience ehbAudience,EhbExhibitor ehbExhibitor) {
		EhbAudienceyeDto ehbAudienceDto = new EhbAudienceyeDto();
		if(ehbAudience==null){
			return ehbAudienceDto;
		}
		BeanUtils.copyProperties(ehbAudience, ehbAudienceDto);
		ehbAudienceDto.setName(ehbExhibitor.getState()==1?ehbExhibitor.getName():ehbAudience.getName());
		ehbAudienceDto.setState(ehbExhibitor.getState()+"");
		return ehbAudienceDto;
	}
	
	public static EhbAudiencegrDto do2dto1(EhbAudience ehbAudience) {
		EhbAudiencegrDto ehbAudienceDto = new EhbAudiencegrDto();
		if(ehbAudience==null){
			return ehbAudienceDto;
		}
		BeanUtils.copyProperties(ehbAudience, ehbAudienceDto);
		return ehbAudienceDto;
	}

}
