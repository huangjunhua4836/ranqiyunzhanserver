package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.yl.soft.po.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户行为记录表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbUseraction对象", description="用户行为记录表")
public class EhbUseraction extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "收藏人")
    private Integer userid;

    @ApiModelProperty(value = "1：企业   2：商机  3：资讯")
    private Integer type;

    @ApiModelProperty(value = "关联ID")
    private Integer relateid;

    @ApiModelProperty(value = "1：收藏    2：点赞    3：关注    4：浏览")
    private Integer activetype;
}