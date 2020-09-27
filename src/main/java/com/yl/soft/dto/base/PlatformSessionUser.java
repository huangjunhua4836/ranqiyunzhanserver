package com.yl.soft.dto.base;

import com.yl.soft.po.CrmMenu;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 类说明：缓存用户信息类
 * @author jiangxl
 * @date 2020-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="后台登录返回session信息")
public class PlatformSessionUser implements Serializable {
    /** 用户ID */
    private int id;
    /** 姓名 */
    private String name;
    /** 登录名 */
    private String loginname;
    private String password;
    /** 角色ID */
    private Integer roleId;
    /** 角色名称 */
    private String roleName;
    /** 菜单 */
    private List<CrmMenu> crmMenus;
    /** 手机号 */
    private String phone;

    private Integer organization_id;
    private String org_name;
    private Integer member_id;
    private String member_name;
    private String position_id;
    private String position_name;
}