package com.yl.soft.common.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

	@Value("${custom.yj.from}")
	public String from;// 邮件发送人的邮件地址
	@Value("${custom.yj.username}")
	private String username;
	@Value("${custom.yj.password}")
	private String password;

	@Value("${custom.yj.host}")
	private String host;

	@Value("${custom.yj.protocol}")
	private String protocol;
	
	@Value("${custom.yj.ssl}")
	private String ssl;
	@Value("${custom.yj.dk}")
	private String dk;
	
	public boolean sendMail(String email, String emailMsg) {
		String to = email; // 邮件接收人的邮件地址

		// 定义Properties对象,设置环境信息
		Properties props = new Properties();

		// 设置邮件服务器的地址
		props.setProperty("mail.smtp.host", host); // 指定的smtp服务器
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", protocol);// 设置发送邮件使用的协议
		if(ssl.equals("1")){
			props.setProperty("mail.smtp.ssl.enable", "true");
		}
		// 创建Session对象,session对象表示整个邮件的环境信息
		Session session = Session.getInstance(props);
		// 设置输出调试信息
		session.setDebug(true);
		try {
			// Message的实例对象表示一封电子邮件
			MimeMessage message = new MimeMessage(session);
			// 设置发件人的地址
			message.setFrom(new InternetAddress(from));
			// 设置主题
			message.setSubject("【燃气云展】邮箱验证");
			// 设置邮件的文本内容
			// message.setText("Welcome to JavaMail World!");
			message.setContent("【燃气云展】验证码："+emailMsg+" 用于绑定邮箱5分钟内失效，该邮件系统自动发送请勿回复", "text/html;charset=utf-8");
			// 从session的环境中获取发送邮件的对象
			Transport transport = session.getTransport();
			// 连接邮件服务器
			transport.connect(host, Integer.parseInt(dk), username, password);
			// 设置收件人地址,并发送消息
			transport.sendMessage(message, new Address[] { new InternetAddress(to) });
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

}
