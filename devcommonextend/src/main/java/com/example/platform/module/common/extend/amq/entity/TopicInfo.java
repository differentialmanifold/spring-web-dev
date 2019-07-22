package com.example.platform.module.common.extend.amq.entity;

import java.io.Serializable;

/**
 */
public class TopicInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6496135380503726728L;

	/**
	 * Topic name
	 */
	private String topicName;
	
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

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
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
