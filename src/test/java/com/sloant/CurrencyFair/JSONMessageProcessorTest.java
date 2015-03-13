package com.sloant.CurrencyFair;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;

import junit.framework.TestCase;

public class JSONMessageProcessorTest extends TestCase {

	public void testProcessMessage() throws Exception {
		String json = "{\"userId\": \"134256\", \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"10-FEB-15 10:27:44\", \"originatingCountry\" : \"FR\"}";
		
		JSONMessageProcessor processor = new JSONMessageProcessor();
		Message cfMessage = processor.processMessage(json);
		
		if (cfMessage instanceof TradeMessage) {
			TradeMessage message = (TradeMessage)cfMessage;
						
			assertTrue("User ids not equal", message.getUserId().equals("134256"));	
			assertTrue("Currency from not equal", message.getCurrencyFrom().equals("EUR"));
			assertTrue("Currency to not equal", message.getCurrencyTo().equals("GBP"));
			assertTrue("Amount buy not equal", message.getAmountBuy() == 747.10);
			assertTrue("Amount sell not equal", message.getAmountSell() == 1000);
			assertTrue("Rate not equal", message.getRate() == 0.7471);
			DateFormat format = new SimpleDateFormat("dd-MMM-yy hh:mm:ss", Locale.ENGLISH);
			Date timePlaced = null;
			try {
				timePlaced = format.parse("10-FEB-15 10:27:44");
			} catch (JSONException | ParseException e) {
				e.printStackTrace();
				fail("Error testing date");
			}
			assertTrue("Time placed not equal", message.getTimePlaced().equals(timePlaced));
			assertTrue("Originating country not equal", message.getOriginatingCountry().equals("FR"));		
			
		}
		else {
			fail("Unsupported message type returned");
		}
		
	}
}
