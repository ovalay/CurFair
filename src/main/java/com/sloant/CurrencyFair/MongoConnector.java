package com.sloant.CurrencyFair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoConnector{

	private Properties dbProps;
	private int port;
	private String server, dbName, collectionName;
	MongoClient mongoClient;   //MongoClient is threadsafe
	MongoCollection<Document> collection;
	MongoDatabase db;
	private static MongoConnector INSTANCE = null;


	private MongoConnector () {
		//private
	}

	public synchronized static MongoConnector getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MongoConnector();
			INSTANCE.initialise();
		}
		return INSTANCE;
	}

	private void initialise() {
		dbProps = new Properties();
		try {
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			dbProps.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		server = dbProps.getProperty("server");
		dbName = dbProps.getProperty("db.name");
		port = Integer.parseInt(dbProps.getProperty("port"));
		collectionName = dbProps.getProperty("collection");
		System.out.println("DB Properties loaded OK");

		mongoClient = new MongoClient( server , port );
		db = mongoClient.getDatabase(dbName);
		collection = db.getCollection(collectionName);

		System.out.println("Connection to Database established successfully");
	}

	/**
	 * Close connection to DB
	 */
	public void close() {
		mongoClient.close();
	}

	/**
	 * Insert a single message
	 * @param cfMessage
	 */
	public void logMessage(Message cfMessage) {		
		if (cfMessage instanceof TradeMessage)
			collection.insertOne(DocumentConverter.convertTradeMessageToDocument((TradeMessage)cfMessage));
	}

	/**
	 * Get documents in a range (for pagination)
	 * @param start Starting document
	 * @param limit Number of documents to fetch
	 */
	public List<TradeMessage> fetchMessages(int start, int limit) {
		MongoCursor<Document> cursor = collection.find().skip(start).limit(limit).iterator();
		try {
			List<TradeMessage> results = new ArrayList<TradeMessage>();
			while (cursor.hasNext()) {
				results.add(DocumentConverter.convertDocumentToTradeMessage(cursor.next()));
			}
			return results;
		} finally {
			cursor.close();
		}

	} 

	/**
	 * Count documents in collection
	 * @return
	 */
	public long countDocuments() {
		return collection.count();
	}
	
	/**
	 * Bulk Insert a list of messages
	 * @param messageList
	 */
	public void logMessages(List<TradeMessage> messageList) {
		//do a batch insert of data in mongo
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
