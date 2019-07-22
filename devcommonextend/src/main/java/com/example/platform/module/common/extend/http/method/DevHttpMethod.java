package com.example.platform.module.common.extend.http.method;
import com.example.platform.module.common.extend.http.HttpClient.DevHttpResponse;

public interface DevHttpMethod {
	
	DevHttpResponse call()throws Exception;
	
}
