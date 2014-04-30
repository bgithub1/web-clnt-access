package com.billybyte;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.billybyte.commonstaticmethods.Utils;



public class WsAccessClient {
		private final Service service;
		private final String publicIpAddressOfWebService;// like = "http://localhost";
		private final String portNumberOfWebService;// like = "8080";
		private final String implProjectName  = "WebSrvcAccess";// project name of Web Service
		private final String implClassName = "WsAccess"; // implementation class name without the ".java"
		private final String implPackageName = "com.billybyte"; // package location of implClass
		private final String wsdl ;
	    private final URL url;
	    private final WsAccess wsAccess;
	    private final QName name;
	    
	    
	    public WsAccessClient(
	    		String publicIpAddressOfWebService,
	    		String portNumberOfWebService
	    		){
	    	this.publicIpAddressOfWebService = publicIpAddressOfWebService;
	    	this.portNumberOfWebService = portNumberOfWebService;
	    	String serviceName = implClassName+"Service";
	    	wsdl = "/"+implProjectName+"/"+serviceName+"?wsdl";
			try {
				url = new URL(publicIpAddressOfWebService+":"+portNumberOfWebService+wsdl);
			} catch (MalformedURLException e) {
				throw new IllegalStateException(e);
			}
			String[] nameArg0Parts = implPackageName.split("\\.");
			String arg0 = "http://";
			for(int i = nameArg0Parts.length-1;i>=0;i--){
				arg0 = arg0+nameArg0Parts[i]+".";
			}
			// strip of last "."
			arg0 = arg0.substring(0,arg0.length()-1);
			arg0 = arg0+"/";
			String arg1 = serviceName;
			name = new QName(arg0,arg1);
		    service = Service.create(url, name);
		    wsAccess = service.getPort(WsAccess.class);
	    }
	    
	    
	    public String[] callService(String[] input){
	    	String[] output = wsAccess.AccessServ(input);
	    	return output;
	    }
	    
	    public String getPublicIpAddressOfWebService() {
			return publicIpAddressOfWebService;
		}


		public String getPortNumberOfWebService() {
			return portNumberOfWebService;
		}


		public static void main(String[] args) {
			WsAccessClient client = new WsAccessClient("http://127.0.0.1", "8080");
			String[] results = client.callService(new String[]{
					"9000",
					"HO.FUT.NYMEX.USD.201302",
					"HO.FUT.NYMEX.USD.201303",
					"HO.FUT.NYMEX.USD.201304"});
			for(String result:results){
				Utils.prtObMess(WsAccessClient.class, result);
			}
		}
	
/**
 * 	
 		Service service;
		String publicIpAddressOfWebService = "http://localhost";
		String portNumberOfWebService = "8080";
		String wsdl = "/deploytomcattest/TctestImplService?wsdl";
	    URL url=null;
		try {
			url = new URL(publicIpAddressOfWebService+":"+portNumberOfWebService+wsdl);
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		}
	      // Specify the qualified name
	      QName name = new QName("http://testtomcat.billybyte.com/", "TctestImplService");
	      // Create the service
	    service = Service.create(url, name);
	    TctestImpl tcTest = service.getPort(TctestImpl.class);
	    String[] sArray = tcTest.getStrings(new String[]{"NG.FUT.NYMEX.USD.201201"});
	    for(String s:sArray){
	    	System.out.println(s);
	    }

 */
}
