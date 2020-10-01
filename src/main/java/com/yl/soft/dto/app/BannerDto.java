package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.yl.soft.po.EhbBanner;
import com.yl.soft.po.EhbLabel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * banner表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbBanner对象", description="banner表")
public class BannerDto implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "编号")
    private Integer id;

    @ApiModelProperty(value = "类型（1：首页顶部，2：首页大会预告）")
    private Integer type;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图片地址")
    private String imgurl;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endtime;

    @ApiModelProperty(value = "链接地址")
    private String linkurl;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    public static BannerDto of(EhbBanner po) {
        BannerDto dto = new BannerDto();
        BeanUtil.copyProperties(po,dto);
        return dto;
    }
}