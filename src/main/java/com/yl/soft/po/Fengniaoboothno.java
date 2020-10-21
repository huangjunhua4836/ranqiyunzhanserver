package com.yl.soft.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 消息记录表
 * </p>
 *
 * @author ${author}
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Fengniaoboothno对象", description="消息记录表")
public class Fengniaoboothno implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "蜂鸟id")
    @TableId(value = "fid", type = IdType.INPUT)
    private String fid;

    @ApiModelProperty(value = "展位名称")
    private String boothnoname;


}
