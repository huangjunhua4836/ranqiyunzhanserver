package com.yl.soft.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

@Component
public class HWPlayFlowAuthUtil {

	@Value("${custom.hwyzb.live}")
	public String live;
	@Value("${custom.hwyzb.tlive}")
	private String tlive;
	@Value("${custom.hwyzb.appname}")
	private String appname;
	@Value("${custom.hwyzb.lkey}")
	private String lkey;
	@Value("${custom.hwyzb.tkey}")
	private String tkey;

	/**
	 * 生成随机数当作getItemID n ： 需要的长度
	 * 
	 * @return
	 */
	private static String getItemID(int n) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
			if ("char".equalsIgnoreCase(str)) { // 产生字母
				int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
				// System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
				val += (char) (nextInt + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(str)) { // 产生数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 播流地址生成
	 * 
	 * @param streamName 流名称
	 * @return
	 */
	public String liveUrl(String streamName) {

		// data="$"+<Timestamp>+"$"+<LiveID>+"$"+<CheckLevel>，具体请参见“鉴权方式C”
		String data = appname + "/" + streamName;

		// 随机生成的16位数字和字母组合
		byte[] ivBytes = getItemID(16).getBytes();

		// 在直播控制台配置的Key值
		byte[] key = lkey.getBytes();

		String msg = aesCbcEncrypt(data, ivBytes, key);
		try {
			System.out.println(URLEncoder.encode(msg, "UTF-8") + "." + bytesToHexString(ivBytes));
			return "http://" + live + "/" + appname + "/" + streamName + ".flv/auth_info="
					+ URLEncoder.encode(msg, "UTF-8") + "." + bytesToHexString(ivBytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String liveUrlRtmp(String streamName) {
		// data="$"+<Timestamp>+"$"+<LiveID>+"$"+<CheckLevel>，具体请参见“鉴权方式C”
		String data = appname + "/" + streamName;

		// 随机生成的16位数字和字母组合
		byte[] ivBytes = getItemID(16).getBytes();

		// 在直播控制台配置的Key值
		byte[] key = lkey.getBytes();

		String msg = aesCbcEncrypt(data, ivBytes, key);
		try {
			System.out.println(URLEncoder.encode(msg, "UTF-8") + "." + bytesToHexString(ivBytes));
			return "rtmp://" + live + "/" + appname + "/" + streamName + "/auth_info="
					+ URLEncoder.encode(msg, "UTF-8") + "." + bytesToHexString(ivBytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成推流地址
	 * 
	 * @param streamName 流名称
	 * @return
	 */
	public String tiveUrl(String streamName) {
		return "rtmp://" + tlive + "/" + appname + "/" + streamName;
	}

	public static void main(String[] args) {
		// data="$"+<Timestamp>+"$"+<LiveID>+"$"+<CheckLevel>，具体请参见“鉴权方式C”
		String data = "rqyz/A002";

		// 随机生成的16位数字和字母组合
		byte[] ivBytes = "yCmE666N3YAq30SN".getBytes();

		// 在直播控制台配置的Key值
		byte[] key = "3deYM0x8o8Fuq2ke".getBytes();

		String msg = aesCbcEncrypt(data, ivBytes, key);
		try {
			System.out.println(URLEncoder.encode(msg, "UTF-8") + "." + bytesToHexString(ivBytes));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private static String aesCbcEncrypt(String data, byte[] ivBytes, byte[] key) {
		try {
			SecretKeySpec sk = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			if (ivBytes != null) {
				cipher.init(Cipher.ENCRYPT_MODE, sk, new IvParameterSpec(ivBytes));
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, sk);
			}

			return Base64.encode(cipher.doFinal(data.getBytes("UTF-8")));
		} catch (Exception e) {
			return null;
		}
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if ((src == null) || (src.length <= 0)) {
			return null;
		}

		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
