package com.example.platform.module.common.http.method;
import com.example.platform.module.common.http.HttpClient.DevHttpResponse;

public interface DevHttpMethod {
	
	DevHttpResponse call()throws Exception;
	
}
