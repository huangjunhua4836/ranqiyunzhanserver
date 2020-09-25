package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class EhbExhibitor implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;

    @ApiModelProperty(value = "管理人员")
    private String managerman;

    @ApiModelProperty(value = "身份证")
    private String idcard;

    @ApiModelProperty(value = "企业名称")
    private String enterprisename;

    @ApiModelProperty(value = "座机")
    private String tel;

    @ApiModelProperty(value = "营业执照")
    private String businesslicense;

    @ApiModelProperty(value = "企业授权书")
    private String credentials;

    @ApiModelProperty(value = "曾经是否加入 1-加入  0-未加入")
    private Boolean isjoin;

    @ApiModelProperty(value = "展位号")
    private String boothno;

    @ApiModelProperty(value = "地址")
    private String address;
}