/**
 * 
 */
package com.app.stockmarket.api;

import com.app.stockmarket.exception.InvalidStockException;

/**
 * Stock API interface to define operations on a Stock
 * 
 * @author Shivakumar Ramannavar
 *
 */
public interface IStockAPI {

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
	public double volumeWeightedStockPriceByTime(String stockSymbol, int minutes) throws InvalidStockException;

	/**
	 * Validate a stock symbol to be associated with a Stock in market
	 * @param stockSymbol Symbol of the stock
	 * @throws InvalidStockException When no stock is associated with the stock symbol
	 */
	public boolean validate(String stockSymbol) throws InvalidStockException;
	
	/**
	 * Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
	 */
	public double calculateAllShareIndex() throws InvalidStockException;
	
}
