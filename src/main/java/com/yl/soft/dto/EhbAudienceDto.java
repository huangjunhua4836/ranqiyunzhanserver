package com.yl.soft.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yl.soft.po.EhbExhibitor;

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
	
		@ApiModelProperty(value = "姓名")
	 	private String name;

	    @ApiModelProperty(value = "头像")
	    @TableField("head_portrait")
	    private String headPortrait;

	    @ApiModelProperty(value = "联系方式")
	    private String phone;

	    @ApiModelProperty(value="企业名称")
	    private String enterprise;
	    
	    @ApiModelProperty(value="标签：字符串数组1,2,3")
	    private String labelid;

	    @ApiModelProperty("展商ID")
	    private Integer bopie;
	    
}
