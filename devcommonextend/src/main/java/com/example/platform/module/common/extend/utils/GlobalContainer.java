package com.example.platform.module.common.extend.utils;

import com.example.platform.module.common.config.AppProperty;
import com.example.platform.module.common.extend.thread.ExecutorProcessPool;

public class GlobalContainer {

	/*
	 * global thread pool
	 */
	public static ExecutorProcessPool threadPool = null;
	
	/*
	 * Initialize on SchedulerInitListener 
	 */
	public static AppProperty appProperty = null;

	

}
