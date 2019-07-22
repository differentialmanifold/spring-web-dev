package com.example.platform.module.common.extend.amq.jmx;

import com.example.platform.module.common.extend.amq.entity.ConstantUtil;
import org.apache.activemq.broker.jmx.BrokerViewMBean;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 */
public class JmxOperate {
	
	private String url;
	private String user;
	private String password;
	private String brokerName;
	
	private JMXConnector connector = null;
	
	private BrokerViewMBean mbean = null;
	
	private MBeanServerConnection connection = null;
	
	public JmxOperate(String url) throws IOException, MalformedObjectNameException {
		this(url, ConstantUtil.MQ_JMX_DEFAULT_USER, ConstantUtil.MQ_JMX_DEFAULT_PASS, ConstantUtil.MQ_DEFAULT_BROKER_NAME);
	}
	
	public JmxOperate(String url, String user, String password, String brokerName) throws IOException, MalformedObjectNameException {
		this.url = url;
		this.user = user;
		this.password = password;
		this.brokerName = brokerName;
		
		init();
	}
	
	private void init() throws IOException, MalformedObjectNameException {
		
		Map<String, Object> map = new HashMap<String, Object>();

		String[] credentials = new String[2];
		credentials[0] = user;
		credentials[1] = password;
		
		map.put(JMXConnector.CREDENTIALS, credentials);
		
		JMXServiceURL jMXServiceURL = new JMXServiceURL(url);
		
		connector = JMXConnectorFactory.connect(jMXServiceURL, map);
		
		connector.connect();
		
		connection = connector.getMBeanServerConnection();
		
		ObjectName name = new ObjectName("org.apache.activemq:type=Broker,brokerName=" + brokerName);
		
		mbean = (BrokerViewMBean) MBeanServerInvocationHandler
				.newProxyInstance(connection, name, BrokerViewMBean.class, true);
		
	}
	
	public BrokerViewMBean getMbean() {
		return mbean;
	}

	public MBeanServerConnection getConnection() {
		return connection;
	}

	public JMXConnector getConnector() {
		return connector;
	}
	
}
