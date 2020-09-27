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
 * 标签信息表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbLabel对象", description="标签信息表")
public class EhbLabel extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "标签名称")
    private String name;
}