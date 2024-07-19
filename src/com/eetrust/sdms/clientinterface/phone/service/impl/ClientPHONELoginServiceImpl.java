package com.eetrust.sdms.clientinterface.phone.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.eetrust.sdms.clientinterface.phone.service.IClientPHONELoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Random;
import org.apache.log4j.Logger;
/***
 * 管理员的单点登录
 * 
 * @author yut
 *
 */

public class ClientPHONELoginServiceImpl implements IClientPHONELoginService {
	private static final Logger log = Logger.getLogger(ClientPHONELoginServiceImpl.class);

	public String ticket(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		log.info("外置jar认证>>>>>>>>>>>>>>>>>>>>>");
		String phone = request.getParameter("loginName");
		log.info("phone>>>>>>>"+phone);
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		// 循环6次，每次生成一个0-9之间的随机数
		for (int i = 0; i < 6; i++) {
			sb.append(random.nextInt(10));
		}
		// 生成的随机数
		String randomNumber = sb.toString();
		String msg="【新疆电信商密安全系统】您的验证码："+randomNumber+"（有效期为3分钟），发送参考时间为"+ DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") +"。如非本人操作，请忽略本短信!";
		log.info("msg>>>>>>>>>>>>>>"+msg);
		String content= Base64.encode(msg, "UTF-8");
		String xml="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cxf=\"http://cxf.server.uccp.ztesoft.com/\"><soapenv:Header/>" +
				"<soapenv:Body>" +
				"<cxf:send>" +
				"<itData>" +
				"<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<request>" +
				"<account>"+ ConstantV.account+"</account>" +
				"<password>"+ConstantV.password+"</password>" +
				"<tplInstId>"+ConstantV.tplInstId+"</tplInstId>" +
				"<interfaceType>001</interfaceType>" +
				"<businessType>001</businessType>" +
				"<needReport>1</needReport>" +
				"<msgFormat>15</msgFormat>" +
				"<srcTermId>"+ConstantV.sendPhone+"</srcTermId>" +
				"<destTermId>"+phone+"</destTermId>" +
				"<msgContent>"+content+"</msgContent>" +
				"</request>]]>" +
				"</itData>" +
				"</cxf:send>" +
				"</soapenv:Body>" +
				"</soapenv:Envelope>";
		log.info("xml>>>>>>>>>>>>>>>>>>>>"+xml);
		String post = HttpUtil.post(ConstantV.smsUrl, xml);
		log.info("post>>>>>>>>>>>>>>>>>>>>"+post);
		return randomNumber;
	}

	public String auth(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
		return "";
	}
}
