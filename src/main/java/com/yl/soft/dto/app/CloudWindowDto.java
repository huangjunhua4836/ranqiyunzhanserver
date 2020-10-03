package com.yl.soft.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 云端对象DTO
 * </p>
 *
 * @author ${author}
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="云端对象DTO", description="云端对象DTO")
public class CloudWindowDto implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "首字母")
    private String word;

    @ApiModelProperty(value = "展商集合")
    private List<ExhibitorDto> exhibitorDtos;
}