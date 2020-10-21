package com.yl.soft.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FengNiaoVo对象", description="蜂鸟导入导出Excel对象")
public class FengNiaoVo implements Serializable {
    private static final long serialVersionUID=1L;
    private String fid;
    private String boothNoName;

    public FengNiaoVo(String fid, String boothNoName) {
        this.fid = fid;
        this.boothNoName = boothNoName;
    }
}
