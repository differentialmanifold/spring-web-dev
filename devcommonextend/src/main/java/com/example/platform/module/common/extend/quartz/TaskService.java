package com.example.platform.module.common.extend.quartz;


import com.example.platform.module.common.extend.quartz.entity.DevQuartzJob;
import com.example.platform.module.common.extend.quartz.entity.DevQuartzJobVO;
import com.example.platform.module.common.extend.quartz.entity.DevQuartzNoConcurrentJob;
import com.example.platform.module.common.extend.quartz.util.TaskUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Service("taskService")
public class TaskService {
	
	private final Logger LOG = LoggerFactory.getLogger(TaskService.class);    
    
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	    
	/**    
	 * 获取单个任务    
	 * @param jobName    
	 * @param jobGroup    
	 * @return    
	 * @throws SchedulerException    
	 */    
	public DevQuartzJobVO getJob(String jobName, String jobGroup) throws SchedulerException{
		DevQuartzJobVO job = null;
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);    
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);      
		if (null != trigger) {    
			job = new DevQuartzJobVO();
	        job.setJobName(jobName);    
	        job.setJobGroup(jobGroup);    
	        job.setDescription("触发器:" + trigger.getKey());    
//	        job.setNextTime(trigger.getNextFireTime()); //下次触发时间    
//	        job.setPreviousTime(trigger.getPreviousFireTime());//上次触发时间    
	        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());    
	        job.setJobStatus(triggerState.name());    
	        if (trigger instanceof CronTrigger) {    
	            CronTrigger cronTrigger = (CronTrigger) trigger;    
	            String cronExpression = cronTrigger.getCronExpression();    
	            job.setCronExpression(cronExpression);    
	        }    
		}		    
        return job;    
	}    
	/**    
	 * 获取所有任务    
	 * @return    
	 * @throws SchedulerException    
	 */    
	public List<DevQuartzJobVO> getAllJobs() throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();      
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();    
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);    
		List<DevQuartzJobVO> jobList = new ArrayList<DevQuartzJobVO>();
		for (JobKey jobKey : jobKeys) {    
		    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);    
		    for (Trigger trigger : triggers) {    
		        DevQuartzJobVO job = new DevQuartzJobVO();
		        job.setJobName(jobKey.getName());    
		        job.setJobGroup(jobKey.getGroup());    
		        job.setDescription("触发器:" + trigger.getKey());    
		            
//		        job.setNextTime(trigger.getNextFireTime()); //下次触发时间		       
//		        trigger.getFinalFireTime();//最后一次执行时间    
//		        job.setPreviousTime(trigger.getPreviousFireTime());//上次触发时间    
//		        trigger.getStartTime();//开始时间    
//		        trigger.getEndTime();//结束时间		            
		        //触发器当前状态    
		        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());    
		        job.setJobStatus(triggerState.name());    
		        //    
		        if (trigger instanceof CronTrigger) {    
		            CronTrigger cronTrigger = (CronTrigger) trigger;    
		            String cronExpression = cronTrigger.getCronExpression();    
		            job.setCronExpression(cronExpression);    
		        }    
		        jobList.add(job);    
		    }    
		}    
		return jobList;    
	}    
	    
	/**    
	 * 所有正在运行的job    
	 *     
	 * @return    
	 * @throws SchedulerException    
	 */    
	public List<DevQuartzJobVO> getRunningJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();    
		List<DevQuartzJobVO> jobList = new ArrayList<DevQuartzJobVO>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {    
			DevQuartzJobVO job = new DevQuartzJobVO();
			JobDetail jobDetail = executingJob.getJobDetail();    
			JobKey jobKey = jobDetail.getKey();    
			Trigger trigger = executingJob.getTrigger();    
			job.setJobName(jobKey.getName());    
			job.setJobGroup(jobKey.getGroup());    
			job.setDescription("触发器:" + trigger.getKey());    
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());    
			job.setJobStatus(triggerState.name());    
			if (trigger instanceof CronTrigger) {    
				CronTrigger cronTrigger = (CronTrigger) trigger;    
				String cronExpression = cronTrigger.getCronExpression();    
				job.setCronExpression(cronExpression);    
			}    
			jobList.add(job);    
		}    
		return jobList;    
	}    
	    
	/**    
	 * 查询任务列表    
	 * @return    
	 */    
	public List<DevQuartzJobVO> getTaskList(){
		List<DevQuartzJobVO> jobs = new ArrayList<DevQuartzJobVO>();
		return jobs;    
	}    

	    
	/**    
	 * 添加任务    
	 *
	 * @throws SchedulerException    
	 */    
	public boolean addJob(DevQuartzJobVO job) throws SchedulerException {
//		if (job == null || !BDMQuartzJobVO.STATUS_RUNNING.equals(job.getJobStatus())) {    
//			return false;    
//		}    
		if(!TaskUtils.isValidExpression(job.getCronExpression())){
			LOG.error("时间表达式错误（"+job.getJobName()+","+job.getJobGroup()+"）,"+job.getCronExpression());    
			return false;    
		}else{    
			Scheduler scheduler = schedulerFactoryBean.getScheduler();    
			// 任务名称和任务组设置规则：    // 名称：task_1 ..    // 组 ：group_1 ..        
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());    
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);        
			// 不存在，创建一个       
			if (null == trigger) { 
				boolean isConcurrent = DevQuartzJobVO.CONCURRENT_IS.equals(job.getIsConcurrent());
				//是否允许并发执行    
				Class<? extends Job> clazz =  null;
				if(isConcurrent){
					clazz = DevQuartzJob.class;
				}
				clazz = DevQuartzNoConcurrentJob.class;
				JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();     
				jobDetail.getJobDataMap().put("scheduleJob", job);     
				// 表达式调度构建器         
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());    
				// 按新的表达式构建一个新的trigger         
				trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();     
				scheduler.scheduleJob(jobDetail, trigger);       
			} else {     // trigger已存在，则更新相应的定时设置         
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());         
				// 按新的cronExpression表达式重新构建trigger         
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();        
				// 按新的trigger重新设置job执行         
				scheduler.rescheduleJob(triggerKey, trigger);       
			}			      
		}    
		return true;    
	}    
    
	/**    
	 * 暂停任务    
	 * @param scheduleJob    
	 * @return    
	 */    
	public boolean pauseJob(DevQuartzJobVO scheduleJob){
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());    
		try {    
			scheduler.pauseJob(jobKey);    
			return true;    
		} catch (SchedulerException e) {			    
		}    
		return false;    
	}    
	    
	/**    
	 * 恢复任务    
	 * @param scheduleJob    
	 * @return    
	 */    
	public boolean resumeJob(DevQuartzJobVO scheduleJob){
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());    
		try {    
			scheduler.resumeJob(jobKey);    
			return true;    
		} catch (SchedulerException e) {			    
		}    
		return false;    
	}    
	    
	/**    
	 * 删除任务    
	 */    
	public boolean deleteJob(DevQuartzJobVO scheduleJob){
		Scheduler scheduler = schedulerFactoryBean.getScheduler();   
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());    
		try{   
			if(scheduler.checkExists(jobKey)){
				scheduler.deleteJob(jobKey);    
			}
			return true;    
		} catch (SchedulerException e) {		
			e.printStackTrace();
		}    
		return false;    
	}    
	    
	/**    
	 * 立即执行一个任务    
	 * @param scheduleJob    
	 * @throws SchedulerException    
	 */    
	public void runJob(DevQuartzJobVO scheduleJob) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());    
		scheduler.triggerJob(jobKey);    
	}    
	    
	/**    
	 * 更新任务时间表达式    
	 * @param scheduleJob    
	 * @throws SchedulerException    
	 */    
	public void updateCronExpression(DevQuartzJobVO scheduleJob) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());    
		//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"    
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);    
		//表达式调度构建器    
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());    
		//按新的cronExpression表达式重新构建trigger    
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();    
		//按新的trigger重新设置job执行    
		scheduler.rescheduleJob(triggerKey, trigger);    
	}

}
