package com.example.platform.module.common.extend.http.method;

import org.apache.http.NameValuePair;
import com.example.platform.module.common.extend.http.HttpClient.DevHttpResponse;

import java.util.List;

/**
 */
public abstract class AbastractDevHttpMethod implements DevHttpMethod {

	@Override
	public abstract DevHttpResponse call()throws Exception;

	protected String url;
	protected String user;
	protected String pass;
	protected String parameter;//request body(application/json)
	protected List<NameValuePair> bodyParams;//request body(not json)
	protected String authorization;
	
	protected AbastractDevHttpMethod() {}

	protected AbastractDevHttpMethod(String url) {
		this(url , null , null);
	}
	
	protected AbastractDevHttpMethod(String url , String params) {
		this(url , null , null ,params);
	}
	
	protected AbastractDevHttpMethod(String url , List<NameValuePair> bodyParams) {
		this.url = url;
		this.bodyParams = bodyParams;
	}

	protected AbastractDevHttpMethod(String url, String user, String pass) {
		this(url , user , pass , null);
	}
	
	protected AbastractDevHttpMethod(String url, String user, String pass , String parameter) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.parameter = parameter;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public List<NameValuePair> getBodyParams() {
		return bodyParams;
	}

	public void setBodyParams(List<NameValuePair> bodyParams) {
		this.bodyParams = bodyParams;
	}
	

}