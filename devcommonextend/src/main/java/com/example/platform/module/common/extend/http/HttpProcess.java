package com.example.platform.module.common.extend.http;

import org.apache.http.NameValuePair;

import java.util.List;

import com.example.platform.module.common.extend.http.HttpClient.DevHttpResponse;

public interface HttpProcess {
	
	DevHttpResponse postByBodyParam(String url, List<NameValuePair> params, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse get(String url, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse get(List<String> urls, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse getByAuth(String url, String user, String pass, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse getByAuth(List<String> urls, String user, String pass, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse post(String url, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse post(List<String> urls, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse post(String url, String params, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse post(List<String> urls, String params, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse put(String url, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse put(List<String> urls, int retryTimes, int retrySleepSeconds)throws Exception;

    DevHttpResponse put(String url, String params, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse put(List<String> urls, String params, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse delete(String url, int retryTimes, int retrySleepSeconds)throws Exception;

	DevHttpResponse delete(List<String> urls, int retryTimes, int retrySleepSeconds)throws Exception;
}
