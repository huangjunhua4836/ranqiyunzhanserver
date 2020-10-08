package com.yl.soft.dto.app;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.yl.soft.po.EhbAudience;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbCommentDto implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="评论id")
    private Integer id;

    @ApiModelProperty(value = "评论人ID")
    private Integer userid;
    
    private EhbAudience user;

    @ApiModelProperty(value = "上级评论")
    private Integer pid;

    @ApiModelProperty(value = "1：企业   2：商机  3：资讯")
    private Integer type;

    @ApiModelProperty(value="关联id")
    private Integer relateid;

    @ApiModelProperty(value="评论内容")
    private String content;
    
    @ApiModelProperty(value="评论时间")
    private LocalDateTime createtime;
    
}
