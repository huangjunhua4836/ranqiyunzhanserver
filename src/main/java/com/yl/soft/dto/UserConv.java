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

	public static EhbAudienceDto do2dto(EhbAudience user) {
		EhbAudienceDto userDto = new EhbAudienceDto();
		BeanUtils.copyProperties(user, userDto, "passwword");
		return userDto;
	}

}
