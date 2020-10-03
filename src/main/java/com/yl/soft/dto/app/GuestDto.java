package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.yl.soft.po.EhbGuest;
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
public class GuestDto implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "编号")
    private Integer id;

    @ApiModelProperty(value = "嘉宾姓名")
    private String name;

    @ApiModelProperty(value = "嘉宾职位")
    private String job;

    @ApiModelProperty(value = "嘉宾图片")
    private String picture;

    public static GuestDto of(EhbGuest po) {
        GuestDto dto = new GuestDto();
        BeanUtil.copyProperties(po,dto);
        return dto;
    }
}
