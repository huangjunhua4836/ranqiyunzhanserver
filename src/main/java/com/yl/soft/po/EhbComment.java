package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author ${author}
 * @since 2020-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EhbComment对象", description="评论表")
public class EhbComment implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer createuser;

    private LocalDateTime createtime;

    private Integer updateuser;

    private LocalDateTime updatetime;

    @ApiModelProperty(value = "0：不可用 1：可用")
    private Integer state;

    private Integer version;

    private Integer isdel;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "评论人ID")
    private Integer userid;

    @ApiModelProperty(value = "上级评论")
    private Integer pid;

    @ApiModelProperty(value = "1：企业   2：商机  3：资讯")
    private Integer type;

    private Integer relateid;

    private String content;

    private String picture;


}
