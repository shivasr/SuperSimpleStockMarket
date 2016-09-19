/**
 * 
 */
package com.app.stockmarket;

import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;
import com.app.stockmarket.service.ITradeService;

/**
 * Interface which defines
 * @author sramanna
 *
 */
public interface StockExchangeAPI {
	
	/**
	 * Create Stock in Market
	 * 
	 * @param stock Details of the new stock
	 * @return
	 * @throws InvalidStockException 
	 */
	public boolean createStockInMarket(Stock stock) throws InvalidStockException;
	
	
	/**
	 * Buy a stock denoted by a stock symbol
	 * 
	 * @param stockSymbol Symbol 
	 * @param quantity
	 * @param price
	 * @return
	 */
	public boolean buyStock(String stockSymbol, int quantity, double price) throws InvalidStockException;

	/**
	 * Buy a stock denoted by a stock symbol
	 * 
	 * @param stockSymbol
	 * @param quantity
	 * @param price
	 * @return
	 */
	public boolean sellStock(String stockSymbol, int quantity, double price) throws InvalidStockException;
	
	/**
	 * Register Trade Service Implementation
	 * 
	 * @param tradeService implementation of ITradeService interface
	 */
	public void registerTradeService(ITradeService tradeService);
	
	/**
	 * @param stockDataService the stockDataService to set
	 */
	public void registerStockDataService(IStockDataService stockDataService);

	/**
	 * Calculate Dividend yield given price as input 
	 *  
	 * @param stockSymbol Symbol of the stock
	 * @param price Price of the stock
	 * @return Dividend yield for a given stock
	 * @throws InvalidStockException When no stock is associated with the stock symbol
	 */
	public double calculateDividendYield(String stockSymbol, double price) throws InvalidStockException;
	
	/**
	 * Calculate P/E ratio for a given stock, given price as input
	 * 
	 * @param stockSymbol Symbol of the stock
	 * @param price Price of the stock
	 * @return P/E ratio for a given stock,
	 * @throws InvalidStockException When no stock is associated with the stock symbol
	 */
	public double priceOverDividendRatio(String stockSymbol, double price) throws InvalidStockException;
	
	/**
	 * Calculate Volume Weighted Stock Price based on trades in past given input minutes
	 * 
	 * @param symbol Symbol of the stock
	 * @param minutes Time frame in which volume weighted stock price has to be calculated 
	 * @return Volume Weighted Stock price of a given stock
	 * 
	 * @throws InvalidStockException  When no stock is associated with the stock symbol
	 */
	public double calculateVolumeWeightedStockPrice(String stockSymbol, int minutes) throws InvalidStockException;

	/**
	 * Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
	 */
	public double calculateAllShareIndex() throws InvalidStockException;
}
