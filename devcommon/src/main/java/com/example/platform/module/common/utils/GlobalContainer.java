package com.example.platform.module.common.utils;

import com.example.platform.module.common.config.AppProperty;
import com.example.platform.module.common.thread.ExecutorProcessPool;
import org.apache.curator.framework.CuratorFramework;

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
