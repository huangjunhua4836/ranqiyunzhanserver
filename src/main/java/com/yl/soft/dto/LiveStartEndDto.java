package com.yl.soft.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LiveStartEndDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String domain;
	
	private String app;
	
	private String stream;
	
	private String usr_args;
	
	private String client_ip;
	
	private String node_ip;
	
	private String publish_timestamp;
	
	private String event;

}
