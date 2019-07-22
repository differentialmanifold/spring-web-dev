package com.example.platform.module.common.extend.amq.queue;

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
public class QueueMessageProducer implements IProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(QueueMessageProducer.class);
	
	private ActiveMQConnectionFactory activeMQConnectionFactory;
	
	/**
	 * Default priority
	 */
	private static final Integer DEFAULT_PRIORITY = 4;
	
	public QueueMessageProducer(ActiveMQConnectionFactory activeMQConnectionFactory) {
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
	 * Single message for producer
	 * @param qMessage
	 * @param priority
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

			Queue queue = session.createQueue(qMessage.getDestination());
			
			producer = session.createProducer(queue);
			
			//Set a priority
			producer.setPriority(priority);
			
			producer.setDeliveryMode(qMessage.isPersistent() ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);

			TextMessage message = session.createTextMessage(qMessage.getContent());
			
			logger.info("######Producer - CONTENT:" + qMessage.getContent()
						+ ", TIME:" + qMessage.getTime());
			
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
	 * Multiple messages for producer
	 * @param qMessageList    message list
	 * @param isTranscation
	 * @param destincation    queue name
	 * @param isPersistent
	 * @throws Exception
	 */
	@Override
	public void sendMessage(List<QMessage> qMessageList, boolean isTransaction, 
					String destincation, boolean isPersistent) throws JMSException {
		Connection conn = null;
		Session session = null;
		
		try {
			conn = activeMQConnectionFactory.createQueueConnection();
			conn.start();
			
			session = conn.createSession(isTransaction, ActiveMQSession.AUTO_ACKNOWLEDGE);
			
			Queue queue = session.createQueue(destincation);

			MessageProducer producer = session.createProducer(queue);
			
			producer.setDeliveryMode(isPersistent ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
			
			for (QMessage qMessage : qMessageList) {
				
				TextMessage message = session.createTextMessage(qMessage.getContent());
				
				logger.info("######Producer - CONTENT:" + qMessage.getContent()
							+ ", TIME:" + qMessage.getTime());
				
				producer.send(message);
				
				logger.info("######Message send success.");
			}
			
			if (isTransaction) {
				session.commit();
			}
			
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
