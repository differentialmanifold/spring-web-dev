package com.example.platform.module.common.extend.http.impl;

import com.example.platform.module.common.extend.http.HttpClient.DevHttpResponse;
import com.example.platform.module.common.extend.http.HttpUtilInterface;
import com.example.platform.module.common.extend.http.method.DevHttpMethod;

public class HttpUtilImpl implements HttpUtilInterface {

	@Override
	public DevHttpResponse action()throws Exception{
		// TODO Auto-generated method stub
		return this.http.call();
	}
	
	private DevHttpMethod http;
	public HttpUtilImpl(DevHttpMethod http){
		this.http = http;
	}
	public DevHttpMethod getHttp() {
		return http;
	}
	public void setHttp(DevHttpMethod http) {
		this.http = http;
	}
	
}
