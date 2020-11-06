package com.yl.soft.common.live;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.sdk.live.v1.model.ShowTranscodingsTemplateRequest;
import com.sun.org.apache.bcel.internal.generic.NEW;

import lombok.Data;

@Data
public class CreateRecTplRequest {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value = "domain")
	private String domain;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value = "app")
	private String app_name;

	
	
	
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value = "default_record_config")
	private Map<String, Map<String, Object>> default_record_config;
	
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value = "plan_record_time")
	private Map<String, String> plan_record_time = new HashMap<>();
	
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value = "publish_domain")
	private String push_domain;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value = "record_type")
	private String record_type;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value = "stream")
	private String stream;
	


    public CreateRecTplRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

}