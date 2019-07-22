package com.example.platform.module.common.extend.amq.entity;

/**
 * Amq connection config entity
 */
public class AmqConnConfig {
	
	public static final String MQ_DEFAULT_USER = "admin";
	public static final String MQ_DEFAULT_PASS = "admin";
	
	
	private String url;
	private String user;
	private String password;
	
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
