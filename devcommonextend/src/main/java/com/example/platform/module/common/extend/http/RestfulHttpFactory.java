package com.example.platform.module.common.extend.http;


import com.example.platform.module.common.extend.http.impl.HttpProcessImpl;
import com.example.platform.module.common.extend.http.impl.HttpRetryStrategyRoundRobin;

/**
 * http util factory
 *
 */
public class RestfulHttpFactory {
	
	private static HttpProcess http = null;
	
	public static HttpProcess createDefaultHttpProcess(){
		if(http == null){
			http = new HttpProcessImpl(new HttpRetryStrategyRoundRobin());
		}
		return http;
	}

}
