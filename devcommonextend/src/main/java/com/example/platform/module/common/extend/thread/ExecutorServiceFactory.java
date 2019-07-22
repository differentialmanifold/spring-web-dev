package com.example.platform.module.common.extend.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池构造工厂
 */
public class ExecutorServiceFactory {
	
	private ExecutorService executors;
	
	
	public  ExecutorServiceFactory(){
		
	}
	
	
	/**
	 * 创建一个调度型线程池，可支持定时及周期性任务
	 * @return
	 */
	public ExecutorService createScheduledThreadPool() {
		//CPU个数
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		
		executors = Executors.newScheduledThreadPool(availableProcessors * 10, getThreadFactory());
        return executors;
	}
	
	
	/**
	 * 创建一个单线程化的Executor，可保证顺序地执行各个任务
	 * @return
	 */
	public ExecutorService createSingleThreadExecutor() {
		executors  =  Executors.newSingleThreadExecutor(getThreadFactory());
		return executors;
	}
	
	
	/**
	 * 创建一个可缓存的线程池，调用execute将重用以前的线程（如果线程可用）
	 * 如果没有可用的，则创建一个新线程，并添加到池中。
	 * 终止并从缓存中删除哪些已有60s未被使用的线程。
	 * @return
	 */
	public ExecutorService createCachedThreadPool() {
		executors = Executors.newCachedThreadPool(getThreadFactory());
        return executors;
	}
	
	
	/**
	 * 创建可重用固定数目线程的线程池
	 * @return
	 */
	public ExecutorService createFixedThreadPool(int count) {
		executors = Executors.newFixedThreadPool(count, getThreadFactory());
        return executors;
	}
	
	
	/**
	 * 获取线程池工厂
	 * @return
	 */
	private ThreadFactory getThreadFactory() {
		return new ThreadFactory() {
			AtomicInteger sn = new AtomicInteger();
            public Thread newThread(Runnable r) {
                SecurityManager s = System.getSecurityManager();
                ThreadGroup group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
                Thread t = new Thread(group, r);
                t.setDaemon(true);
                t.setName("dev-thread-pool - " + sn.incrementAndGet());
                return t;
            }
		};
	}
}
