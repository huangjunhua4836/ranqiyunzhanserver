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
 * 资讯表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbArticle对象", description="资讯表")
public class EhbArticle extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "头图")
    private String headpicture;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime releasetime;

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

    @ApiModelProperty(value = "是否推荐  true-推荐  false-不推荐")
    private Boolean isrecommend;
}