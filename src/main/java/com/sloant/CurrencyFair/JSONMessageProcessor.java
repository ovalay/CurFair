package com.sloant.CurrencyFair;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.sloant.CurrencyFair.TradeMessage.TradeMessageBuilder;



public class JSONMessageProcessor implements ProcessingService{

	@Override
	public Message processMessage(String message) {
		JSONObject obj = new JSONObject(message);
		String userId = (String) obj.get("userId");
		String currencyFrom = (String)obj.get("currencyFrom");
		String currencyTo = (String)obj.get("currencyTo");
		double amountSell = (double)obj.getDouble("amountSell");
		double amountBuy = (double)obj.getDouble("amountBuy");
		double rate = (double)obj.getDouble("rate");
		DateFormat format = new SimpleDateFormat("dd-MMM-yy hh:mm:ss", Locale.ENGLISH);
		Date timePlaced = null;
		try {
			timePlaced = format.parse(obj.getString("timePlaced"));
		} catch (JSONException | ParseException e) {
			e.printStackTrace();
		}
		String originatingCountry = (String)obj.get("originatingCountry");
		
		Message tradeMessage = new TradeMessageBuilder(userId).currencyFrom(currencyFrom).currencyTo(currencyTo).amountSell(amountSell).amountBuy(amountBuy).rate(rate).date(timePlaced).originatingCountry(originatingCountry).createTradeMessage();
		
		return tradeMessage;
	}

}
