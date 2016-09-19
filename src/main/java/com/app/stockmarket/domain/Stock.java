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
public abstract class Stock {

	
	/**
	 * @return the lastDividend
	 */
	public abstract double getLastDividend();

	/**
	 * Setter method for Last Dividend of the stock
	 * 
	 * @param lastDividend the lastDividend to set
	 */
	public abstract void setLastDividend(double lastDividend);

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
	public abstract String getSymbol();

	/**
	 * Getter method for Stock Symbol
	 * 
	 * @param symbol the symbol to set
	 */
	public abstract void setSymbol(String symbol);

	/**
	 * Getter method for Par Value of the stock
	 * 
	 * @return the parValue
	 */
	public abstract double getParValue();
	
	/**
	 * Setter method for Par value of the stock
	 * 
	 * @param parValue the parValue to set
	 */
	public abstract void setParValue(double parValue);

	/**
	 * Getter method for currency of the price of the stock
	 * 
	 * @return the currency
	 */
	public abstract Currency getCurrency();

	/**
	 * Getter method for currency of the stock
	 * 
	 * @param currency the currency to set
	 */
	public abstract void setCurrency(Currency currency);

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"Stock [Last Dividend=%s, Stock Type=%s, Symbol=%s, Par Value=%s, Currency=%s]",
				getLastDividend(), getStockType(), getSymbol(), getParValue(), getCurrency());
	}
	
}
