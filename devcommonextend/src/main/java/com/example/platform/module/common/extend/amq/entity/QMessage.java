package com.example.platform.module.common.extend.amq.entity;

import java.io.Serializable;

/**
 * Activemq message entity
 */
public class QMessage implements Serializable {

	private static final long serialVersionUID = -3903776634069547311L;

	/**
	 * message content
	 */
	private String content;
	/**
	 * message destination, queue/topic name
	 */
	private String destination;			
	/**
	 * If support transaction or not, default value is true
	 */
	private boolean transaction = true;
	/**
	 * If support persistent or not, default value is false
	 */
	private boolean persistent = false;
	/**
	 * Timestamp, default value is current time
	 */
	private long time = System.currentTimeMillis();
	
	public QMessage(){};
	
	public QMessage(String destination , String content){
		this (destination, content, true, false);
	}
	
	public QMessage(String destination , String content , boolean transaction ,boolean persistent){
		this.destination = destination;
		this.content = content;
		this.transaction = transaction;
		this.persistent = persistent;
		this.time = System.currentTimeMillis();
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public boolean isTransaction() {
		return transaction;
	}

	public void setTransaction(boolean transaction) {
		this.transaction = transaction;
	}

	public boolean isPersistent() {
		return persistent;
	}

	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
}
