/**
 * 
 */
package com.app.stockmarket.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.domain.TradeTransaction;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;

/**
 * @author sramanna
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

	/* (non-Javadoc)
	 * @see com.app.stockmarket.service.IStockDataService#listStockSymbols()
	 */
	@Override
	public Set<String> listStockSymbols() {
		return stocks.keySet();
	}
	
	/* (non-Javadoc)
	 * @see com.app.stockmarket.service.IStockDataService#listStockSymbols()
	 */
	@Override
	public Set<Stock> listAllStocks() {
		
		Set<Stock> allStocks = new HashSet<Stock>(stocks.values());
		
		return allStocks;
	}

	/* (non-Javadoc)
	 * @see com.app.stockmarket.service.IStockDataService#saveStockData(com.app.stockmarket.domain.Stock)
	 */
	@Override
	public boolean saveStockData(Stock stock) throws InvalidStockException {
		
		stocks.put(stock.getSymbol(), stock);
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.app.stockmarket.service.IStockDataService#getStockData(com.app.stockmarket.domain.Stock)
	 */
	@Override
	public Stock getStockData(String stockSymbol) throws InvalidStockException {
		
		if(!stocks.containsKey(stockSymbol))
			throw new InvalidStockException(InvalidStockException.ErrorCode.INVALID_STOCK_SYMBOL);
		
		return stocks.get(stockSymbol);
	}

	@Override
	public boolean recordTradeTransation(TradeTransaction tradeTransaction) throws InvalidStockException {
		validate(tradeTransaction);
		tradeTransactions.add(tradeTransaction);
		return true;
	}

	private void validate(TradeTransaction tradeTransaction)  throws InvalidStockException  {
		if(!stocks.containsKey(tradeTransaction.getStockSymbol()))
			throw new InvalidStockException(InvalidStockException.ErrorCode.INVALID_STOCK_SYMBOL);
		
		if(tradeTransaction.getQuantity() <= 0)
			throw new InvalidStockException(InvalidStockException.ErrorCode.INVALID_STOCK_QTY);
		
		if(tradeTransaction.getTradedPrice() <= 0.0)
			throw new InvalidStockException(InvalidStockException.ErrorCode.INVALID_STOCK_PRICE);
		
		return;
		
	}

	@Override
	public List<TradeTransaction> getTransactionRecordsByDuration(String stockSymbol, Date currentTime, int minutes) {
		List<TradeTransaction> transactionList = new ArrayList<TradeTransaction> ();
		
		Iterator<TradeTransaction> tradeIterator = tradeTransactions.descendingIterator();
		
		long cuttOffMilliSeconds = minutes * 60 *1000;
		while(tradeIterator.hasNext()) {
			TradeTransaction tradeTransaction = tradeIterator.next();
			long diff = (currentTime.getTime() - tradeIterator.next().getTimestamp().getTime());
			
			if(diff <= cuttOffMilliSeconds && stockSymbol.equals(tradeTransaction.getStockSymbol())) {
				transactionList.add(tradeTransaction);
			}
		}
				
		return transactionList;	
	}

	@Override
	public List<TradeTransaction> getAllTransactionRecords() {
		
		List<TradeTransaction> transactionList = new ArrayList<TradeTransaction> ();
		
		transactionList.addAll(tradeTransactions);
		
		return transactionList;
	}
}
