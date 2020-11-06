package com.yl.soft.common.live;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.sdk.core.SdkResponse;
import com.huaweicloud.sdk.live.v1.model.AppQualityInfo;

import lombok.Data;

@Data
public class CreateRecTplResponse extends SdkResponse {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value = "error_code")
	private String error_code;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value = "error_msg")
	private String error_msg;

	@Override
	public String toString() {
		return "CreateRecTplResponse [error_code=" + error_code + ", error_msg=" + error_msg + "]";
	}
    

}