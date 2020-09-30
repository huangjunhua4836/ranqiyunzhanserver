package com.yl.soft.dto.app;

import com.yl.soft.common.unified.entity.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * 展商列表、展商详情DTO
 */
@Data
public class Exhibitor_AdvertisingDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BasePage<ExhibitorDto> exhibitorDtoBasePage;

	private AdvertisingDto advertisingDto;
}