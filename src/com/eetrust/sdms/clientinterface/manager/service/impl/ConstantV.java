package com.eetrust.sdms.clientinterface.manager.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 

public class ConstantV {

	public static String smsUrl = "";
	public static String accessKey = "";
	public static String templateId = "";
	static{
		Properties p = new Properties();
		InputStream in;		
		in = ConstantV.class.getClassLoader().getResourceAsStream("/config/ssoLogin.properties");
		try {
			p.load(in);	
			// 获取本应用（业务应用自身）的资源编码（在信任服务资源系统中注册后取得）及身份认证服务地址
			smsUrl = p.getProperty("smsUrl");
			accessKey = p.getProperty("accessKey");
			templateId = p.getProperty("templateId");
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
