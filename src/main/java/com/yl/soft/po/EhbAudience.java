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
 * 参展用户信息
 * </p>
 *
 * @author ${author}
 * @since 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbAudience对象", description="参展用户信息")
public class EhbAudience extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    private String name;

    private String describes;

    @ApiModelProperty(value = "头像")
    @TableField("head_portrait")
    private String headPortrait;

    private String phone;

    private String province;

    private String city;

    private String county;

    @ApiModelProperty(value = "登录名称")
    private String loginname;

    @ApiModelProperty(value = "密码")
    private String password;

    private String enterprise;

    private String mailbox;

    private String labelid;
    
    private Integer enabled;

    @ApiModelProperty(value = "是否展商  0：不是  1：是",hidden = true)
    @TableField("iszs")
    private Boolean iszs;
    
    private String wxOpenid;
    
    private String qqOpenid;
}