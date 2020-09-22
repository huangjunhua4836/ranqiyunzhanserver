package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.yl.soft.po.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CrmMenu对象", description="菜单表")
public class CrmMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "菜单名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "菜单url")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "0：普通菜单 1：按钮菜单 2：空菜单")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "on：必须 off：非必须")
    @TableField("ismust")
    private Boolean ismust;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "上级菜单")
    @TableField("parentId")
    private Integer parentId;

    @ApiModelProperty(value = "层级")
    @TableField("level")
    private Integer level;

    @ApiModelProperty(value = "权限值")
    @TableField("permissionValue")
    private String permissionValue;
}