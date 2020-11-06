package com.yl.soft.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
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
//			return "http://" + live + "/" + appname + "/" + streamName + ".flv/auth_info="
//					+ URLEncoder.encode(msg, "UTF-8") + "." + bytesToHexString(ivBytes);
			return "http://" + live + "/" + appname + "/" + streamName + ".flv";
//			String str="mR9DFCwaOI0syBoz" + "C001" + HWPlayFlowAuthUtil.to16Hex(HWPlayFlowAuthUtil.addOneDay());
//			return "http://rqyz.tlive.duoka361.com/rqyz/"+streamName+".flv?txSecret="+str+"&txTime="+HWPlayFlowAuthUtil.to16Hex(HWPlayFlowAuthUtil.addOneDay());
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
//		String str="3deYM0x8o8Fuq2ke" + streamName + HWPlayFlowAuthUtil.to16Hex(HWPlayFlowAuthUtil.addOneDay());
//		return "rtmp://rqyz.tlive.duoka361.com/rqyz/"+streamName+"?txSecret="+DigestUtils.md5DigestAsHex(str.getBytes())+"&txTime="+HWPlayFlowAuthUtil.to16Hex(HWPlayFlowAuthUtil.addOneDay());
		return "rtmp://" + live + "/" + appname + "/" + streamName;
	}

	public static void main(String[] args) {
		String str="3deYM0x8o8Fuq2ke" + "C001" + HWPlayFlowAuthUtil.to16Hex(HWPlayFlowAuthUtil.addOneDay());
		String s=HWPlayFlowAuthUtil.to16Hex(HWPlayFlowAuthUtil.addOneDay());
		System.out.println("rtmp://rqyz.tlive.duoka361.com/rqyz/"+"C001"+"?txSecret="+DigestUtils.md5DigestAsHex(str.getBytes())+"&txTime="+s);
		String str1="mR9DFCwaOI0syBoz" + "C001" + s;
		System.out.println("http://rqyz.live.duoka361.com/rqyz/C001.flv?txSecret="+DigestUtils.md5DigestAsHex(str1.getBytes())+"&txTime="+s);
	}
	
	/**
	 * 生成推拉流鉴权地址（鉴权B）
	 * @param streamName
	 * @return
	 */
	public Map<String,String> tiveOrliveAdd(String streamName){
		Map<String, String> map=new HashedMap<>();
		String str="3deYM0x8o8Fuq2ke" + "C001" + HWPlayFlowAuthUtil.to16Hex(HWPlayFlowAuthUtil.addOneDay());
		String s=HWPlayFlowAuthUtil.to16Hex(HWPlayFlowAuthUtil.addOneDay());
		map.put("tlive", "rtmp://rqyz.tlive.duoka361.com/rqyz/"+streamName+"?txSecret="+DigestUtils.md5DigestAsHex(str.getBytes())+"&txTime="+s);
		String str1="mR9DFCwaOI0syBoz" + "C001" + s;
		map.put("live", "http://rqyz.live.duoka361.com/rqyz/"+streamName+".flv?txSecret="+DigestUtils.md5DigestAsHex(str1.getBytes())+"&txTime="+s);
		return map;
	}
	
	
	
    public static String to16Hex(Date date) {
        Long ab = date.getTime()/1000;
        String a = Long.toHexString(ab);
        System.out.println(ab);
        return a;
    }
    
    public static Date addOneDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date date = cal.getTime();
        System.out.println(DateUtils.DateToString(date, "yyyy-MM-dd HH:mm:ss"));
        return date;
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
