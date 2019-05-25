package com.example.platform.module.common.factory;

import com.example.platform.module.service.GroupService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationContextFactory {
	private static AbstractApplicationContext springContext = null;
	
	public static AbstractApplicationContext getSpringContext() {
		if(springContext == null) {
		    springContext = new  ClassPathXmlApplicationContext("spring-context.xml");
		}
		return springContext;
	}
	
	/**
	 * use case
	 * @param args
	 */
	public static void main(String[] args) {
		GroupService groupService = (GroupService) SpringApplicationContextFactory.getSpringContext().getBean("groupService");
	}
	 

}
