package com.example.platform.module.common.extend.amq.dev;

import com.example.platform.module.common.extend.amq.jmx.QueueJmxOperate;
import org.apache.activemq.broker.jmx.QueueViewMBean;

import javax.management.MalformedObjectNameException;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.OpenDataException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class DevQueueJmxOperate extends QueueJmxOperate {

	public DevQueueJmxOperate(String url) throws IOException, MalformedObjectNameException {
		super(url);
	}
	
	public DevQueueJmxOperate(String url, String user, String password, String brokerName) throws IOException, MalformedObjectNameException {
		super(url, user, password, brokerName);
	}
	
	
	/**
	 * Get AmqMessage list
	 * 
	 * @param queueName
	 * @return
	 * @throws OpenDataException
	 */
	public List<String> browseMessages(String queueName) throws OpenDataException {
		
		List<String> messageList = new LinkedList<String>();
		
		QueueViewMBean queueMbean = getQueueMbean(queueName);
		CompositeData[] queueMessages = queueMbean.browse();

		String content = null;
		String messageID = null;
		Integer priority = 0;

		StringBuilder sb = null;
		
		for (CompositeData queueMessage : queueMessages) {
//			System.out.println("##################" + queueMessage);
			sb = new StringBuilder();

			if (queueMessage.containsKey("JMSMessageID")) {
				messageID = (String) queueMessage.get("JMSMessageID");
			}
              
			if (queueMessage.containsKey("Text")) {  
                content = (String) queueMessage.get("Text");  
            }
			
			if (queueMessage.containsKey("JMSPriority")) {
				priority = (Integer)queueMessage.get("JMSPriority");
			}

			sb.append("JMSMessageID:").append(messageID).append(", ")
					.append("Text:").append(content).append(", ")
					.append("JMSPriority:").append(priority).append("\n");
			
			messageList.add(sb.toString());
		}
		
		return messageList;
	}
	
	/**
	 * Get queue message size
	 * 
	 * @param queueName
	 * @return
	 * @throws OpenDataException
	 */
	public int queueMessageSize(String queueName) throws OpenDataException {
		QueueViewMBean queueMbean = getQueueMbean(queueName);
		CompositeData[] queueMessages = queueMbean.browse();
		
		return queueMessages.length;
	}
	
	
	public static void main(String[] args) throws Exception {
		DevQueueJmxOperate qjo = new DevQueueJmxOperate("service:jmx:rmi:///jndi/rmi://10.0.0.7:11099/jmxrmi");

		int messageLength = qjo.queueMessageSize("Jaycekon-MQ");

		System.out.println("###### message length is " + messageLength);

		List<String> qi = qjo.browseMessages("Jaycekon-MQ");

		int total = 10;

		int index = 0;

		if (qi != null) {
			System.out.println("######" + qi.size());

			for (String message : qi) {
				index++;
				if (index > total) {
					break;
				}
				System.out.println("###" + message);
			}
		}
		QueueViewMBean queueMbean = qjo.getQueueMbean("Jaycekon-MQ");
		System.out.println(queueMbean.getQueueSize());
		
	}
}
