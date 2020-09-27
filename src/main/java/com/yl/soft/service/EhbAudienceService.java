package com.yl.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.soft.enums.LoginType;
import com.yl.soft.po.EhbAudience;

/**
 * <p>
 * 参展用户信息 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-23
 */
public interface EhbAudienceService extends IService<EhbAudience> {
	
	String getOpenId(LoginType loginType, String reqcode);

	String encryptPassword(String password);
}
