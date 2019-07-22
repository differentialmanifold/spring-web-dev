package com.example.platform.module.common.extend.amq.topic;

import com.example.platform.module.common.extend.amq.IProducer;
import com.example.platform.module.common.extend.amq.entity.QMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.List;


/**
 */
public class TopicMessagePublisher implements IProducer {

	private static final Logger logger = LoggerFactory.getLogger(TopicMessagePublisher.class);
	
	private ActiveMQConnectionFactory activeMQConnectionFactory;
	
	/**
	 * Default priority
	 */
	private static final Integer DEFAULT_PRIORITY = 4;
	
	public TopicMessagePublisher(ActiveMQConnectionFactory activeMQConnectionFactory) {
		this.activeMQConnectionFactory = activeMQConnectionFactory;
	}
	
	
	/**
	 * Single message for producer, use default priority
	 * @param qMessage
	 * @throws Exception
	 */
	@Override
	public void sendMessage(QMessage qMessage) throws Exception {
		sendMessage(qMessage, DEFAULT_PRIORITY);
	}
	
	
	/**
	 * Single message for publisher
	 * @param qMessage
	 * @throws Exception
	 */
	@Override
	public void sendMessage(QMessage qMessage, int priority) throws Exception {
		Connection conn = null;
		Session session = null;
		MessageProducer producer = null;
		
		try {
			conn = activeMQConnectionFactory.createQueueConnection();
			conn.start();
			
			session = conn.createSession(qMessage.isTransaction(), ActiveMQSession.AUTO_ACKNOWLEDGE);
			
			Topic topic = session.createTopic(qMessage.getDestination());

			producer = session.createProducer(topic);
			
			//Set a priority
			producer.setPriority(priority);

			producer.setDeliveryMode(qMessage.isPersistent() ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);

			TextMessage message = session.createTextMessage(qMessage.getContent());
			
			logger.info("######Publisher - CONTENT:"+ qMessage.getContent() + ", TIME:" + qMessage.getTime());
			
			producer.send(message);
			
			if (qMessage.isTransaction()) {
				session.commit();
			}
			
			logger.info("######Message send success.");
			
		} catch (Exception e) {
			logger.error("######", e);
			throw e;
		} finally {
			try {
				if (producer != null) {
					producer.close();
				}
				
				if (session != null) {
					session.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ie) {
				logger.error("######Close exception:", ie);
			}
		}
	}

	
	/**
	 * Multiple messages for publisher
	 * @param qMessageList
	 * @param isTranscation
	 * @param destincation    topic name
	 * @param isPersistent
	 * @throws Exception
	 */
	@Override
	public void sendMessage(List<QMessage> qMessageList, boolean isTransaction, String destincation, boolean isPersistent)
			throws Exception {
		Connection conn = null;
		Session session = null;
		
		try {
			conn = activeMQConnectionFactory.createQueueConnection();
			conn.start();
			
			session = conn.createSession(isTransaction, ActiveMQSession.AUTO_ACKNOWLEDGE);
			
			Topic topic = session.createTopic(destincation);
			
			MessageProducer producer = session.createProducer(topic);
			
			producer.setDeliveryMode(isPersistent ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
			
			for (QMessage qMessage : qMessageList) {
				
				TextMessage message = session.createTextMessage(qMessage.getContent());
				
				logger.info("######Publisher - CONTENT:" + qMessage.getContent()
							+ ", TIME:" + qMessage.getTime());
				
				producer.send(message);
			}
			
			if (isTransaction) {
				session.commit();
			}
			
			logger.info("######Message send success.");
			
		} catch (JMSException e) {
			logger.error("######", e);
			throw e;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (JMSException ie) {
				logger.error("######Close exception:", ie);
			}
		}
	}

}
