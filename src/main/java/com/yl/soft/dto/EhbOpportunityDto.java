package com.yl.soft.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.yl.soft.po.EhbExhibitor;
import com.yl.soft.po.EhbLabel;
import com.yl.soft.po.EhbOpportunity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbArticle对象", description="资讯表")
public class EhbOpportunityDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "商品id")
	private Integer id;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "内容")
	private String content;

	@ApiModelProperty(value = "图片")
	private String picture;

	@ApiModelProperty(value = "标签返回")
	private List<EhbLabelDto> labels;

	@ApiModelProperty(value = "企业名称")
	private String exhibitorname;

	@ApiModelProperty(value = "企业id")
	private String exhibitorid;
	
	@ApiModelProperty(value = "企业认证（0:未认证，1:已认证，2:审核中，3审核失败")
	private Integer attestation;

	@ApiModelProperty(value = "总收藏量")
	private Integer countcollection;

	@ApiModelProperty(value = "总点赞量")
	private Integer countthumbs;

	@ApiModelProperty(value = "总关注量")
	private Integer countfollow;

	@ApiModelProperty(value = "总浏览量")
	private Integer countbrowse;

	@ApiModelProperty(value = "总评论量")
	private Integer countcomment;
	
    @ApiModelProperty(value="当前我是否点赞商机 0:否，1：是")
    private Integer isSjZan;
    
    @ApiModelProperty(value="当前我是否点赞商品 0:否，1：是")
    private Integer isSpZan;

    @ApiModelProperty(value="当前我是否收藏商机0:否，1：是")
    private Integer isSCsj;
    
    @ApiModelProperty(value="当前我是否收藏商商品0:否，1：是")
    private Integer isSCsp;

    @ApiModelProperty(value="当前我是否收藏企业0:否，1：是")
    private Integer isSCqy;

	public static EhbOpportunityDto of(EhbOpportunity ehbOpportunity, EhbExhibitor ehbExhibitor, Integer i,
			Map<Integer, EhbLabel> map) {
		EhbOpportunityDto ehbOpportunityDto = new EhbOpportunityDto();
		BeanUtils.copyProperties(ehbOpportunity, ehbOpportunityDto);
		ehbOpportunityDto.setExhibitorid(ehbExhibitor.getId() + "");
		ehbOpportunityDto.setExhibitorname(ehbExhibitor.getName());
		if(null!=ehbOpportunity.getLabel()) {
			List<EhbLabelDto> ehbLabelDtos = JSONObject.parseArray(ehbOpportunity.getLabel(), Integer.class).stream()
					.map(j -> {
						EhbLabelDto ehbLabelDto = new EhbLabelDto();
						EhbLabel ehbLabel = map.get(j);
						BeanUtils.copyProperties(ehbLabel, ehbLabelDto);
						return ehbLabelDto;
					}).collect(Collectors.toList());
			ehbOpportunityDto.setLabels(ehbLabelDtos);
		}
		// 企业认证（1：以认证，2：未认证  3：审核中 4：审核失败）
		ehbOpportunityDto.setAttestation(i > 0 ? ehbExhibitor.getState() : 2);
		return ehbOpportunityDto;
	}
}
