package com.eetrust.sdms.clientinterface.phone.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IClientPHONELoginService {

	public String ticket(HttpServletRequest var1, HttpServletResponse var2) throws RuntimeException;
	
	public String auth(HttpServletRequest var1, HttpServletResponse var2) throws RuntimeException;
}
