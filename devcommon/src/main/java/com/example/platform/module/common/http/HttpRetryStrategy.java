package com.example.platform.module.common.http;
import com.example.platform.module.common.http.HttpClient.DevHttpResponse;


public interface HttpRetryStrategy {
	
	 DevHttpResponse retry(HttpUtilInterface unreliableImpl, int retryTimes, int sleepSeconds) throws Exception;

}
