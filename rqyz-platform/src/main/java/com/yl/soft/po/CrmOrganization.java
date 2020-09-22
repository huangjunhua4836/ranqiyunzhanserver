package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.yl.soft.po.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 机构表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CrmOrganization对象", description="机构表")
public class CrmOrganization extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("orgName")
    private String orgName;

    @TableField("logo")
    private String logo;

    @TableField("orgAddress")
    private String orgAddress;

    @TableField("orgPhone")
    private String orgPhone;
}