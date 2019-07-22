package com.example.platform.module.common.extend.amq.queue;

import com.example.platform.module.common.extend.amq.IConsumer;
import com.example.platform.module.common.extend.amq.entity.QMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 */
public class QueueMessageConsumer implements IConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(QueueMessageConsumer.class);
	
	private ActiveMQConnectionFactory activeMQConnectionFactory;
	
	/**
	 * If the connection support transaction
	 */
	private static final boolean IS_TRANSACTION = false;
	
	/**
	 * Receive time out, default 10s
	 */
	private static final long TIMEOUT = 10000;
	
	public QueueMessageConsumer(ActiveMQConnectionFactory activeMQConnectionFactory) {
		this.activeMQConnectionFactory = activeMQConnectionFactory;
	}

	@Override
	public QMessage receiveMessageSync(String destination) throws Exception {
		return receiveMessageSync(destination, IS_TRANSACTION, TIMEOUT);
	}


	@Override
	public QMessage receiveMessageSync(String destination, boolean isTransaction) throws Exception {
		return receiveMessageSync(destination, isTransaction, TIMEOUT);
	}

	
	@Override
	public QMessage receiveMessageSync(String destination, long timeout) throws Exception {
		return receiveMessageSync(destination, IS_TRANSACTION, timeout);
	}


	/**
	 * Receive message for consumer
	 * @param destination    queue name
	 * @param isTransaction
	 * @param timeout        unit millisecond
	 * @throws Exception
	 */
	@Override
	public QMessage receiveMessageSync(String destination, boolean isTransaction, long timeout) throws Exception {
		Connection conn = null;
		Session session = null;
		MessageConsumer consumer = null;
		QMessage qMessage = null;
		
		try {
			conn = activeMQConnectionFactory.createQueueConnection();
			conn.start();
			
			session = conn.createSession(isTransaction, Session.AUTO_ACKNOWLEDGE);

			Queue queue = session.createQueue(destination);

			consumer = session.createConsumer(queue);
			
			Message msg = consumer.receive(timeout);
			
			if (msg == null) {
				return null;
			}
			
			if (msg instanceof TextMessage) {
				qMessage = new QMessage();
				
				qMessage.setContent(((TextMessage)msg).getText());
			
				logger.info("######Consumer - CONTENT:" + qMessage.getContent());
			}
			
			if (isTransaction) {
				session.commit();
			}
			
			logger.info("######Message receive success.");
			
			return qMessage;
			
		} catch (Exception e) {
			logger.error("######", e);
			throw e;
		} finally {
			try {
				if (consumer != null) {
					consumer.close();
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

}
