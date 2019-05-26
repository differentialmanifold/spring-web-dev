package com.example.platform.module.common.quartz.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * 调度任务表
 *
 */
@TableName(value = "dev_task")
public class DevTask extends AbstractEntity<Long>{
	
	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -9037511416825370205L;

	@TableId(type = IdType.AUTO ,value="task_id")
	private Integer taskID;
	
	@TableField(value = "job_group")
	private String  jobGroup;
	
	@TableField(value = "job_id")
	private String  jobID;
	
	@TableField(value = "job_name")
	private String  jobName;
	
	@TableField(value = "method_name")
	private String  methodName;
	
	@TableField(value = "spring_id")
	private String  springID;
	
	@TableField(value = "is_concurrent")
	private String  isConcurrent;
	
	@TableField(value = "cron_expression")
	private String  cronExpression;
	
	@TableField(value = "task_title")
	private String  taskTitle;
	
	
	public DevTask(){}
	

	public Integer getTaskID() {
		return taskID;
	}





	public void setTaskID(Integer taskID) {
		this.taskID = taskID;
	}





	public String getJobGroup() {
		return jobGroup;
	}





	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}





	public String getJobID() {
		return jobID;
	}





	public void setJobID(String jobID) {
		this.jobID = jobID;
	}





	public String getJobName() {
		return jobName;
	}





	public void setJobName(String jobName) {
		this.jobName = jobName;
	}





	public String getMethodName() {
		return methodName;
	}





	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}





	public String getSpringID() {
		return springID;
	}





	public void setSpringID(String springID) {
		this.springID = springID;
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





	public String getTaskTitle() {
		return taskTitle;
	}





	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}


	@Override
	public String toString() {
		return JSONArray.toJSONString(this);
	}


	@Override
	public Date getCreateTime() {
		
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setCreateTime(Date createTime) {
		
		// TODO Auto-generated method stub
		
	}


	@Override
	public Date getUpdateTime() {
		
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setUpdateTime(Date updateTime) {
		
		// TODO Auto-generated method stub
		
	}


	@Override
	public Long getId() {
		
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setId(Long id) {
		
		// TODO Auto-generated method stub
		
	}
}
