package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.yl.soft.po.EhbAdvertising;
import com.yl.soft.po.EhbBanner;
import com.yl.soft.po.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 广告位表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbAdvertising对象", description="广告位表")
public class AdvertisingDto implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "类型：1-站内   2-站外")
    private Integer type;

    @ApiModelProperty(value = "站内跳转位置：1-企业详情页面")
    private Integer position;

    public static AdvertisingDto of(EhbAdvertising po) {
        AdvertisingDto dto = new AdvertisingDto();
        BeanUtil.copyProperties(po,dto);
        return dto;
    }
}