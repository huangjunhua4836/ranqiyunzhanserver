package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色用户关系表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CrmRoleUser对象", description="角色用户关系表")
public class CrmRoleUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "roleId",type = IdType.INPUT)
    private Integer roleId;

    @TableField("userId")
    private Integer userId;


}
