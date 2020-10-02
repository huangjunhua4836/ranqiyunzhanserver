package com.yl.soft.common.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//如果JDK版本是1.8,可使用原生Base64类
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

//如果JDK版本低于1.8,请使用三方库提供Base64类
//import org.apache.commons.codec.binary.Base64;

@Component
public class SendSms {
	/**
	 * 
	 * 设置不验证主机
	 * 
	 */

	private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

		@Override
		public boolean verify(String hostname, SSLSession session) {

			return true;

		}

	};
	
	@Value("${custom.yzm.url}")
	public String url;
	@Value("${custom.yzm.TemplateId0}")
	private String templateId0;
	@Value("${custom.yzm.TemplateId1}")
	private String templateId1;
	@Value("${custom.yzm.TemplateId2}")
	private String templateId2;
	@Value("${custom.yzm.TemplateId3}")
	private String templateId3;
	@Value("${custom.yzm.TemplateId4}")
	private String templateId4;
	@Value("${custom.yzm.TemplateId5}")
	private String templateId5;
	@Value("${custom.yzm.accout}")
	private String accout;
	@Value("${custom.yzm.passward}")
	private String passward;
	

	/**
	 * 0：注册，
	 * 1：登录，
	 * 2：绑定手机号，
	 * 3：忘记密码，
	 * 4：验证原手机号码，
	 * 5：更换手机号码
	 * @param phone 手机号
	 * @param code 验证码
	 * @param type 验证码类型
	 * @return
	 * @throws Exception
	 */
	public String sendMsgCode(String phone, String code, Integer type) throws Exception {
		// ip：port根据实际情况填写
		String msisdn = phone;
		Map<String, String> templateParas = new HashMap<String, String>();
		templateParas.put("code", code);
		String smsTemplateId="";
		switch (type) {
		case 0:  //注册
			smsTemplateId = templateId0;
			break;
		case 1:  //登录
			smsTemplateId = templateId1;
			break;
		case 2:  //绑定手机号
			smsTemplateId = templateId2;
			break;
		case 3:  //忘记密码
			smsTemplateId = templateId3;
			break;
		case 4:  //验证原手机密码
			smsTemplateId = templateId4;
			break;
		case 5:  //更换手机号
			smsTemplateId = templateId5;
			break;
		default:
			smsTemplateId = templateId0;
			break;
		}
		
		// If the request body does not contain the signature name, set signature to
		// null.
		Map<String, Object> body = buildRequestBody(msisdn, smsTemplateId, templateParas, accout, passward);
		if (null == body || body.isEmpty()) {
			System.out.println("body is null.");
			return "body is null.";
		}

		HttpsURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		trustAllHttpsCertificates();
		try {

			URL realUrl = new URL(url);
			connection = (HttpsURLConnection) realUrl.openConnection();
			connection.setHostnameVerifier(DO_NOT_VERIFY);
			connection.setDoInput(true); // 设置可输入
			connection.setDoOutput(true); // 设置该连接是可以输出的
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			// connection.connect();
			ObjectMapper objectMapper = new ObjectMapper();
			PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
			pw.write(objectMapper.writeValueAsString(body));
			pw.flush();
			pw.close();

			br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			int status = connection.getResponseCode();
			if (200 == status) { // 200
				is = connection.getInputStream();
			} else { // 400/401
				is = connection.getErrorStream();
			}
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = null;
			StringBuilder result = new StringBuilder();
			while ((line = br.readLine()) != null) { // 读取数据
				result.append(line + "\n");
			}
			connection.disconnect();
			System.out.println(result.toString());
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != is) {
					is.close();
				}
				if (null != br) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static void main(String[] args) throws Exception {
		// ip：port根据实际情况填写
		String url = "https://139.9.32.119:18312/common/sms/sendTemplateMessage";
		String msisdn = "17610766085";
		String smsTemplateId = "SMS_20092900003";
		Map<String, String> templateParas = new HashMap<String, String>();
		templateParas.put("code", "12344");
		String accout = "ITA1039"; // 实际账号
		String passward = "DS6*%23du!a"; // 实际密码

		// If the request body does not contain the signature name, set signature to
		// null.
		Map<String, Object> body = buildRequestBody(msisdn, smsTemplateId, templateParas, accout, passward);
		if (null == body || body.isEmpty()) {
			System.out.println("body is null.");
			return;
		}

		HttpsURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		trustAllHttpsCertificates();
		try {

			URL realUrl = new URL(url);
			connection = (HttpsURLConnection) realUrl.openConnection();
			connection.setHostnameVerifier(DO_NOT_VERIFY);
			connection.setDoInput(true); // 设置可输入
			connection.setDoOutput(true); // 设置该连接是可以输出的
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			// connection.connect();
			ObjectMapper objectMapper = new ObjectMapper();
			PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
			pw.write(objectMapper.writeValueAsString(body));
			pw.flush();
			pw.close();

			br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			int status = connection.getResponseCode();
			if (200 == status) { // 200
				is = connection.getInputStream();
			} else { // 400/401
				is = connection.getErrorStream();
			}
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = null;
			StringBuilder result = new StringBuilder();
			while ((line = br.readLine()) != null) { // 读取数据
				result.append(line + "\n");
			}
			connection.disconnect();
			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != is) {
					is.close();
				}
				if (null != br) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// msisdn, smsTemplateId, paramValues, countryID
	public static Map<String, Object> buildRequestBody(String msisdn, String smsTemplateId,
			Map<String, String> paramValues, String accout, String passward) {
		if (null == msisdn || null == smsTemplateId || null == accout || null == passward) {
			System.out.println(
					"buildRequestBody(): mobiles, templateId or templateParas or account or password is null.");
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		List<MtSmsMessage> requestLists = new ArrayList<MtSmsMessage>();
		MtSmsMessage mtSmsMessage = new MtSmsMessage();
		List<String> mobiles = new ArrayList<String>();
		mobiles.add(msisdn);
		mtSmsMessage.setMobiles(mobiles);
		mtSmsMessage.setTemplateId(smsTemplateId);
		mtSmsMessage.setTemplateParas(paramValues);
		mtSmsMessage.setSignature("【燃气云展】");
		requestLists.add(mtSmsMessage);
		map.put("account", accout);
		map.put("password", passward);
		map.put("requestLists", requestLists);
		System.out.println(JSONObject.toJSON(map));
		return map;
	}

	public static class MtSmsMessage {
		List<String> mobiles;
		String templateId;
		Map<String, String> templateParas;

		String signature;

		String messageId;

		String extCode;

		List<NamedPatameter> extendInfos;

		/**
		 * 返回mobiles
		 * 
		 * @return mobiles值
		 */
		public List<String> getMobiles() {
			return mobiles;
		}

		/**
		 * 对mobiles进行赋值
		 * 
		 * @param mobiles mobiles值
		 */
		public void setMobiles(List<String> mobiles) {
			this.mobiles = mobiles;
		}

		/**
		 * 返回 templateId
		 * 
		 * @return templateId值
		 */
		public String getTemplateId() {
			return templateId;
		}

		/**
		 * 对templateId进行赋值
		 * 
		 * @param templateId templateId值
		 */
		public void setTemplateId(String templateId) {
			this.templateId = templateId;
		}

		/**
		 * 返回 templateParas
		 * 
		 * @return templateParas值
		 */
		public Map<String, String> getTemplateParas() {
			return templateParas;
		}

		/**
		 * 对templateParas进行赋值
		 * 
		 * @param templateParas templateParas值
		 */
		public void setTemplateParas(Map<String, String> templateParas) {
			this.templateParas = templateParas;
		}

		/**
		 * 返回 signature
		 * 
		 * @return signature值
		 */
		public String getSignature() {
			return signature;
		}

		/**
		 * 对signature进行赋值
		 * 
		 * @param signature signature值
		 */
		public void setSignature(String signature) {
			this.signature = signature;
		}

		/**
		 * 返回 messageId
		 * 
		 * @return messageId值
		 */
		public String getMessageId() {
			return messageId;
		}

		/**
		 * 对messageId进行赋值
		 * 
		 * @param messageId messageId值
		 */
		public void setMessageId(String messageId) {
			this.messageId = messageId;
		}

		/**
		 * 返回 extCode
		 * 
		 * @return extCode值
		 */
		public String getExtCode() {
			return extCode;
		}

		/**
		 * 对extCode进行赋值
		 * 
		 * @param extCode extCode值
		 */
		public void setExtCode(String extCode) {
			this.extCode = extCode;
		}

		/**
		 * 返回 extendInfos
		 * 
		 * @return extendInfos值
		 */
		public List<NamedPatameter> getExtendInfos() {
			return extendInfos;
		}

		/**
		 * 对extendInfos进行赋值
		 * 
		 * @param extendInfos extendInfos值
		 */
		public void setExtendInfos(List<NamedPatameter> extendInfos) {
			this.extendInfos = extendInfos;
		}

	}

	public class NamedPatameter {
		String key;

		String value;

		/**
		 * 返回 key
		 * 
		 * @return key值
		 */
		public String getKey() {
			return key;
		}

		/**
		 * 对key进行赋值
		 * 
		 * @param key key值
		 */
		public void setKey(String key) {
			this.key = key;
		}

		/**
		 * 返回 value
		 * 
		 * @return value值
		 */
		public String getValue() {
			return value;
		}

		/**
		 * 对value进行赋值
		 * 
		 * @param value value值
		 */
		public void setValue(String value) {
			this.value = value;
		}

	}

	static void trustAllHttpsCertificates() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return;
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		} };
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}
}