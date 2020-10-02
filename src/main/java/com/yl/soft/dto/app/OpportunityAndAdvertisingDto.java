package com.yl.soft.dto.app;

import cn.hutool.core.bean.BeanUtil;
import com.yl.soft.common.unified.entity.BasePage;
import com.yl.soft.po.EhbOpportunity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商机-广告表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OpportunityAndAdvertisingDto对象", description="商机广告对象")
public class OpportunityAndAdvertisingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private BasePage<OpportunityDto> opportunityDtoBasePage;

    private AdvertisingDto advertisingDto;
}