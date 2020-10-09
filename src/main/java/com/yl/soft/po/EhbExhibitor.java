package com.yl.soft.po;

import com.yl.soft.po.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    //state 0：未认证  1：审核通过 2:审核中，3审核失败
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "管理人员")
    private String name;

    @ApiModelProperty(value = "企业描述")
    private String describes;

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

    @ApiModelProperty(value = "公司网址")
    private String website;

    @ApiModelProperty(value = "企业图片")
    private String img;

    @ApiModelProperty(value = "绑定邮箱")
    private String mailbox;

    @ApiModelProperty(value = "标签")
    private String labelid;
    
    
    @ApiModelProperty(value="展位效果图")
    private String zwimg;
    
    @ApiModelProperty(value="logo")
    private String logo;
    
    @ApiModelProperty(value="蜂鸟云地图Fid")
    private String fid;
    
    @ApiModelProperty(value="虚拟展厅html地址")
    private String halurl;
    
    private String telphone;
    
    @ApiModelProperty(value="展位效果图，图片url地址")
    private String floorplan;

    @ApiModelProperty(value="展商拼音名字首字母大写")
    private String firstletter;

    @ApiModelProperty(value = "认证时间")
    private LocalDateTime certificationtime;
}