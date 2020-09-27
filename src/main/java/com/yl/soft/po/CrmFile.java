package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.yl.soft.po.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CrmFile对象", description="文件表")
public class CrmFile extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "文件类型（1营业执照，2企业授权书，3其他）")
    private Integer type;

    @ApiModelProperty(value = "存储地址")
    private String path;
}
