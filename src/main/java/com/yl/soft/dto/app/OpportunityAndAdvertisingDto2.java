package com.yl.soft.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商机-广告表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OpportunityAndAdvertisingDto对象", description="商机广告对象")
public class OpportunityAndAdvertisingDto2 implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "编号")
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime releasetime;

    @ApiModelProperty(value = "标签")
    private String label;

    private Integer exhibitorid;

    @ApiModelProperty(value = "1-商机  2-商品")
    private Integer type;

    @ApiModelProperty(value = "总收藏量")
    private Integer countcollection;

    @ApiModelProperty(value = "总点赞量")
    private Integer countthumbs;

    @ApiModelProperty(value = "总关注量")
    private Integer countfollow;

    @ApiModelProperty(value = "总浏览量")
    private Integer countbrowse;

    @ApiModelProperty(value = "总评论量")
    private Integer countcomment;

    @ApiModelProperty(value = "类型字典翻译")
    private String type_value;

    @ApiModelProperty(value = "企业名称")
    private String enterprisename;

    @ApiModelProperty(value = "广告编号")
    private Integer advertisingid;

    @ApiModelProperty(value = "广告标题")
    private String advertisingtitle;

    @ApiModelProperty(value = "广告图片")
    private String advertisingpicture;

    @ApiModelProperty(value = "广告url")
    private String advertisingurl;

    @ApiModelProperty(value = "广告排序")
    private Integer advertisingsort;

    @ApiModelProperty(value = "广告类型：1-站内   2-站外")
    private Integer advertisingtype;

    @ApiModelProperty(value = "广告 站内跳转位置：1-企业详情页面")
    private Integer advertisingposition;
}