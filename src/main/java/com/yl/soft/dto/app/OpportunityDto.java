package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.yl.soft.po.EhbOpportunity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商机表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbOpportunity对象", description="商机/商品表")
public class OpportunityDto implements Serializable {

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

    public static OpportunityDto of(EhbOpportunity po) {
        OpportunityDto dto = new OpportunityDto();
        BeanUtil.copyProperties(po,dto);
        if(po.getType() == 1){
            dto.setType_value("商机");
        }else if(po.getType() == 2){
            dto.setType_value("商品");
        }else{
            dto.setType_value("未知");
        }
        return dto;
    }
}