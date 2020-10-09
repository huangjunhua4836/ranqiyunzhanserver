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
    
    @ApiModelProperty("企业邮箱")
    private String mailbox;
    
    @ApiModelProperty(value = "公司主页")
    private String website;
    
    @ApiModelProperty("企业描述")
    private String describes;
    
    @ApiModelProperty(value="展商图片['src1','src2'....]")
    private String img;
    
    @ApiModelProperty(value="logo")
    private String logo;
    
    @ApiModelProperty(value="虚拟展厅（Vr html地址）")
    private String halurl;
    
    @ApiModelProperty(value="展位效果图，图片url地址")
    private String floorplan;
    
    
	@ApiModelProperty("是否已认证0:未认证，1:已认证 2:审核中 3：审核失败")
	private Integer state;
	
	
	@ApiModelProperty("企业图片集合['src1','src2'....]")
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
