package com.yl.soft.dto.app;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 综合查询DTO
 */
@Data
public class CommonInfoDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "企业集合")
    private List<ExhibitorDto> exhibitorDtos;
	@ApiModelProperty(value = "商机集合")
    private List<OpportunityDto> opportunityDtos;
	@ApiModelProperty(value = "资讯集合")
    private List<ArticleDto> articleDtos;
}