package com.yl.soft.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class EhbAudienceyeDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 登录用户id
	@ApiModelProperty(value = "用户id")
	private Integer id;

	@ApiModelProperty(value = "用户姓名")
	private String name;
	
	@ApiModelProperty(value = "企业名称")
	private String enterprisename;

	@ApiModelProperty(value = "头像")
	@TableField("head_portrait")
	private String headPortrait;

	@ApiModelProperty(value = "联系方式")
	private String phone;

	@ApiModelProperty(value = "标签：字符串数组1,2,3")
	private String labelid;
	
	@ApiModelProperty(value="联系人")
	private String exname;
	
	@ApiModelProperty(value="认证状态（0：未审核  1：审核通过 2:审核中，3审核失败）")
	private String state;
	
	@ApiModelProperty(value="邮箱")
    private String mailbox;
	
	@ApiModelProperty(value="二维码")
	private String qrcode;

	@ApiModelProperty("展商ID")
	private Integer bopie;
	
	@ApiModelProperty("企业电话")
	private String telphone;
	
	@ApiModelProperty("登录手机号")
	private String loginAccount;
	
	
    @ApiModelProperty(value = "公司地址")
    private String address;
    
    @ApiModelProperty(value = "公司主页")
    private String website;

	@ApiModelProperty(value = "身份证")
	private String idcard;
	
    @ApiModelProperty(value = "展商英文名称")
    private String englishname;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//入参格式化
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//出参格式化
	@ApiModelProperty(value = "认证时间")
	private LocalDateTime certificationtime;
}