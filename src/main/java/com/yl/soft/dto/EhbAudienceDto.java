package com.yl.soft.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "用户")
public class EhbAudienceDto implements Serializable  {
	

	private static final long serialVersionUID = 1L;
	
		//登录用户id
		@ApiModelProperty(value = "用户id")
		private Integer id;
	
		@ApiModelProperty(value = "昵称")
	 	private String name;

	    @ApiModelProperty(value = "头像")
	    @TableField("head_portrait")
	    private String headPortrait;

	    @ApiModelProperty(value = "手机号")
	    private String phone;

	    @ApiModelProperty(value = "登录名称")
	    private String loginname;

	    @ApiModelProperty(value = "密码")
	    private String password;

	    @ApiModelProperty(value="标签：字符串数组1,2,3")
	    private String labelid;

	    @ApiModelProperty(value = "是否展商  0：不是  1：是")
	    @TableField("iszs")
	    private Boolean iszs;
}
