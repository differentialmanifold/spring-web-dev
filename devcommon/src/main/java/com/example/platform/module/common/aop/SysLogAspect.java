package com.example.platform.module.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;


/**
 * 记录访问日志
 *
 */
@Aspect
@Component
public class SysLogAspect {
	
	private static final Logger LOG = LoggerFactory.getLogger(SysLogAspect.class);

	
	@Pointcut("@annotation(com.example.platform.module.common.aop.RequestLog)")
	public void requestLogAspect() {
	}
	
	/**
	 * 后置通知，用于记录操作日志
	 * 
	 * @param joinPoint
	 */
	@AfterReturning("requestLogAspect()")
	public void accessLog(JoinPoint joinPoint) {
		try {
			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			Class<?> targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();

			String name = "";
			String description = "";

			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						name = method.getAnnotation(RequestLog.class).name();
						description = method.getAnnotation(RequestLog.class).description();
						break;
					}
				}
			}

			LOG.info("Current time is {}", getDateTime(new Date()));
			LOG.info("name is {}", name);
			LOG.info("description is {}", description);
		} catch (Exception e) {
			LOG.error("系统日志记录错误", e);
		}

	}

	public static String getDateTime(Date date) {
		java.text.SimpleDateFormat d = new java.text.SimpleDateFormat();
		d.applyPattern("yyyy-MM-dd HH:mm:ss");
		String str_date = d.format(date);
		return str_date;
	}
	



}
