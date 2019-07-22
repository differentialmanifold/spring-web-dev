package com.example.platform.module.common.extend.quartz.util;

import com.example.platform.module.common.extend.quartz.entity.DevQuartzJobVO;
import org.apache.commons.lang.StringUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Date;

public class TaskUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(TaskUtils.class);     
    
	/**     
	 * 通过反射调用scheduleJob中定义的方法     
	 *      
	 * @param scheduleJob     
	 */     
	public static void invokMethod(DevQuartzJobVO scheduleJob) {
		Object object = null;     
		Class<?> clazz = null;   
		if(StringUtils.isNotBlank(scheduleJob.getSpringId())){
			object = DevSpringContextUtil.getBean( scheduleJob.getSpringId() );
		}else if (StringUtils.isNotBlank(scheduleJob.getJobClass())) {//按jobClass查找
			try {     
				clazz = Class.forName(scheduleJob.getJobClass());   
				object = clazz.newInstance();     
			} catch (Exception e) {     
				e.printStackTrace();     
			}     
		}     
		if (object == null) {     
			logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查执行类是否配置正确！！！");     
			return;     
		}     
		clazz = object.getClass();     
		Method method = null;     
		try {     
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());   
		} catch (NoSuchMethodException e) {     
			logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查执行类的方法名是否设置错误！！！");     
		} catch (SecurityException e) {     
			e.printStackTrace();		     
		}     
		if (method != null) {     
			try {     
				method.invoke(object);     
			} catch (Exception e) {     
				// TODO Auto-generated catch block     
				e.printStackTrace();     
			}     
		}		     
	}     
     
	/**     
	 * 判断cron时间表达式正确性     
	 * @param cronExpression     
	 * @return      
	 */     
	public static boolean isValidExpression(final String cronExpression){     
		CronTriggerImpl trigger = new CronTriggerImpl();        
        try {     
			trigger.setCronExpression(cronExpression);     
			Date date = trigger.computeFirstFireTime(null);       
	        return date != null && date.after(new Date());        
		} catch (Exception e) {   
			e.printStackTrace();
		}      
        return false;     
	}  
	
	/**
	 * 判断Spring Bean是否存在,方法是否存在
	 * @return
	 */
	public static boolean isMethodExist(String springID  , String methodName){
		Object object = null;     
		Class<?> clazz = null;   
		boolean isExist = false;
		try{
		    object = DevSpringContextUtil.getBean( springID );
		}catch(Exception e){
			e.printStackTrace();
			return isExist;
		}
		
		clazz = object.getClass();     
		Method method = null; 
		try{
			method = clazz.getDeclaredMethod( methodName );   
		}catch(NoSuchMethodException e){
			e.printStackTrace();
			return isExist;
		}
	    
		return true;
	}

}
