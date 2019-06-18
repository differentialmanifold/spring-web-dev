package com.example.platform.module.common.utils;

public class ConstantUtil {
	
	public static final String THREAD_INPUT = "process_input";
	public static final String THREAD_ERROR = "process_error";

	public static final String Process_Tree_Kill_BinaryPath = System.getProperty("catalina.home")
			+ "/bin/tree_kill.sh";/* kill process tree */


	/**
	 * Portal module thread pool's thread count
	 */
	public static final int PORTAL_THREAD_COUNT = 10;

	/**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^1(3|4|5|7|8)[0-9]\\d{8}$";

	public final static String SCHEDULER_TYPE_SYNC         = "sync_scheduler";
	public final static String SCHEDULER_TYPE_OTHER        = "other_scheduler";


	// hdfs hive超级用户
	public static final String HIVE_SUPER_USER = "hive";
	public static final String HDFS_SUPER_USER = "hdfs";

}
