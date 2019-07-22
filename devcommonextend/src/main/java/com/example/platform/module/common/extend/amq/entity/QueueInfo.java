package com.example.platform.module.common.extend.amq.entity;

import java.io.Serializable;

/**
 */
public class QueueInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3859879953427127198L;
	
	/**
	 * Queue name
	 */
	private String queueName;
	
	/**
	 * Number Of Pending Message
	 */
	private long pMessageNum;
	
	/**
	 * Number Of Consumer
	 */
	private long consumersNum;
	
	/**
	 * Number of Messages Enqueued
	 */
	private long mEnqueuedNum;
	
	/**
	 * Number of Messages Dequeued
	 */
	private long mDequeuedNum;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public long getpMessageNum() {
		return pMessageNum;
	}

	public void setpMessageNum(long pMessageNum) {
		this.pMessageNum = pMessageNum;
	}

	public long getConsumersNum() {
		return consumersNum;
	}

	public void setConsumersNum(long consumersNum) {
		this.consumersNum = consumersNum;
	}

	public long getmEnqueuedNum() {
		return mEnqueuedNum;
	}

	public void setmEnqueuedNum(long mEnqueuedNum) {
		this.mEnqueuedNum = mEnqueuedNum;
	}

	public long getmDequeuedNum() {
		return mDequeuedNum;
	}

	public void setmDequeuedNum(long mDequeuedNum) {
		this.mDequeuedNum = mDequeuedNum;
	}
	
}
