package com.example.platform.module.common.http.impl;


import com.example.platform.module.common.http.HttpRetryStrategy;
import com.example.platform.module.common.http.HttpUtilInterface;
import com.example.platform.module.common.http.HttpClient.DevHttpResponse;

/**
 */
public class HttpRetryStrategyRoundRobin implements HttpRetryStrategy {

	@Override
	public DevHttpResponse retry(HttpUtilInterface unreliableImpl, int retryTimes, int sleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		for (int currentTime = 0; currentTime < retryTimes; currentTime++) {
			try {
				return unreliableImpl.action();
			} catch (Exception e) {
				try {
					Thread.sleep(sleepSeconds * 1000);
				} catch (InterruptedException e1) {
				}
			}
		}

		throw new Exception("http request retry all counts , error");
	}

}
