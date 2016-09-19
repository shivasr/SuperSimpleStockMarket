/**
 * 
 */
package com.app.stockmarket.domain;

import com.app.stockmarket.types.Currency;
import com.app.stockmarket.types.StockType;

/**
 * @author sramanna
 *
 */
public class CommonStock extends Stock {
	/**
	 * Type of Stock - Common or preferred
	 */
	protected StockType stockType = StockType.COMMON;
	
	/**
	 * Symbol of the stock
	 */
	private String symbol;
	
	/**
	 * Par value of the stock
	 * 
	 */
	private double parValue;
	
	
	/**
	 * Currency of the stock, defaulted to the currency of the Stock Market
	 */
	private Currency currency;
	
	/**
	 * Last Dividend declared for the stock
	 */
	private double lastDividend;
	
	/**
	 * @return the lastDividend
	 */
	public double getLastDividend() {
		return lastDividend;
	}

	/**
	 * Setter method for Last Dividend of the stock
	 * 
	 * @param lastDividend the lastDividend to set
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * Getter method for Last Dividend of the stock
	 * 
	 * @return the stockType
	 */
	public StockType getStockType() {
		return StockType.COMMON;
	}

	/**
	 * Getter method for Stock Symbol
	 * 
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Getter method for Stock Symbol
	 * 
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * Getter method for Par Value of the stock
	 * 
	 * @return the parValue
	 */
	public double getParValue() {
		return parValue;
	}

	/**
	 * Setter method for Par value of the stock
	 * 
	 * @param parValue the parValue to set
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	/**
	 * Getter method for currency of the price of the stock
	 * 
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Getter method for currency of the stock
	 * 
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
