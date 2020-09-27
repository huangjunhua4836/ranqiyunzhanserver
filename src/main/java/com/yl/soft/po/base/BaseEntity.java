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
	@ApiModelProperty
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(hidden = true)
	@TableField("createuser")
	private Integer createuser;

	@ApiModelProperty(hidden = true)
	@TableField("createtime")
    private LocalDateTime createtime;

	@ApiModelProperty(hidden = true)
	@TableField("updateuser")
	private Integer updateuser;

	@ApiModelProperty(hidden = true)
	@TableField("updatetime")
	private LocalDateTime updatetime;

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