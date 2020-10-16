package com.yl.soft.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 直播录制回调
 * 
 * @author xingdi
 *
 */
@Data
public class LiveDto implements Serializable{

	private String project_id;

	private String publish_domain;

	private String event_type;

	private String app;

	private String stream;

	private String record_format;
	private String download_url;

	private String asset_id;

	private String play_url;

	private Integer file_size;

	private Integer record_duration;

	private String start_time;

	private String end_time;

	private Integer width;

	private Integer height;
	private String obs_location;
	private String obs_bucket;
	private String obs_object;

}
