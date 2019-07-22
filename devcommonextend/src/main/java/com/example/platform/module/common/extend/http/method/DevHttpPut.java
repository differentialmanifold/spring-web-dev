package com.example.platform.module.common.extend.http.method;


import com.example.platform.module.common.extend.http.HttpClient;
import com.example.platform.module.common.extend.http.HttpClient.DevHttpResponse;

public class DevHttpPut extends AbastractDevHttpMethod {

	@Override
	public DevHttpResponse call() throws Exception {
		// TODO Auto-generated method stub
		if(super.getParameter() == null){
			return HttpClient.httpPut(super.getUrl()  );
		}else{
			return HttpClient.httpPut(super.getUrl() ,super.getParameter() );
		}
		
	}

	public DevHttpPut() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DevHttpPut(String url, String user, String pass, String parameter) {
		super(url, user, pass, parameter);
		// TODO Auto-generated constructor stub
	}

	public DevHttpPut(String url, String user, String pass) {
		super(url, user, pass);
		// TODO Auto-generated constructor stub
	}

	public DevHttpPut(String url, String params) {
		super(url, params);
		// TODO Auto-generated constructor stub
	}

	public DevHttpPut(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	

}
