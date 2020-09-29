package com.yl.soft.dto.app;

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
}