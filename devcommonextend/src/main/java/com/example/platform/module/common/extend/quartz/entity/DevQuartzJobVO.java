package com.example.platform.module.common.extend.quartz.entity;

import org.quartz.CronExpression;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DevQuartzJobVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7170803461492112626L;
	public static final String STATUS_RUNNING = "1";     
	public static final String STATUS_NOT_RUNNING = "0";     
	public static final String CONCURRENT_IS = "1";     
	public static final String CONCURRENT_NOT = "0";  
	
	private int taskID;
	     
	/** 任务id */     
    private String jobId;     
      
    /** 任务名称 */     
    private String jobName;     
      
    /** 任务分组，任务名称+组名称应该是唯一的 */     
    private String jobGroup;     
      
    /** 任务初始状态 0禁用 1启用 2删除*/     
    private String jobStatus;     
      
    /** 任务是否有状态（并发） */     
	private String isConcurrent = "1";     
	     
    /** 任务运行时间表达式 */     
    private String cronExpression;     
      
    /** 任务描述 */     
    private String description;     
     
    /** 任务调用类在spring中注册的bean id，如果spingId不为空，则按springId查找 */     
    private String springId;     
         
    /** 任务调用类名，包名+类名，通过类反射调用 ，如果spingId为空，则按jobClass查找   */     
    private String jobClass;     
         
    /** 任务调用的方法名 */     
	private String methodName;     
         
    /** 启动时间 */     
    private String startTime;     
         
    /** 前一次运行时间 */     
    private String previousTime;     
         
    /** 下次运行时间 */     
    private String nextTime;
    
    private String title;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpringId() {
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getPreviousTime() {
		return previousTime;
	}

	public void setPreviousTime(String previousTime) {
		this.previousTime = previousTime;
	}

	public String getNextTime() {
		return nextTime;
	}

	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}





	/*     
	 * 任务运行状态     
	 */     
	public enum TASK_STATE{     
		NONE("NONE","未知"),     
		WAIT("WAIT", "等待"),     
		NORMAL("NORMAL", "正常运行"),     
		PAUSED("PAUSED", "暂停状态"),      
		COMPLETE("COMPLETE","运行完成"),     
		ERROR("ERROR","错误状态"),     
		BLOCKED("BLOCKED","锁定状态");     
		     
		private TASK_STATE(String index,String name) {        
	        this.name = name;        
	        this.index = index;        
	    }       
		private String index;       
		private String name;     
		
		
		public String getName() {        
	        return name;        
	    }        
	    public String getIndex() {        
	        return index;        
	    }     
	} 
	
	
    public static List<DevQuartzJobVO> devTask2VO(List<DevTask> list){
    	List<DevQuartzJobVO> voList = new ArrayList<DevQuartzJobVO>();
    	if(list == null || list.size() == 0)  return voList;
    	Date now = new Date(System.currentTimeMillis()) ;
    	for(DevTask task : list){
    		DevQuartzJobVO vo = new DevQuartzJobVO();
    		vo.setTaskID(task.getTaskID());
        	vo.setCronExpression(task.getCronExpression());
        	vo.setIsConcurrent(task.getIsConcurrent());
        	vo.setJobGroup(task.getJobGroup());
        	vo.setJobId(task.getJobID());
        	vo.setJobName(task.getJobName());
        	vo.setMethodName(task.getMethodName());
        	vo.setSpringId(task.getSpringID());
        	vo.setTitle(task.getTaskTitle());
        	vo.setTaskID(task.getTaskID());
        	
        	try{
        	    //nextTime
        	    CronExpression cronExpression = new CronExpression(task.getCronExpression());
        	    Date fireTime  = cronExpression.getNextValidTimeAfter(now) ;
				SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
				vo.setNextTime(sdf.format(fireTime));
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	
        	
        	voList.add(vo);
    	}
    	return voList;
    }
	
    public static DevQuartzJobVO devTask2VO(DevTask task){
    	DevQuartzJobVO vo = new DevQuartzJobVO();
    	vo.setCronExpression(task.getCronExpression());
    	vo.setIsConcurrent(task.getIsConcurrent());
    	vo.setJobGroup(task.getJobGroup());
    	vo.setJobId(task.getJobID());
    	vo.setJobName(task.getJobName());
    	vo.setMethodName(task.getMethodName());
    	vo.setSpringId(task.getSpringID());
    	vo.setTitle(task.getTaskTitle());
    	vo.setTaskID(task.getTaskID());
    	return vo;
    }
	
    public static DevTask vo2DevTask(DevQuartzJobVO vo){
    	DevTask task = new DevTask();
    	task.setTaskID(vo.getTaskID());
    	task.setCronExpression(vo.getCronExpression());
    	task.setIsConcurrent(vo.getIsConcurrent());
    	task.setJobGroup(vo.getJobGroup());
    	task.setJobID(vo.getJobId());
    	task.setJobName(vo.getJobName());
    	task.setMethodName(vo.getMethodName());
    	task.setSpringID(vo.getSpringId());
    	task.setTaskTitle(vo.getTitle());
    	return task;
    }
    
    

}
