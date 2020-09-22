package com.yl.soft.dto.base;

import com.yl.soft.po.CrmMenu;

import java.io.Serializable;
import java.util.List;

/**
 * 类说明：缓存用户信息类
 * @author jiangxl
 * @date 2020-06-25
 */
public class SessionUser implements Serializable {
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
    /** 状态码 */
    private int code;

    private Integer organization_id;
    private String org_name;
    private Integer member_id;
    private String member_name;
    private String position_id;
    private String position_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<CrmMenu> getCrmMenus() {
        return crmMenus;
    }

    public void setCrmMenus(List<CrmMenu> crmMenus) {
        this.crmMenus = crmMenus;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}