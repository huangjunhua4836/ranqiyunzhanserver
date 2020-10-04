package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.yl.soft.po.EhbExhibitor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 展商列表、展商详情DTO
 */
@Data
public class ExhibitorDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "企业ID")
    private Integer id;

    @ApiModelProperty(value = "企业名称")
    private String enterprisename;

    @ApiModelProperty(value="logo")
    private String logo;

    @ApiModelProperty("企业描述")
    private String describes;

    @ApiModelProperty(value = "展位号")
    private String boothno;

    @ApiModelProperty(value = "管理人员")
    private String name;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "座机")
    private String tel;

    @ApiModelProperty(value = "绑定邮箱")
    private String mailbox;

    @ApiModelProperty(value = "公司主页")
    private String website;

    @ApiModelProperty(value = "公司地址")
    private String address;

    @ApiModelProperty(value="展商图片")
    private String img;
    
	@ApiModelProperty("是否已认证  0-未认证，1-已认证")
	private Integer state;

	@ApiModelProperty("关注展商ID(等于空-展商没有关注，非空-此展商已关注)")
	private Integer relateid;

	@ApiModelProperty("认证状态翻译")
	private String state_show;

    public static ExhibitorDto of(EhbExhibitor ehbExhibitor) {
        ExhibitorDto exhibitorDto = new ExhibitorDto();
        BeanUtil.copyProperties(ehbExhibitor,exhibitorDto);
        if(ehbExhibitor.getState() == 0){
            exhibitorDto.setState_show("未认证");
        }else if(ehbExhibitor.getState() == 1){
            exhibitorDto.setState_show("已认证");
        }else{
            exhibitorDto.setState_show("未知状态");
        }
        return exhibitorDto;
    }
}