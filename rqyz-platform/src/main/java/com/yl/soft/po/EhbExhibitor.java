package com.yl.soft.po;

import com.yl.soft.po.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 参展商信息
 * </p>
 *
 * @author ${author}
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbExhibitor对象", description="参展商信息")
public class EhbExhibitor extends BaseEntity implements Serializable {

    //state： 1-待审核  2-审核通过

    private static final long serialVersionUID=1L;

    private String name;

    private String describes;

    @ApiModelProperty(value = "头像")
    private String headPortrait;

    private String phone;

    private String tel;

    private String province;

    private String city;

    private String county;

    @ApiModelProperty(value = "登录名")
    private String loginname;

    @ApiModelProperty(value = "密码")
    private String password;

    private String enterprise;

    private String mailbox;

    private String labelid;

    private String businesslicense;

    private String credentials;

    private Integer isjoin;

    private String boothno;

    private String address;

    private String idcard;
}