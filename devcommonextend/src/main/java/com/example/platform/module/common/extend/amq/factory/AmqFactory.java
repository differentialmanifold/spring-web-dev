package com.example.platform.module.common.extend.amq.factory;

import com.example.platform.module.common.extend.amq.IConsumer;
import com.example.platform.module.common.extend.amq.IProducer;
import com.example.platform.module.common.extend.amq.entity.ConstantUtil;
import com.example.platform.module.common.extend.amq.queue.QueueMessageConsumer;
import com.example.platform.module.common.extend.amq.queue.QueueMessageProducer;
import com.example.platform.module.common.extend.amq.topic.TopicMessagePublisher;
import com.example.platform.module.common.extend.amq.topic.TopicMessageSubscriber;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Activemq factory
 */
public class AmqFactory {

    private volatile static AmqFactory instance = null;

    private ActiveMQConnectionFactory activeMQConnectionFactory;

    private AmqFactory(String brokerUrl, String username, String password) {
        activeMQConnectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
    }

    public static AmqFactory getInstance(String brokerUrl) {
        return getInstance(brokerUrl, ConstantUtil.MQ_DEFAULT_USER, ConstantUtil.MQ_DEFAULT_PASS);
    }

    public static AmqFactory getInstance(String brokerUrl, String username, String password) {
        if (instance == null) {
            synchronized (AmqFactory.class) {
                if (instance == null) {
                    instance = new AmqFactory(brokerUrl, username, password);
                }
            }
        }
        return instance;
    }

    /**
     * Create activemq queue producer
     *
     * @return
     */
    public IProducer createQueueAmqProducer() {
        return new QueueMessageProducer(activeMQConnectionFactory);
    }

    /**
     * Create activemq topic publisher
     *
     * @return
     */
    public IProducer createTopicAmqPublisher() {
        return new TopicMessagePublisher(activeMQConnectionFactory);
    }

    /**
     * Create activemq queue consumer
     *
     * @return
     */
    public IConsumer createQueueAmqConsumer() {
        return new QueueMessageConsumer(activeMQConnectionFactory);
    }

    /**
     * Create activemq topic subscriber
     *
     * @return
     */
    public IConsumer createTopicAmqSubscriber() {
        return new TopicMessageSubscriber(activeMQConnectionFactory);
    }

    /**
     * Create QueueAmqInfo
     * @return
     */
    /*public QueueAmqInfo createQueueAmqInfo() {
        return new QueueAmqInfo(activeMQConnectionFactory);
	}*/

	/*public static void main(String[] args) {
		QueueAmqInfo qai = AmqFactory.getInstance("tcp://10.17.2.134:61616").createQueueAmqInfo();
		
		List<String> queueNames = qai.getQueueNames();
		int size = qai.getSize();
		
		for (String queue : queueNames) {
			System.out.println("###0.0###" + queue);
		}
		
		System.out.println("###=.=###" + size);
		
		qai.close();
	}*/

}
