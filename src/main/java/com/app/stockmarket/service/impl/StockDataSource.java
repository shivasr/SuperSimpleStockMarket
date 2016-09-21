/**
 * 
 */
package com.app.stockmarket.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.domain.TradeTransaction;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;

/**
 * Data Source for the stocks. 
 * 
 * This class is responsible for Create / Update of the stock data
 * 
 * @author Shivakumar Ramannavar
 *
 */
public class StockDataSource implements IStockDataService {

	/**
	 * List of stocks resembling table of Stocks
	 */
	private LinkedHashMap<String, Stock> stocks;

	/**
	 * Trade transactions of the stocks
	 */
	private LinkedList<TradeTransaction> tradeTransactions;

	/**
	 * Constructor for Stock Data Source
	 */
	public StockDataSource() {
		stocks = new LinkedHashMap<String, Stock>();
		tradeTransactions = new LinkedList<TradeTransaction>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.stockmarket.service.IStockDataService#listStockSymbols()
	 */
	@Override
	public List<String> listStockSymbols() {
		List<String> allStocks = new ArrayList<String>(stocks.values().size());

		Iterator<String> stockIterator = stocks.keySet().iterator();

		while (stockIterator.hasNext()) {
			allStocks.add(stockIterator.next());
		}

		return allStocks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.stockmarket.service.IStockDataService#listStockSymbols()
	 */
	@Override
	public List<Stock> listAllStocks() {

		List<Stock> allStocks = new ArrayList<Stock>();

		Iterator<String> stockIterator = stocks.keySet().iterator();

		while (stockIterator.hasNext()) {
			allStocks.add(stocks.get(stockIterator.next()));
		}

		return allStocks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.stockmarket.service.IStockDataService#saveStockData(com.app.
	 * stockmarket.domain.Stock)
	 */
	@Override
	public boolean saveStockData(Stock stock) throws InvalidStockException {

		stocks.put(stock.getSymbol(), stock);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.stockmarket.service.IStockDataService#getStockData(com.app.
	 * stockmarket.domain.Stock)
	 */
	@Override
	public Stock getStockData(String stockSymbol) throws InvalidStockException {

		if (!stocks.containsKey(stockSymbol))
			throw new InvalidStockException(InvalidStockException.ErrorCode.INVALID_STOCK_SYMBOL);

		return stocks.get(stockSymbol);
	}

	@Override
	public boolean recordTradeTransation(TradeTransaction tradeTransaction) throws InvalidStockException {
		validate(tradeTransaction);
		tradeTransactions.add(tradeTransaction);
		return true;
	}

	private void validate(TradeTransaction tradeTransaction) throws InvalidStockException {
		if (!stocks.containsKey(tradeTransaction.getStockSymbol()))
			throw new InvalidStockException(InvalidStockException.ErrorCode.INVALID_STOCK_SYMBOL);

		if (tradeTransaction.getQuantity() <= 0)
			throw new InvalidStockException(InvalidStockException.ErrorCode.INVALID_STOCK_QTY);

		if (tradeTransaction.getTradedPrice() <= 0.0)
			throw new InvalidStockException(InvalidStockException.ErrorCode.INVALID_STOCK_PRICE);

		return;

	}

	@Override
	public List<TradeTransaction> getTransactionRecordsByDuration(String stockSymbol, Date currentTime, int minutes) {
		List<TradeTransaction> transactionList = new ArrayList<TradeTransaction>();

		Date now = currentTime;
		
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Iterator<TradeTransaction> tradeIterator = tradeTransactions.descendingIterator();
		
		Calendar prev = Calendar.getInstance();
		prev.setTime(now);
				
		prev.add(Calendar.MINUTE, - minutes);
		prev.add(Calendar.SECOND, -1);
		
		while (tradeIterator.hasNext()) {
			TradeTransaction tradeTransaction = tradeIterator.next();
			
			if (tradeTransaction.getTimestamp().after(prev.getTime()) 
					&& stockSymbol.equals(tradeTransaction.getStockSymbol())) {
				System.out.println(tradeTransaction);
				transactionList.add(tradeTransaction);
			}
		}

		return transactionList;
	}

	@Override
	public List<TradeTransaction> getAllTransactionRecords() {

		List<TradeTransaction> transactionList = new ArrayList<TradeTransaction>();

		transactionList.addAll(tradeTransactions);

		return transactionList;
	}
	
	 private Date trim(Date date) {

	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.HOUR, 0);
	        return calendar.getTime();
	    }
}
