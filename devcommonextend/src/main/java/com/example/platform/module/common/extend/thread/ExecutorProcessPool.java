package com.example.platform.module.common.extend.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 线程处理类
 */
public class ExecutorProcessPool {
	
	private ExecutorService executor;

	private  int defaultThreadCount = 10;

	public  ExecutorProcessPool() {
		executor = new ExecutorServiceFactory().createFixedThreadPool(defaultThreadCount);
	}
	
	public  ExecutorProcessPool(int threadCount) {
		if(threadCount <= 0){
			throw new IllegalArgumentException();
		}
		executor = new ExecutorServiceFactory().createFixedThreadPool(threadCount);
	}

	/**
	 * 关闭线程池，线程池会执行完队列中的所有任务才退出
	 */
	public void shutdown() {
		executor.shutdown();
	}
	
	
	/**
	 * 提交任务到线程池，可以接收线程返回值
	 * @param task
	 * @return
	 */
	public Future<?> submit(Runnable task) {
		return executor.submit(task);
	}
	
	
	/**
	 * 提交任务到线程池，没有返回值
	 * @param task
	 */
	public void execute(Runnable task) {
		executor.execute(task);
	}

	public ExecutorService getExecutor() {
		return executor;
	}
	
}
