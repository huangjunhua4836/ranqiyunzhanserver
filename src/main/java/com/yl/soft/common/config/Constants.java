package com.yl.soft.common.config;

public interface Constants {
	static final String PHONE_REG = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
	static final String PASSWD_REG = "^[a-zA-Z][a-zA-Z0-9!@#$%^&*_+-=]{5,19}$";
	static final String PT_CONFIG_SMSTPL_WHO_KEY = "[WHO]";
	static final String PT_CONFIG_SMSTPL_NAME_KEY = "[NAME]";
	static final String PT_CONFIG_SMSTPL_AMOUNT_KEY = "[AMOUNT]";
	static final String PT_CONFIG_SMSTPL_TIME_KEY = "[TIME]";
	static final String PT_CONFIG_SMSTPL_SELF_PAY = "SMSTPL_SELF_PAY";
	static final String PT_CONFIG_SMSTPL_HELP_PAY = "SMSTPL_HELP_PAY";
	static final String PT_CONFIG_SMSTPL_LIVE_NOTIFY = "SMSTPL_LIVE_NOTIFY";
}
