package com.yl.soft.po.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseEntity implements Serializable{
	@ApiModelProperty(hidden = true)
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(hidden = true)
	@TableField("createUser")
	private Integer createUser;

	@ApiModelProperty(hidden = true)
	@TableField("createTime")
    private LocalDateTime createTime;

	@ApiModelProperty(hidden = true)
	@TableField("updateUser")
	private Integer updateUser;

	@ApiModelProperty(hidden = true)
	@TableField("updateTime")
	private LocalDateTime updateTime;

	@ApiModelProperty(hidden = true)
	@TableField("state")
	private Integer state;

	@ApiModelProperty(hidden = true)
	@TableField("version")
	private Integer version;

	@ApiModelProperty(hidden = true)
	@TableField("isdel")
	private Boolean isdel;  //tinyint(1)
}