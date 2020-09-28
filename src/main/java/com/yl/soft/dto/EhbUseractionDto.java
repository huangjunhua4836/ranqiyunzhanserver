package com.yl.soft.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.yl.soft.dto.base.SessionUser;
import com.yl.soft.po.EhbUseraction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EhbUseractionDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "收藏人")
    private Integer userid;

    @ApiModelProperty(value = "1：企业   2：商机  3：资讯")
    private Integer type;

    @ApiModelProperty(value = "关联ID")
    private Integer relateid;

    @ApiModelProperty(value = "1：收藏    2：点赞    3：关注    4：浏览")
    private Integer activetype;
    
	public static EhbUseraction of(SessionUser sessioner,Integer type,Integer relateid,Integer activetype) {
		EhbUseraction eBEhbUseraction=new EhbUseraction();
		eBEhbUseraction.setCreateuser(sessioner.getId());
		eBEhbUseraction.setCreatetime(LocalDateTime.now());
		eBEhbUseraction.setState(0);
		eBEhbUseraction.setIsdel(false);
		eBEhbUseraction.setUserid(sessioner.getId());
		eBEhbUseraction.setType(type);
		eBEhbUseraction.setRelateid(relateid);
		return eBEhbUseraction.setActivetype(activetype);
	}

}
