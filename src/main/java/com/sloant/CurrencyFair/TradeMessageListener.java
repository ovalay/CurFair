package com.sloant.CurrencyFair;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TradeMessageListener implements MessageListener {

	//Used for populating random data in testing
	//ProcessingService ps = new MockProcessingService();
	
	ProcessingService ps = new JSONMessageProcessor();
	
	@Override
	public void onMessage(Message message) {

		try { 
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				com.sloant.CurrencyFair.Message cfMessage = ps.processMessage(text);
				MongoConnector.getInstance().logMessage(cfMessage);
			} else {
				System.out.println("Unknown message type received by TradeMessageListener, ignoring");
			}
		}
		catch (JMSException e) {
			System.err.println("Error processing message");
			e.printStackTrace();
		}
	}

}
