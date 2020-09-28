package com.yl.soft.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONArray;
import com.yl.soft.po.EhbExhibitor;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbExhibitorDto  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "企业ID")
    private Integer id;


    @ApiModelProperty(value = "企业名称")
    private String enterprisename;

    @ApiModelProperty(value = "电话号")
    private String tel;

    @ApiModelProperty(value = "展位号")
    private String boothno;

    @ApiModelProperty(value = "公司地址")
    private String address;
    

    @ApiModelProperty(value = "公司主页")
    private String website;
    
    @ApiModelProperty("企业描述")
    private String describes;
    
    @ApiModelProperty(value="展商图片")
    private String img;
    
    @ApiModelProperty(value="logo")
    private String logo;
    
	@ApiModelProperty("是否已认证0未认证，1已认证")
	private Integer state;
	
	
	@ApiModelProperty("banners")
	private List<String> bannerList;
	
	public static EhbExhibitorDto of(EhbExhibitor ehbExhibitor) {
		EhbExhibitorDto ehbExhibitorDto=new EhbExhibitorDto();
		if(ehbExhibitor==null) {
			return ehbExhibitorDto;
		}
		BeanUtils.copyProperties(ehbExhibitor, ehbExhibitorDto);
		List<String> list = JSONArray.parseArray(ehbExhibitor.getImg(), String.class);
		ehbExhibitorDto.setBannerList(list);
		return ehbExhibitorDto;
	}
    
    

}
