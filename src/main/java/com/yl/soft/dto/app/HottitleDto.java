package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.yl.soft.po.EhbBanner;
import com.yl.soft.po.EhbHottitle;
import com.yl.soft.po.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 热搜词配置表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbHottitle对象", description="热搜词配置表")
public class HottitleDto implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "编码")
    private Integer id;
    @ApiModelProperty(value = "热词")
    private String hottitle;

    public static HottitleDto of(EhbHottitle po) {
        HottitleDto dto = new HottitleDto();
        BeanUtil.copyProperties(po,dto);
        return dto;
    }
}