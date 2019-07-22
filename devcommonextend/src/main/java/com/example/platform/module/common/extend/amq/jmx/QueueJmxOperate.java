package com.example.platform.module.common.extend.amq.jmx;

import com.example.platform.module.common.extend.amq.entity.QueueInfo;
import com.example.platform.module.common.extend.amq.util.IOUtil;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.io.IOException;

/**
 * ActiveMQ Queue operation through JMX
 *
 */
public class QueueJmxOperate extends JmxOperate {
	
	private static final Logger logger = LoggerFactory.getLogger(QueueJmxOperate.class);  
	
	public QueueJmxOperate(String url) throws IOException, MalformedObjectNameException {
		super(url);
	}
	
	public QueueJmxOperate(String url, String user, String password, String brokerName) throws IOException, MalformedObjectNameException {
		super(url, user, password, brokerName);
	}
	
	
	/**
	 * Get ActiveMQ all queues
	 * @return
	 */
	public ObjectName[] getQueues() {
		return getMbean().getQueues();
	}
	
	
	/**
	 * Get ActiveMQ queue size
	 * @return
	 */
	public long getQueueNums() {
		return getQueues().length;
	}
	
	
	/**
	 * @param queueName
	 * @return
	 */
	public QueueViewMBean getQueueMbean(ObjectName queue) {
		return (QueueViewMBean)MBeanServerInvocationHandler
				.newProxyInstance(getConnection(), queue, QueueViewMBean.class, true);
	}
	
	
	/**
	 * Get QueueViewMbean by queue name
	 * etc. queue.fortest
	 * 
	 * @param queueName
	 * @return
	 */
	public QueueViewMBean getQueueMbean(String queueName) {
		ObjectName queue = getQueueObjectName(queueName);
		return getQueueMbean(queue);
	}
	
	
	/**
	 * Get queue ObjectName by queue name string
	 * 
	 * etc.
	 * queue.fortest  ->
	 * org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,destinationName=queue.fortest
	 * 
	 * @param queueName
	 * @return
	 */
	public ObjectName getQueueObjectName(String queueName) {
		ObjectName[] queues = getQueues();
		
		for (ObjectName queue : queues) {
			QueueViewMBean queueMbean = getQueueMbean(queue);
			
			if (queueMbean.getName().equals(queueName)) {
				return queue;
			}
		}
		
		return null;
	}
	
	
	/**
	 * Get QueueInfo
	 * 
	 * @param queueName
	 * @return
	 */
	public QueueInfo getQueueInfo(String queueName) {
		QueueViewMBean queueMbean = getQueueMbean(queueName);
		
		return boxQueueInfo(queueMbean);
	}
	
	
	/**
	 * Get QueueInfo
	 * @param queue
	 * @return
	 */
	public QueueInfo getQueueInfo(ObjectName queue) {
		QueueViewMBean queueMbean = getQueueMbean(queue);
		
		return boxQueueInfo(queueMbean);
	}
	
	
	/**
	 * @param queueMbean
	 * @return
	 */
	public QueueInfo boxQueueInfo(QueueViewMBean queueMbean) {
		QueueInfo queueInfo = new QueueInfo();
		
		queueInfo.setQueueName(queueMbean.getName());
		queueInfo.setpMessageNum(queueMbean.getQueueSize());
		queueInfo.setConsumersNum(queueMbean.getConsumerCount());
		queueInfo.setmEnqueuedNum(queueMbean.getEnqueueCount());
		queueInfo.setmDequeuedNum(queueMbean.getDequeueCount());
		
		return queueInfo;
	}
	
	
	/**
	 * Delete the queue
	 * @param queueName
	 * @throws Exception
	 */
	public void deleteQueue(String queueName) throws Exception {
		getMbean().removeQueue(queueName);
	}
	
	
	/**
	 * Remove the message by messageID
	 * @param queueName
	 * @param messageID
	 * @throws Exception
	 */
	public void removeMessage(String queueName, String messageID) throws Exception {
		QueueViewMBean queueMbean = getQueueMbean(queueName);
		queueMbean.removeMessage(messageID);
	}
	
	
	/**
	 * close
	 */
	public void close() {
		try {
			IOUtil.closeQuietly(getConnector());
		} catch (Exception e) {
			logger.error("######Close exception:", e);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		QueueJmxOperate qjo = new QueueJmxOperate("service:jmx:rmi:///jndi/rmi://172.81.252.75:11099/jmxrmi");
		System.out.println("#####   " + qjo.getQueueNums());
		
		/*for (ObjectName queueName : qjo.getQueues()) {
			QueueInfo qi = qjo.getQueueInfo(queueName);
			System.out.println("############begin###########");
			System.out.println("&&&&&&&&&" + queueName);
			System.out.println("queue name:" + qi.getQueueName());
			System.out.println("consumer number:" + qi.getConsumersNum());
			System.out.println("message number:" + qi.getpMessageNum());
			System.out.println("dequeued number:" + qi.getmDequeuedNum());
			System.out.println("enqueued number:" + qi.getmEnqueuedNum());
			System.out.println("############end###########");
			System.out.println("");
			
		}*/
		
//		qjo.deleteQueue("com.xiaoniu.job.execute.failed.alarm");
	}
}
