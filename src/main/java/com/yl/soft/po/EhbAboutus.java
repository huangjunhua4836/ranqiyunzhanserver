package com.yl.soft.po;

import java.sql.Blob;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbAboutus对象", description="")
public class EhbAboutus implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    @ApiModelProperty(value = "用户协议")
    private String useragr;

    @ApiModelProperty(value = "社区规范")
    private String communitynorms;

    @ApiModelProperty(value = "隐私条款")
    private String privacyclause;


}
