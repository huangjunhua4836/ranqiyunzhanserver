package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yl.soft.po.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 机构成员/科室
 * </p>
 *
 * @author ${author}
 * @since 2020-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CrmMember对象", description="机构成员/科室")
public class CrmMember extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("memberName")
    private String memberName;

    @TableField("parentId")
    private Integer parentId;

    @TableField("organizationId")
    private Integer organizationId;

    @TableField("phone")
    private String phone;
}
