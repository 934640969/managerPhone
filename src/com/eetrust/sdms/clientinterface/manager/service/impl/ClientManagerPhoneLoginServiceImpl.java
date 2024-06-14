package com.eetrust.sdms.clientinterface.manager.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.eetrust.sdms.clientinterface.manager.service.IClientManagerPhoneLoginService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/***
 * 管理员的单点登录
 * 
 * @author yut
 *
 */

public class ClientManagerPhoneLoginServiceImpl implements IClientManagerPhoneLoginService {

	public String ticket(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		System.out.println("外置jar认证>>>>>>>>>>>>>>>>>>>>>");
		String phone = request.getParameter("phone");
		System.out.println("phone>>>>>>>"+phone);
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		// 循环6次，每次生成一个0-9之间的随机数
		for (int i = 0; i < 6; i++) {
			sb.append(random.nextInt(10));
		}
		// 生成的随机数
		String randomNumber = sb.toString();

		String url=ConstantV.smsUrl+"smsmsg/integratedmsg/sms/sendtempletmsg";
		Map map=new HashMap<String,String>();
		map.put("msg",randomNumber);
		map.put("mobiles",phone);
		map.put("templateId",ConstantV.templateId);
		System.out.println("map>>>>>"+map);
		HttpRequest post = HttpUtil.createPost(url);
		post.header("X-ACCESS-KEY",ConstantV.accessKey);
		post.form(map);
		String body = post.execute().body();
		System.out.println("body>>>>>>>>"+body);
		return randomNumber;
	}

	public String auth(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		return "";
	}
}
