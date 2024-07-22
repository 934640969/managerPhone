package com.eetrust.sdms.clientinterface.phone.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConstantV {

	public static String smsUrl = "";
	public static String account = "";
	public static String password = "";
	public static String tplInstId = "";
	public static String sendPhone = "";
	public static String id = "";
	public static String key = "";
	static{
		Properties p = new Properties();
		InputStream in;		
		in = ConstantV.class.getClassLoader().getResourceAsStream("/config/ssoLogin.properties");
		try {
			p.load(in);	
			// 获取本应用（业务应用自身）的资源编码（在信任服务资源系统中注册后取得）及身份认证服务地址
			smsUrl = p.getProperty("smsUrl");
			account = p.getProperty("account");
			password = p.getProperty("password");
			tplInstId = p.getProperty("tplInstId");
			sendPhone = p.getProperty("sendPhone");
			id = p.getProperty("X-APP-ID");
			key = p.getProperty("X-APP-KEY");
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
