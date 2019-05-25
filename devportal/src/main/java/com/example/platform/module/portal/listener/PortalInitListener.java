package com.example.platform.module.portal.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.example.platform.module.common.config.AppProperty;
import com.example.platform.module.common.thread.ExecutorProcessPool;
import com.example.platform.module.common.utils.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import static com.example.platform.module.common.utils.GlobalContainer.appProperty;
import static com.example.platform.module.common.utils.GlobalContainer.threadPool;


/**
 * Portal initialize class
 *
 */
public class PortalInitListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(PortalInitListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("######Shutdown the thread pool");
		threadPool.shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("######Portal contextInitialize init");
		
		/**
		 * Initialize the thread pool's count 
		 */
		threadPool = new ExecutorProcessPool(ConstantUtil.PORTAL_THREAD_COUNT);
		
		WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext(); 
		
		if (ac != null && ac.getBean("appProperty") != null && appProperty == null) {
			appProperty = (AppProperty) ac.getBean("appProperty");
		}
		
		String env = appProperty.getEnv();

		logger.info("######Portal contextInitialize init, env is {}", env);

	}

}
