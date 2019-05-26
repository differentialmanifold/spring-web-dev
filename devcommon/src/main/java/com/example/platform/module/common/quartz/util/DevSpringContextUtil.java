package com.example.platform.module.common.quartz.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DevSpringContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	public static Object getBean(String name) throws BeansException {
		 if(null == name) return null;
	     return applicationContext.getBean(name);  
	}  

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		DevSpringContextUtil.applicationContext = arg0;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	

}
