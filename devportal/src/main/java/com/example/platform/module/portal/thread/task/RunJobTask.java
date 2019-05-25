package com.example.platform.module.portal.thread.task;

import com.example.platform.module.common.config.AppProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RunJobTask implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(RunJobTask.class);

	private AppProperty appProperty;
	
	public RunJobTask(AppProperty appProperty) {
		this.appProperty = appProperty;
	}
	
	@Override
	public void run() {

		String env = appProperty.getEnv();

		logger.info("The env is {}",env);
	}
}
