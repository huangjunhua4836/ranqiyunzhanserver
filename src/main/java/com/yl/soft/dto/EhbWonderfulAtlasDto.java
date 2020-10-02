package com.yl.soft.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbWonderfulAtlasDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "精彩图片ID")
    private Integer id;

    @ApiModelProperty(value = "图片地址")
    private String imgUrl;

    @ApiModelProperty(value = "直播ID")
    private Integer liveId;
    
    @ApiModelProperty(value="图片宽度")
    private Integer wide;
    
    @ApiModelProperty(value="图片高度")
    private Integer high;


	
}
