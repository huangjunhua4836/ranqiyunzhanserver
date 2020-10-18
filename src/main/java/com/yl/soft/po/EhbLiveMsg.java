package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbLiveMsg对象", description="")
public class EhbLiveMsg implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String domain;

    private String app;

    private String stream;

    @TableField("usr_args")
    private String usrArgs;

    @TableField("client_ip")
    private String clientIp;

    @TableField("node_ip")
    private String nodeIp;

    @TableField("publish_timestamp")
    private String publishTimestamp;

    private String event;

    private Integer liveid;


}
