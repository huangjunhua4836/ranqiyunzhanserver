package com.yl.soft.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sun.mail.util.MailSSLSocketFactory;

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

	/**
     * 发送带附件的邮件
     * 
     * @param receive
     *            收件人
     * @param subject
     *            邮件主题
     * @param msg
     *            邮件内容
     * @param filename
     *            附件地址
     * @return
     * @throws GeneralSecurityException
     */
    public boolean sendMail(String receive, String subject, String msg, URL filename,String name)
            throws GeneralSecurityException {
        if (StringUtils.isEmpty(receive)) {
            return false;
        }

        // 发件人电子邮箱
        // 发件人电子邮箱密码
        String pass = password;


        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() { // qq邮箱服务器账户、第三方登录授权码
                return new PasswordAuthentication(from, pass); // 发件人邮件用户名、密码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receive));

            // Set Subject: 主题文字
            message.setSubject(subject);

            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();

            // 消息
            messageBodyPart.setText(msg);

            // 创建多重消息
            Multipart multipart = new MimeMultipart();

            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            // 附件部分
            messageBodyPart = new MimeBodyPart();
            // 设置要发送附件的文件路径
            URLDataSource source = new URLDataSource(filename);
            DataHandler dataHandler=new DataHandler(source);
            messageBodyPart.setDataHandler(dataHandler);

            // messageBodyPart.setFileName(filename);
            // 处理附件名称中文（附带文件路径）乱码问题
            messageBodyPart.setFileName(MimeUtility.encodeText(name).replaceAll("\r", "").replaceAll("\n", ""));
            multipart.addBodyPart(messageBodyPart);

            // 发送完整消息
            message.setContent(multipart);

            // 发送消息
            Transport.send(message);
            // System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	public boolean sendMail(String email, String emailMsg) {
		String to = email; // 邮件接收人的邮件地址

		// 定义Properties对象,设置环境信息
		Properties props = new Properties();

		// 设置邮件服务器的地址
		props.setProperty("mail.smtp.host", host); // 指定的smtp服务器
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", protocol);// 设置发送邮件使用的协议
		if (ssl.equals("1")) {
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
			message.setContent("【燃气云展】验证码：" + emailMsg + " 用于绑定邮箱5分钟内失效。", "text/html;charset=utf-8");
//			String str="<head> <base target=\"_blank\" /> <style type=\"text/css\">::-webkit-scrollbar{ display: none; }</style> <style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}</style> <style id=\"blockquoteStyle\" type=\"text/css\">blockquote{display:none;}</style> <style type=\"text/css\"> body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px} td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana} pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%} th,td{font-family:arial,verdana,sans-serif;line-height:1.666} img{ border:0} header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block} blockquote{margin-right:0px} </style> </head> <body tabindex=\"0\" role=\"listitem\"> <table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\"> <tbody> <tr> <td> <div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\"> <tbody><tr><td width=\"210\"></td></tr></tbody> </table> </div> <div style=\"width:680px;padding:0 10px;margin:0 auto;\"> <div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\"> <strong style=\"display:block;margin-bottom:15px;\">尊敬的用户：<span style=\"color:#f60;font-size: 16px;\"></span>您好！</strong> <strong style=\"display:block;margin-bottom:15px;\"> 您正在进行<span style=\"color: red\">绑定邮箱</span>操作，请在验证码输入框中输入：<span style=\"color:#f60;font-size: 24px\">"+emailMsg+"</span>，以完成操作。 </strong> </div> <div style=\"margin-bottom:30px;\"> <small style=\"display:block;margin-bottom:20px;font-size:12px;\"> <p style=\"color:#747474;\"> 注意：如非本人操作，请及时登录并修改密码以保证帐户安全 <br>（工作人员不会向你索取此验证码，请勿泄漏！) </p> </small> </div> </div> <div style=\"width:700px;margin:0 auto;\"> <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\"> <p>此为系统邮件，请勿回复<br> 请保管好您的邮箱，避免账号被他人盗用 </p> <p>燃气云展</p> </div> </div> </td> </tr> </tbody> </table> </body>";
//			message.setContent(str, "text/html;charset=utf-8");
			// 从session的环境中获取发送邮件的对象
			Transport transport = session.getTransport();
			// 连接邮件服务器
			transport.connect(host, Integer.parseInt(dk), username, password);
			@SuppressWarnings("static-access")
			InternetAddress[] internetAddressCC = new InternetAddress().parse(username);
			message.setRecipients(Message.RecipientType.CC, internetAddressCC);
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
