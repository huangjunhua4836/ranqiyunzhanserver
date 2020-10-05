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
 * 虚拟展厅信息表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbHall对象", description="虚拟展厅信息表")
public class EhbHall extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "展商ID")
    private Integer exhibitorid;

    @ApiModelProperty(value = "展商名称")
    private String exhibitorname;

    @ApiModelProperty(value = "展位号")
    private String boothno;

    @ApiModelProperty(value="vrHTML地址")
    private String hallurl;

    @ApiModelProperty(value="浏览量")
    private Integer views;
    @ApiModelProperty(value="是否推荐顶部位置0：否，1：是")
    private Integer recommend;
    @ApiModelProperty(value="排序，值越大越靠前")
    private Integer sort;
    
    
}