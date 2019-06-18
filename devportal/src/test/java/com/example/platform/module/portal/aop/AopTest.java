package com.example.platform.module.portal.aop;

import com.example.platform.module.common.aop.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config/spring-context.xml"})
public class AopTest {
	
	@Autowired
	private AopService aopService;

	@Test
	public void test() throws Exception {

		aopService.getAllUser("aop test");
		System.out.println("=========");
	}

}
