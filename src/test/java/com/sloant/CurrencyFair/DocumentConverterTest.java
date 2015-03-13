package com.sloant.CurrencyFair;

import java.util.Date;

import org.bson.Document;

import com.sloant.CurrencyFair.TradeMessage.TradeMessageBuilder;

import junit.framework.TestCase;

public class DocumentConverterTest extends TestCase {

	public void testConvertTradeMessageToDocument() throws Exception {
		
		Date d = new Date();
		
		TradeMessage message = new TradeMessageBuilder("testUser")
				.currencyFrom("testFrom")
				.currencyTo("testTo")
				.amountBuy(75.365)
				.amountSell(55.44)
				.rate(1.44)
				.date(d)
				.originatingCountry("FRA").createTradeMessage();
		
		Document doc = DocumentConverter.convertTradeMessageToDocument(message);
		
		//quick test, no edge cases 
		
		assertTrue("User ids not equal", message.getUserId() == doc.getString(TradeMessage.USER_ID_DB));	
		assertTrue("Currency from not equal", message.getCurrencyFrom() == doc.getString(TradeMessage.CURRENCY_FROM_DB));
		assertTrue("Currency to not equal", message.getCurrencyTo() == doc.getString(TradeMessage.CURRENCY_TO_DB));
		assertTrue("Amount buy not equal", message.getAmountBuy() == doc.getDouble(TradeMessage.AMOUNT_BUY_DB));
		assertTrue("Amount sell not equal", message.getAmountSell() == doc.getDouble(TradeMessage.AMOUNT_SELL_DB));
		assertTrue("Rate not equal", message.getRate() == doc.getDouble(TradeMessage.RATE_DB));
		assertTrue("Time placed not equal", message.getTimePlaced() == doc.getDate(TradeMessage.TIME_PLACED_DB));
		assertTrue("Originating country not equal", message.getOriginatingCountry() == doc.getString(TradeMessage.ORIGINATING_COUNTRY_DB));		
	}

	public void testConvertDocumentToTradeMessage() throws Exception {

		Date d = new Date();
		
		Document doc = new Document();
		doc.append(TradeMessage.USER_ID_DB,
		"testUser")
		.append(TradeMessage.CURRENCY_FROM_DB,
				"testFrom")
		.append(TradeMessage.CURRENCY_TO_DB, "testTo")
		.append(TradeMessage.AMOUNT_BUY_DB, 75.365)
		.append(TradeMessage.AMOUNT_SELL_DB, 55.44)
		.append(TradeMessage.RATE_DB, 1.44)
		.append(TradeMessage.TIME_PLACED_DB, d)
		.append(TradeMessage.ORIGINATING_COUNTRY_DB,"FRA");
		
		TradeMessage message = DocumentConverter.convertDocumentToTradeMessage(doc);
		
		//quick test, no edge cases 
		
		assertTrue("User ids not equal", message.getUserId() == doc.getString(TradeMessage.USER_ID_DB));
		assertTrue("Currency from not equal", message.getCurrencyFrom() == doc.getString(TradeMessage.CURRENCY_FROM_DB));
		assertTrue("Currency to not equal", message.getCurrencyTo() == doc.getString(TradeMessage.CURRENCY_TO_DB));
		assertTrue("Amount buy not equal", message.getAmountBuy() == doc.getDouble(TradeMessage.AMOUNT_BUY_DB));
		assertTrue("Amount sell not equal", message.getAmountSell() == doc.getDouble(TradeMessage.AMOUNT_SELL_DB));
		assertTrue("Rate not equal", message.getRate() == doc.getDouble(TradeMessage.RATE_DB));
		assertTrue("Time placed not equal", message.getTimePlaced() == doc.getDate(TradeMessage.TIME_PLACED_DB));
		assertTrue("Originating country not equal", message.getOriginatingCountry() == doc.getString(TradeMessage.ORIGINATING_COUNTRY_DB));
	}
}
