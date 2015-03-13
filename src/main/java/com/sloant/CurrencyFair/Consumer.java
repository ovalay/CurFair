package com.sloant.CurrencyFair;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Class for trade message consumption from the activemq queue 
 *
 */
public class Consumer 
{

	private String connectionUri, queueName;
	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageConsumer consumer;

	//Load app properties
	public void loadProps() {
		Properties serverProps = new Properties();
		try {
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(("server.properties"));
			serverProps.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		connectionUri = serverProps.getProperty("server.uri");
		queueName = serverProps.getProperty("queue.name");
		System.out.println("Properties loaded OK");
	}

	/**
	 * Setup connection to queue
	 * @throws JMSException
	 */
	public void setUp() throws JMSException {
		connectionFactory = new ActiveMQConnectionFactory(connectionUri);
		connection = connectionFactory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue(queueName);
	}

	public void shutDown() throws JMSException {
		if (connection != null) {
			connection.close();
		}
	}

	/**
	 * Set the listener to be used for processing messages and start listening
	 * @throws JMSException
	 */
	public void process() throws JMSException {
		consumer = session.createConsumer(destination);
		consumer.setMessageListener(new TradeMessageListener());		
	}
	
	
	public static void main( String[] args )
	{		
		Consumer messageConsumer = new Consumer();
		messageConsumer.loadProps();
		try {
			messageConsumer.setUp();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		try {
			messageConsumer.process();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
