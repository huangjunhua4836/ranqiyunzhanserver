package com.yl.soft.vo;

import com.yl.soft.po.EhbOpportunity;
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
 * 商机表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbOpportunity对象", description="商机/商品表")
public class OpportunityVo extends EhbOpportunity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "所属展商")
    private String exhiName;
}