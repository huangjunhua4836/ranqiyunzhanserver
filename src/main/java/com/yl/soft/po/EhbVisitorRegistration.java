package com.yl.soft.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import springfox.documentation.annotations.ApiIgnore;

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
@ApiModel(value="EhbVisitorRegistration对象", description="")
public class EhbVisitorRegistration implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;
    
    @ApiModelProperty(value = "联系人")
    private String name;


    @ApiModelProperty(value = "创建时间",hidden = true)
    private LocalDateTime createtime;

    @ApiModelProperty(value = "公司名称")
    private String compname;

    @ApiModelProperty(value="联系方式")
    private String phone;

    @ApiModelProperty(value = "预展位面积（两个值中间用英文逗号分隔）")
    private String showarea;

    @ApiModelProperty(value = "备注")
    private String remarks;
    
    @ApiModelProperty(value="登记人id",hidden = true)
    private Integer userid;
    

    @ApiModelProperty(value="职位")
    private String position;
    
    @ApiModelProperty(value="称谓（0=男，1=女）")
    private Integer appellation;
    
    
    


}
