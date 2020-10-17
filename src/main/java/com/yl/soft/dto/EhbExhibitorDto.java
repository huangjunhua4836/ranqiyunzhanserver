package com.yl.soft.dto;

import java.io.Serializable;

import com.yl.soft.common.util.StringUtils;
import org.springframework.beans.BeanUtils;

import com.yl.soft.po.EhbAudience;
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
    
    @ApiModelProperty(value="手机号")
    private String phone;
    
    @ApiModelProperty(value = "联系人")
    private String name;

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
    
    @ApiModelProperty(value="展商图片 多张图片逗号隔开")
    private String img;
    
    @ApiModelProperty(value="logo")
    private String logo;
    
    @ApiModelProperty(value="虚拟展厅（Vr html地址）")
    private String halurl;
    
    @ApiModelProperty(value="展位效果图，图片url地址")
    private String floorplan;
    
    
	@ApiModelProperty("是否已认证0:未认证，1:已认证 2:审核中 3：审核失败")
	private Integer state;

    @ApiModelProperty(value = "展商英文名称")
    private String englishname;
    
    @ApiModelProperty(value="我是否关注 0：未关注， 1：已关注")
    private Integer isColl;
    
    @ApiModelProperty(value="蜂鸟云地图Fid")
    private String fid;
	
	
//	@ApiModelProperty("展商图片 多张图片逗号隔开")
//	private List<String> bannerList;
	
	public static EhbExhibitorDto of(EhbExhibitor ehbExhibitor,EhbAudience user) {
		EhbExhibitorDto ehbExhibitorDto=new EhbExhibitorDto();
		if(ehbExhibitor==null) {
			return ehbExhibitorDto;
		}
		BeanUtils.copyProperties(ehbExhibitor, ehbExhibitorDto);
		ehbExhibitorDto.setFloorplan(ehbExhibitor.getZwimg());
		ehbExhibitorDto.setLogo(user.getHeadPortrait());
		ehbExhibitorDto.setEnglishname(ehbExhibitor.getEnglishname());
		ehbExhibitorDto.setTel(StringUtils.isEmpty(ehbExhibitor.getTelphone())?ehbExhibitor.getTel():ehbExhibitor.getTelphone());
//		List<String> list = JSONArray.parseArray(ehbExhibitor.getImg(), String.class);
//		ehbExhibitorDto.setBannerList(list);
		return ehbExhibitorDto;
	}
    
    

}
