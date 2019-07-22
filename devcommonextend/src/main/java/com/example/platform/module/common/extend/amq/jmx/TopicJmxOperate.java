package com.example.platform.module.common.extend.amq.jmx;

import com.example.platform.module.common.extend.amq.entity.TopicInfo;
import com.example.platform.module.common.extend.amq.util.IOUtil;
import org.apache.activemq.broker.jmx.TopicViewMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.io.IOException;

/**
 * ActiveMQ Topic operation through JMX
 *
 */
public class TopicJmxOperate extends JmxOperate {
	
	private static final Logger logger = LoggerFactory.getLogger(TopicJmxOperate.class);
	
	public TopicJmxOperate(String url) throws MalformedObjectNameException, IOException {
		super(url);
	}
	
	public TopicJmxOperate(String url, String user, String password, String brokerName) throws MalformedObjectNameException, IOException {
		super(url, user, password, brokerName);
	}
	
	
	/**
	 * Get ActiveMQ all topics
	 * @return
	 */
	public ObjectName[] getTopics() {
		return getMbean().getTopics();
	}
	
	
	/**
	 * Get ActiveMQ topic size
	 * @return
	 */
	public long getTopicNums() {
		return getTopics().length;
	}

	
	/**
	 * @param topic
	 * @return
	 */
	public TopicViewMBean getTopicMbean(ObjectName topic) {
		return (TopicViewMBean)MBeanServerInvocationHandler
				.newProxyInstance(getConnection(), topic, TopicViewMBean.class, true);
	}
	
	
	/**
	 * Get TopicViewMBean by topic name
	 * etc. topic.fortest
	 * 
	 * @param topicName
	 * @return
	 */
	public TopicViewMBean getTopicMbean(String topicName) {
		ObjectName topic = getTopicObjectName(topicName);
		return getTopicMbean(topic);
	}
	
	
	/**
	 * Get topic ObjectName by topic name string
	 * 
	 * etc.
	 * topic.fortest  ->
	 * org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Topic,destinationName=topic.fortest
	 * 
	 * @param topicName
	 * @return
	 */
	public ObjectName getTopicObjectName(String topicName) {
		ObjectName[] topics = getTopics();
		
		for (ObjectName topic : topics) {
			TopicViewMBean topicMbean = getTopicMbean(topic);
			
			if (topicMbean.getName().equals(topicName)) {
				return topic;
			}
		}
		
		return null;
	}
	
	
	/**
	 * Get TopicInfo
	 * @param topic
	 * @return
	 */
	public TopicInfo getTopicInfo(ObjectName topic) {
		TopicViewMBean topicMbean = getTopicMbean(topic);
		return boxTopicInfo(topicMbean);
	}
	
	
	/**
	 * Get TopicInfo
	 * @param topicName
	 * @return
	 */
	public TopicInfo getTopicInfo(String topicName) {
		TopicViewMBean topicMbean = getTopicMbean(topicName);
		return boxTopicInfo(topicMbean);
	}
	
	
	/**
	 * @param topicMbean
	 * @return
	 */
	public TopicInfo boxTopicInfo(TopicViewMBean topicMbean) {
		TopicInfo topicInfo = new TopicInfo();
		
		topicInfo.setTopicName(topicMbean.getName());
		topicInfo.setConsumersNum(topicMbean.getConsumerCount());
		topicInfo.setmEnqueuedNum(topicMbean.getEnqueueCount());
		topicInfo.setmDequeuedNum(topicMbean.getDequeueCount());
		
		return topicInfo;
	}
	
	
	/**
	 * Delete the topic
	 * @param topicName
	 * @throws Exception
	 */
	public void deleteTopic(String topicName) throws Exception {
		getMbean().removeTopic(topicName);
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
	
	
	public static void main(String[] args) throws MalformedObjectNameException, IOException {
		TopicJmxOperate qjo = new TopicJmxOperate("service:jmx:rmi:///jndi/rmi://10.17.2.134:11099/jmxrmi");
		
		qjo.getTopicInfo("scheduler.svn.update");
	}
}
