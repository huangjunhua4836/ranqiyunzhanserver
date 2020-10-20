package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * app版本信息
 * </p>
 *
 * @author ${author}
 * @since 2020-10-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Appversion对象", description="app版本信息")
public class Appversion implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "下载地址")
    private String url;

    @ApiModelProperty(value = "当前版本号")
    private String version;

    @ApiModelProperty(value = "上一版本号")
    private String oldversion;


}
