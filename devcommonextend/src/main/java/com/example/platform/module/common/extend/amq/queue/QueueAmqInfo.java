package com.example.platform.module.common.extend.amq.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 */
public class QueueAmqInfo {
	
	private static final Logger logger = LoggerFactory.getLogger(QueueAmqInfo.class);
	
	private ActiveMQConnectionFactory activeMQConnectionFactory;
	private ActiveMQConnection conn;
	private DestinationSource destinationSource;
	
	public QueueAmqInfo(ActiveMQConnectionFactory activeMQConnectionFactory) {
		this.activeMQConnectionFactory = activeMQConnectionFactory;
		
		init();
	}
	
	public void init() {
		try {
			conn = (ActiveMQConnection)activeMQConnectionFactory.createQueueConnection();
			conn.start();
			destinationSource = conn.getDestinationSource();
		} catch (JMSException e) {
			logger.error("######ActiveMQ info class init exception:", e);
		}
	}
	
	/**
	 * Get queue size
	 * @return
	 */
	public int getSize() {
		return getQueues().size();
	}
	
	/**
	 * Get queue name list
	 * @return
	 */
	public List<String> getQueueNames() {
		List<String> queueNames = new LinkedList<String>();
		Set<ActiveMQQueue> queues = getQueues();
		
		for (ActiveMQQueue queue : queues) {
			try {
				queueNames.add(queue.getQueueName());
			} catch (JMSException e) {
				logger.error("######Get queue name list exception:", e);
			}
		}
		
		return queueNames;
	}
	
	/**
	 * Get queue set(ActiveMQQueue)
	 * @return 
	 */
	public Set<ActiveMQQueue> getQueues() {
		return destinationSource.getQueues();
	}
	
	/**
	 * close
	 */
	public void close() {
		try {
			conn.close();
		} catch (JMSException e) {
			logger.error("######ActiveMQ connection close exception:", e);
		}
	}
	
	public static void main(String[] args) throws JMSException, InterruptedException {
		ActiveMQConnectionFactory activeMQConnectionFactory =  new ActiveMQConnectionFactory("tcp://10.17.2.134:61616");
		QueueAmqInfo qai = new QueueAmqInfo(activeMQConnectionFactory);
		
		List<String> queueNames = qai.getQueueNames();
		int size = qai.getSize();
		
		for (String queue : queueNames) {
			System.out.println("###0.0###" + queue);
		}
		
		System.out.println("###=.=###" + size);
		
		qai.close();
	}
}
