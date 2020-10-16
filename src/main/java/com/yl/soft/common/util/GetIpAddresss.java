package com.yl.soft.common.util;

import com.yl.soft.common.unified.entity.BaseResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 获取ip地址
 * 
 * @author admin
 *
 */
public class GetIpAddresss {

	public static void main(String[] args) {
		System.out.println(GetIpAddresss.getIp()+"+++++++++++++=====");
	}
	/* public static String  getIp(){
		 ReusltItem res=new ReusltItem();
		 try {
			InetAddress address =InetAddress.getLocalHost();
*/
	/*
	 * public static void main(String[] args) {
	 * System.out.println(GetIpAddresss.getIp()+"+++++++++++++====="); }
	 */
	public static String getIp() {
		BaseResponse res = new BaseResponse();
		try {
			InetAddress address = InetAddress.getLocalHost();

			return address.getHostAddress();
		} catch (Exception e) {
			// TODO: handle exception
			res.setDesc(e.getMessage());
		}
		return res.toString();
	}

	/**
	 * 获取客户端Ip
	 * @param
	 * @return
	 */
	public static String getIpAddr() {
		String ipAddress = null;
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		// ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
