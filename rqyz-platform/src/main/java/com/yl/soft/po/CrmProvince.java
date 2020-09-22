package com.yl.soft.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 省份表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CrmProvince对象", description="省份表")
public class CrmProvince implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "编码")
    @TableId(value = "code")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;
}
