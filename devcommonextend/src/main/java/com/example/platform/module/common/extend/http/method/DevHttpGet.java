package com.example.platform.module.common.extend.http.method;


import com.example.platform.module.common.extend.http.HttpClient;
import com.example.platform.module.common.extend.http.HttpClient.DevHttpResponse;

public class DevHttpGet extends AbastractDevHttpMethod {

	@Override
	public DevHttpResponse call()throws Exception{
		// TODO Auto-generated method stub
		return HttpClient.httpGet(super.getUrl());
	}

	public DevHttpGet() {
		super();
	}

	public DevHttpGet(String url, String user, String pass, String parameter) {
		super(url, user, pass, parameter);
		// TODO Auto-generated constructor stub
	}

	public DevHttpGet(String url, String user, String pass) {
		super(url, user, pass);
		// TODO Auto-generated constructor stub
	}

	public DevHttpGet(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

}