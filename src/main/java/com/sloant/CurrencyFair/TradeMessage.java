package com.sloant.CurrencyFair;

import java.util.Date;

/**
 * Class representing a TradeMessage received
 *
 */
public class TradeMessage implements Message{

	//class member variables
	private final String userId, currencyFrom, currencyTo, originatingCountry;
	private final double amountSell, amountBuy, rate;
	private final Date timePlaced;
	
	//constants to be used for db field names
	public static final String USER_ID_DB = "userId";
	public static final String CURRENCY_FROM_DB = "currencyFrom";
	public static final String CURRENCY_TO_DB = "currencyTo";
	public static final String ORIGINATING_COUNTRY_DB = "originatingCountry";
	public static final String AMOUNT_SELL_DB = "amountSell";
	public static final String AMOUNT_BUY_DB = "amountBuy";
	public static final String RATE_DB = "rate";
	public static final String TIME_PLACED_DB = "timePlaced";
	
	//used by builder
	private TradeMessage(String userId, String currencyFrom, String currencyTo,
			String originatingCountry, double amountSell, double amountBuy,
			double rate, Date timePlaced) {
		
		this.userId = userId;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.originatingCountry = originatingCountry;
		this.amountSell = amountSell;
		this.amountBuy = amountBuy;
		this.rate = rate;
		this.timePlaced = timePlaced;
	}


	public String getUserId() {
		return userId;
	}


	public String getCurrencyFrom() {
		return currencyFrom;
	}


	public String getCurrencyTo() {
		return currencyTo;
	}


	public String getOriginatingCountry() {
		return originatingCountry;
	}


	public double getAmountSell() {
		return amountSell;
	}


	public double getAmountBuy() {
		return amountBuy;
	}


	public double getRate() {
		return rate;
	}


	public Date getTimePlaced() {
		return timePlaced;
	}
	
	/**
	 * Builder class for TradeMessage
	 *
	 */
	public static class TradeMessageBuilder {

		private String nestedUserId, nestedCurrencyFrom, nestedCurrencyTo, nestedOriginatingCountry;
		private double nestedAmountSell, nestedAmountBuy, nestedRate;
		private Date nestedTimePlaced;
		
		public TradeMessageBuilder (String userId) {
			this.nestedUserId = userId;
		}
		
		public TradeMessageBuilder currencyFrom(String currencyFrom) {
			this.nestedCurrencyFrom = currencyFrom;
			return this;
		}
		
		public TradeMessageBuilder currencyTo(String currencyTo) {
			this.nestedCurrencyTo = currencyTo;
			return  this;
		}
		
		public TradeMessageBuilder originatingCountry(String originatingCountry) {
			this.nestedOriginatingCountry = originatingCountry;
			return this;
		}
		
		public TradeMessageBuilder amountSell(double amountSell) {
			this.nestedAmountSell = amountSell;
			return this;
		}
		
		public TradeMessageBuilder amountBuy(double amountBuy) {
			this.nestedAmountBuy = amountBuy;
			return this;
		}
		
		public TradeMessageBuilder rate(double rate) {
			this.nestedRate = rate;
			return this;
		}
		
		public TradeMessageBuilder date(Date timePlaced) {
			this.nestedTimePlaced = timePlaced;
			return this;
		}
		
		/**
		 * Create new TradeMessage object
		 * @return
		 */
		public TradeMessage createTradeMessage() {
			return new TradeMessage(nestedUserId, nestedCurrencyFrom, nestedCurrencyTo, nestedOriginatingCountry, nestedAmountSell, nestedAmountBuy, nestedRate, nestedTimePlaced);
		}
		
	}
	
}
