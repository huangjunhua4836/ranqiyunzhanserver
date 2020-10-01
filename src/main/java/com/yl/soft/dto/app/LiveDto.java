package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.yl.soft.po.EhbBanner;
import com.yl.soft.po.EhbLive;
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
 * 直播信息表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbLive对象", description="直播信息表")
public class LiveDto implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "编号")
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "副标题")
    private String subtitle;

    @ApiModelProperty(value = "直播标签")
    private String levellabel;

    @ApiModelProperty(value = "开播时间")
    private LocalDateTime leveltime;

    public static LiveDto of(EhbLive po) {
        LiveDto dto = new LiveDto();
        BeanUtil.copyProperties(po,dto);
        return dto;
    }
}