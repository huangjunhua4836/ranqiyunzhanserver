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
		ehbAudienceDto.setEnterprisename(ehbExhibitor.getEnterprisename());
		ehbAudienceDto.setState(ehbExhibitor.getState()+"");
		ehbAudienceDto.setTelphone(ehbExhibitor.getTel());
		ehbAudienceDto.setPhone(ehbAudience.getPhone());
		ehbAudienceDto.setMailbox(ehbExhibitor.getMailbox());
		ehbAudienceDto.setAddress(ehbExhibitor.getAddress());
		ehbAudienceDto.setWebsite(ehbExhibitor.getWebsite());
		ehbAudienceDto.setExname(ehbExhibitor.getName());
		ehbAudienceDto.setIdcard(ehbExhibitor.getIdcard());
		ehbAudienceDto.setEnglishname(ehbExhibitor.getEnglishname());
		ehbAudienceDto.setCertificationtime(ehbExhibitor.getCertificationtime());
		return ehbAudienceDto;
	}
	
	public static EhbAudiencegrDto do2dto1(EhbAudience ehbAudience) {
		EhbAudiencegrDto ehbAudienceDto = new EhbAudiencegrDto();
		if(ehbAudience==null){
			return ehbAudienceDto;
		}
		BeanUtils.copyProperties(ehbAudience, ehbAudienceDto);
		ehbAudienceDto.setState(ehbAudience.getState()+"");
		return ehbAudienceDto;
	}

}
