package com.sloant.CurrencyFair;

import java.util.Date;

import com.sloant.CurrencyFair.TradeMessage.TradeMessageBuilder;

/**
 * Class that just creates random test data, regardless of what input string it receives. Used for populating database when I want to avoid having to test parsing json as well
 */
public class MockProcessingService implements ProcessingService{

	@Override
	public Message processMessage(String message) {
		
		TradeMessage tradeMessage = new TradeMessageBuilder("test"+System.currentTimeMillis()).currencyFrom("Test").currencyTo("Test").amountSell(45).amountBuy(45).rate(0.7).date(new Date()).originatingCountry("FRA").createTradeMessage();
		
		return tradeMessage;		
		
	}

}
