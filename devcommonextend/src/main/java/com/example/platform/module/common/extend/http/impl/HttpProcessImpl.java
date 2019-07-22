package com.example.platform.module.common.extend.http.impl;

import com.example.platform.module.common.extend.http.HttpProcess;
import com.example.platform.module.common.extend.http.HttpRetryStrategy;
import com.example.platform.module.common.extend.http.HttpUtilInterface;
import com.example.platform.module.common.extend.http.method.*;
import org.apache.http.NameValuePair;
import com.example.platform.module.common.extend.http.HttpClient.DevHttpResponse;

import java.util.List;

public class HttpProcessImpl implements HttpProcess {

	@Override
	public DevHttpResponse get(String url, int retryTimes, int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		HttpUtilInterface httpUtil = new HttpUtilImpl(new DevHttpGet(url));
		return retryStrategy.retry(httpUtil, retryTimes, retrySleepSeconds);
	}

	@Override
	public DevHttpResponse get(List<String> urls, int retryTimes, int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		for(String url : urls){
			return this.get(url, retryTimes, retrySleepSeconds);
		}
		throw new Exception("can't get request" );
	}
	
    /**
     * retry strategy
     */
    private HttpRetryStrategy retryStrategy;

    public HttpProcessImpl(HttpRetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

	@Override
	public DevHttpResponse getByAuth(String url, String user, String pass, int retryTimes, int retrySleepSeconds)
			throws Exception {
		// TODO Auto-generated method stub
		HttpUtilInterface httpUtil = new HttpUtilImpl(new DevHttpGetByAuth(url , user , pass));
		return retryStrategy.retry(httpUtil, retryTimes, retrySleepSeconds);
	}

	@Override
	public DevHttpResponse getByAuth(List<String> urls, String user, String pass, int retryTimes,
			int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		for(String url:urls){
			return this.getByAuth(url, user, pass, retryTimes, retrySleepSeconds);
		}
		throw new Exception("can't get request by auth" );
	}

	@Override
	public DevHttpResponse post(String url, int retryTimes, int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		HttpUtilInterface httpUtil = new HttpUtilImpl(new DevHttpPost(url));
		return retryStrategy.retry(httpUtil, retryTimes, retrySleepSeconds);
	}

	@Override
	public DevHttpResponse post(List<String> urls, int retryTimes, int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		for(String url:urls){
			return this.post(url,  retryTimes, retrySleepSeconds);
		}
		throw new Exception("can't post request" );
	}

	@Override
	public DevHttpResponse post(String url, String params, int retryTimes, int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		HttpUtilInterface httpUtil = new HttpUtilImpl(new DevHttpPost(url ,params));
		return retryStrategy.retry(httpUtil, retryTimes, retrySleepSeconds);
	}

	@Override
	public DevHttpResponse post(List<String> urls, String params, int retryTimes, int retrySleepSeconds)
			throws Exception {
		// TODO Auto-generated method stub
		for(String url:urls){
			return this.post(url, params , retryTimes, retrySleepSeconds);
		}
		throw new Exception("can't post request" );
	}

	@Override
	public DevHttpResponse put(String url, int retryTimes, int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		HttpUtilInterface httpUtil = new HttpUtilImpl(new DevHttpPut(url));
		return retryStrategy.retry(httpUtil, retryTimes, retrySleepSeconds);
	}

	@Override
	public DevHttpResponse put(List<String> urls, int retryTimes, int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		for(String url:urls){
			return this.put(url, retryTimes, retrySleepSeconds);
		}
		throw new Exception("can't put request" );
	}

	@Override
	public DevHttpResponse put(String url, String params, int retryTimes, int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		HttpUtilInterface httpUtil = new HttpUtilImpl(new DevHttpPut(url ,params));
		return retryStrategy.retry(httpUtil, retryTimes, retrySleepSeconds);
	}

	@Override
	public DevHttpResponse put(List<String> urls, String params, int retryTimes, int retrySleepSeconds)
			throws Exception {
		// TODO Auto-generated method stub
		for(String url:urls){
			return this.put(url,params , retryTimes, retrySleepSeconds);
		}
		throw new Exception("can't put request" );
	}

	@Override
	public DevHttpResponse delete(String url, int retryTimes, int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		HttpUtilInterface httpUtil = new HttpUtilImpl(new DevHttpDelete(url));
		return retryStrategy.retry(httpUtil, retryTimes, retrySleepSeconds);
	}

	@Override
	public DevHttpResponse delete(List<String> urls, int retryTimes, int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		for(String url:urls){
			return this.delete(url, retryTimes, retrySleepSeconds);
		}
		throw new Exception("can't delete request" );
	}

	@Override
	public DevHttpResponse postByBodyParam(String url, List<NameValuePair> params, int retryTimes,
			int retrySleepSeconds) throws Exception {
		// TODO Auto-generated method stub
		HttpUtilInterface httpUtil = new HttpUtilImpl(new DevHttpPost(url ,params));
		return retryStrategy.retry(httpUtil, retryTimes, retrySleepSeconds);
	}

}
