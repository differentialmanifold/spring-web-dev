package com.example.platform.module.common.extend.http;
import com.example.platform.module.common.extend.http.HttpClient.DevHttpResponse;


public interface HttpRetryStrategy {
	
	 DevHttpResponse retry(HttpUtilInterface unreliableImpl, int retryTimes, int sleepSeconds) throws Exception;

}
