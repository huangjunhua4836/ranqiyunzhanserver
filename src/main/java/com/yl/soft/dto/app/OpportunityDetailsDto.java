package com.yl.soft.dto.app;

import com.yl.soft.dto.EhbOpportunityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbArticle对象", description="资讯表")
public class OpportunityDetailsDto extends EhbOpportunityDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "标签翻译")
	private List<String> labelStrings;

	@ApiModelProperty(value = "发布作者ID")
	private Integer userid;
}
