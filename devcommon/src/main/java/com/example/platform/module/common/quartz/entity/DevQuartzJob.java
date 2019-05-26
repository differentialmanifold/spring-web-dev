package com.example.platform.module.common.quartz.entity;

import com.example.platform.module.common.quartz.util.TaskUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DevQuartzJob implements Job{
	
	private static final Logger logger = LoggerFactory.getLogger(DevQuartzJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		 DevQuartzJobVO scheduleJob = (DevQuartzJobVO)context.getMergedJobDataMap().get("scheduleJob");
	     logger.info("运行任务名称 = [" + scheduleJob.getJobName() + "]");     
	     TaskUtils.invokMethod(scheduleJob);
	}

}
