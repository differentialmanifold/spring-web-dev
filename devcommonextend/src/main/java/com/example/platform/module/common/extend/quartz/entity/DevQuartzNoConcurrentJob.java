package com.example.platform.module.common.extend.quartz.entity;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DevQuartzNoConcurrentJob implements Job{
	
	private final Logger logger = LoggerFactory.getLogger(DevQuartzNoConcurrentJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		 DevQuartzJobVO scheduleJob = (DevQuartzJobVO)context.getMergedJobDataMap().get("scheduleJob");
	     logger.info("运行任务名称 = [" + scheduleJob.getJobName() + "]");     
//	     TaskUtils.invokMethod(scheduleJob);
	}

}
