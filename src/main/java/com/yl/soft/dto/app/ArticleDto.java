package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.yl.soft.po.EhbArticle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
public class ArticleDto implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "资讯ID")
    private Integer id;

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

    public static ArticleDto of(EhbArticle ehbArticle) {
        ArticleDto articleDto = new ArticleDto();
        BeanUtil.copyProperties(ehbArticle,articleDto);
        return articleDto;
    }
}