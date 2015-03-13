package com.sloant.CurrencyFair;

import org.bson.Document;

import com.sloant.CurrencyFair.TradeMessage.TradeMessageBuilder;

public class DocumentConverter {

	/**
	 * Convert a trade message to a document for Mongo
	 * @param message
	 * @return
	 */
	public static Document convertTradeMessageToDocument(TradeMessage message) {
		Document doc = new Document(TradeMessage.USER_ID_DB,
				message.getUserId())
				.append(TradeMessage.CURRENCY_FROM_DB,
						message.getCurrencyFrom())
				.append(TradeMessage.CURRENCY_TO_DB, message.getCurrencyTo())
				.append(TradeMessage.AMOUNT_BUY_DB, message.getAmountBuy())
				.append(TradeMessage.AMOUNT_SELL_DB, message.getAmountSell())
				.append(TradeMessage.RATE_DB, message.getRate())
				.append(TradeMessage.TIME_PLACED_DB, message.getTimePlaced())
				.append(TradeMessage.ORIGINATING_COUNTRY_DB,
						message.getOriginatingCountry());

		return doc;
	}

	/**
	 * Convert a mongo Document to a TradeMessage
	 * @param doc
	 * @return
	 */
	public static TradeMessage convertDocumentToTradeMessage(Document doc) {

		TradeMessage message = new TradeMessageBuilder(
				doc.getString(TradeMessage.USER_ID_DB))
				.currencyFrom(doc.getString(TradeMessage.CURRENCY_FROM_DB))
				.currencyTo(doc.getString(TradeMessage.CURRENCY_TO_DB))
				.amountBuy(doc.getDouble(TradeMessage.AMOUNT_BUY_DB))
				.amountSell(doc.getDouble(TradeMessage.AMOUNT_SELL_DB))
				.rate(doc.getDouble(TradeMessage.RATE_DB))
				.date(doc.getDate(TradeMessage.TIME_PLACED_DB))
				.originatingCountry(
						doc.getString(TradeMessage.ORIGINATING_COUNTRY_DB))
				.createTradeMessage();
		return message;
	}
}
