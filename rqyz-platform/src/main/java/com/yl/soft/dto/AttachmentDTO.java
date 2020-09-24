package com.yl.soft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="文件返回信息")
public class AttachmentDTO {
    @ApiModelProperty(value = "文件id",required = true)
    private Integer id;
    @ApiModelProperty(value = "文件名称",required = true)
    private String name;
    @ApiModelProperty(value = "文件预览地址",required = true)
    private String url;
}