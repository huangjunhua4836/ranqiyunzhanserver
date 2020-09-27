package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yl.soft.po.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CrmUser对象", description="用户表")
public class CrmUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("userCode")
    private String userCode;

    @TableField("nickname")
    private String nickname;

    @TableField("password")
    private String password;

    @TableField("describes")
    private String describes;

    @TableField("organizationId")
    private Integer organizationId;

    @TableField("memberId")
    private Integer memberId;

    @ApiModelProperty(value = "权重值 默认值为 0")
    @TableField("weight")
    private Integer weight;

    @ApiModelProperty(value = "头像")
    @TableField("headPortrait")
    private String headPortrait;

    @TableField("phone")
    private String phone;
}
