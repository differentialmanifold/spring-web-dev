package com.example.platform.module.common.http.method;


import com.example.platform.module.common.http.HttpClient;
import com.example.platform.module.common.http.HttpClient.DevHttpResponse;

public class DevHttpDelete extends AbastractDevHttpMethod {

	@Override
	public DevHttpResponse call() throws Exception {
		// TODO Auto-generated method stub
		return HttpClient.httpDelete(super.getUrl());
	}

	public DevHttpDelete() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DevHttpDelete(String url, String user, String pass, String parameter) {
		super(url, user, pass, parameter);
		// TODO Auto-generated constructor stub
	}

	public DevHttpDelete(String url, String user, String pass) {
		super(url, user, pass);
		// TODO Auto-generated constructor stub
	}

	public DevHttpDelete(String url, String params) {
		super(url, params);
		// TODO Auto-generated constructor stub
	}

	public DevHttpDelete(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	

}
