package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 短信验证码记录表
 * </p>
 *
 * @author ${author}
 * @since 2020-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Smsrecord对象", description="短信验证码记录表")
public class Smsrecord implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "短信类型（0：注册，1：登录，2：绑定手机号，3：忘记密码，4：验证原手机号码，5：更换手机号码，404：未知类型）")
    private Integer type;

    @ApiModelProperty(value = "验证码")
    private String content;

    @ApiModelProperty(value = "发送时间")
    private LocalDateTime createtime;


}
