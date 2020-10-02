package com.yl.soft.dto;

import org.springframework.beans.BeanUtils;

import com.yl.soft.po.EhbAudience;

/**
 * <p>
 * 用户转换器
 * </p>
 *
 * @author Chanhs
 * @since 2020-09-07
 */
public interface UserConv {

	public static EhbAudiencedlDto do2dto(EhbAudience user) {
		EhbAudiencedlDto userDto = new EhbAudiencedlDto();
		BeanUtils.copyProperties(user, userDto, "passwword");
		return userDto;
	}

}
