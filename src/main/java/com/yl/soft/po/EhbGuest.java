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
 * 推荐配置表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbGuest对象", description="推荐配置表")
public class EhbGuest extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "嘉宾姓名")
    private String name;

    @ApiModelProperty(value = "嘉宾职位")
    private String job;

    @ApiModelProperty(value = "嘉宾图片")
    private String picture;
}
