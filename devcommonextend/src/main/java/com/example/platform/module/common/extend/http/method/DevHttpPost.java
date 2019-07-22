package com.example.platform.module.common.extend.http.method;

import com.example.platform.module.common.extend.http.HttpClient;
import org.apache.http.NameValuePair;

import java.util.List;
import com.example.platform.module.common.extend.http.HttpClient.DevHttpResponse;

public class DevHttpPost extends AbastractDevHttpMethod {

	@Override
	public DevHttpResponse call() throws Exception {
		// TODO Auto-generated method stub
	    if(super.getParameter() != null){
			return HttpClient.httpPost(super.getUrl() ,super.getParameter() );
		}else if(super.getBodyParams() != null){
			return HttpClient.httpPostBodyParam(super.getUrl(), super.getBodyParams());
		}else{
			return HttpClient.httpPost(super.getUrl());
		}
	}

	public DevHttpPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DevHttpPost(String url, String user, String pass, String parameter) {
		super(url, user, pass, parameter);
		// TODO Auto-generated constructor stub
	}

	public DevHttpPost(String url, String user, String pass) {
		super(url, user, pass);
		// TODO Auto-generated constructor stub
	}

	public DevHttpPost(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public DevHttpPost(String url, String params) {
		super(url, params);
		// TODO Auto-generated constructor stub
	}

	public DevHttpPost(String url, List<NameValuePair> bodyParams) {
		super(url, bodyParams);
		// TODO Auto-generated constructor stub
	}
	

}
