package com.yl.soft.vo;

/**
 * ztree的JSON展示
 */
public class TreeVo {
    private Integer id;
    private Integer pId;
    private String name;
    private boolean open;//层级是否展开
    private boolean isParent;
    private boolean checked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}