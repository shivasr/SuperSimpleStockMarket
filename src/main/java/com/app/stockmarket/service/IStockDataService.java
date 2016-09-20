package com.app.stockmarket.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.app.stockmarket.domain.Stock;import com.app.stockmarket.domain.TradeTransaction;
import com.app.stockmarket.exception.InvalidStockException;

/**
 * Data Service interface to store Stocks
 * 
 * @author Shivakumar Ramannavar
 *
 */
public interface IStockDataService {

	/**
	 * List All stock symbols in the market
	 * 
	 * @return List of symbols of all stocks in the market
	 */
	public Set<String> listStockSymbols();
	
	
	/**
	 * List All stocks in the market
	 * 
	 * @return List of all stocks in the market
	 */
	public Set<Stock> listAllStocks();
	
	/**
	 * Save Stock Data
	 * 
	 * @param stock Stock object containing the stock details
	 * @return
	 */
	public boolean saveStockData(Stock stock) throws InvalidStockException;
	
	/**
	 * Update stock details
	 * 
	 * @param stock Stock object containing the stock details
	 * 
	 * @return Stock object containing the stock details
	 */
	public Stock getStockData(String stockSymbol) throws InvalidStockException;
	
	/**
	 * Record Trade Transaction
	 * 
	 * @param tradeTransaction TradeTransaction object encapsulating Trade done
	 * @return
	 */
	public boolean recordTradeTransation(TradeTransaction tradeTransaction) throws InvalidStockException;

	/**
	 * Get Transaction Records with given duration
	 * 
	 * @param stockSymbol
	 * @param currentTime
	 * @param minutes
	 * @return
	 */
	public List<TradeTransaction> getTransactionRecordsByDuration(String stockSymbol, Date currentTime, int minutes);
	
	/**
	 * Get all Transaction Records
	 * 
	 * @return list of all transaction records
	 */
	public List<TradeTransaction> getAllTransactionRecords();
}

