package com.billybyte;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WsAccess{
	@WebMethod
	public String[] AccessServ(String[] input);
}
