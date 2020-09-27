package com.yl.soft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yl.soft.enums.LoginType;
import com.yl.soft.mapper.EhbAudienceMapper;
import com.yl.soft.po.EhbAudience;
import com.yl.soft.service.EhbAudienceService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参展用户信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-09-23
 */
@Service
public class EhbAudienceServiceImpl extends ServiceImpl<EhbAudienceMapper, EhbAudience> implements EhbAudienceService {

	// QQ
	private static String QQ_OAUTH2_URL = "https://graph.qq.com/oauth2.0/me?access_token={CODE}&fmt=json";
	// 微信-小程序JSSDK
	private static String WX_JSCODE_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={CODE}&grant_type=authorization_code";
	// 微信-网站应用接入
	// private static String WX_OAUTH2_URL =
	// "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code";

	@Value("${custom.wechat.app_id}")
	private String wxAppId;
	@Value("${custom.wechat.app_security}")
	private String wxAppSecret;
	@Value("${custom.authentication.enable_check_reqcode}")
	private Boolean enableCheckReqcode;
	@Value("${custom.authentication.pwd_salt}")
	private String pwdSalt;

	@Override
	public String getOpenId(LoginType loginType, String code) {
		if (!enableCheckReqcode) {
			return code;
		}
		String apiUrl = null;
		switch (loginType) {
		case 微信:
			apiUrl = WX_JSCODE_URL.replace("{APPID}", wxAppId).replace("{SECRET}", wxAppSecret).replace("{CODE}", code);
			break;
		case QQ:
			apiUrl = QQ_OAUTH2_URL.replace("{CODE}", code);
			break;
		default:
			return null;
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		StringBuilder respBody = new StringBuilder();
		HttpGet httpGet = new HttpGet(apiUrl);
		try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
			HttpEntity httpEntity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new IOException("[Response] statusCode != 200");
			} else if (httpEntity == null) {
				throw new IOException("[Response] httpEntity == null");
			}
			Charset charset = ContentType.getOrDefault(httpEntity).getCharset();
			try (Reader reader = new InputStreamReader(httpEntity.getContent(),
					charset != null ? charset : StandardCharsets.UTF_8)) {
				char[] buffer = new char[512 * 1024];
				int length = reader.read(buffer);
				while (length != -1) {
					respBody.append(buffer, 0, length);
					length = reader.read(buffer);
				}
			}
		} catch (IOException e) {
			log.error("API error:", e);
			return null;
		}
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> respMap = new ObjectMapper().readValue(respBody.toString(), HashMap.class);
			return (String) respMap.getOrDefault("openid", "");
		} catch (JsonProcessingException e) {
			log.error("PARSE-JSON error:", e);
		}
		return null;
	}

	@Override
	public String encryptPassword(String password) {
		int len = password.length() + pwdSalt.length();
		StringBuilder stringBuilder = new StringBuilder(len);
		for (int i = 0, pi = password.length(), si = pwdSalt.length(); i < len; i += 2) {
			if (--pi > -1) {
				stringBuilder.append(password.charAt(pi));
			}
			if (--si > -1) {
				stringBuilder.append(pwdSalt.charAt(si));
			}
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(stringBuilder.toString().getBytes());
			byte[] digest = md.digest();
			stringBuilder = new StringBuilder();
			for (byte b : digest) {
				String s = Integer.toHexString(b & 0xFF);
				if (s.length() == 1)
					stringBuilder.append("0");
				stringBuilder.append(s);
			}
			return stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	
}
