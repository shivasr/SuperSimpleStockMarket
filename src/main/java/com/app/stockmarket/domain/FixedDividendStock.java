package com.app.stockmarket.domain;

import com.app.stockmarket.types.StockType;

public class FixedDividendStock extends CommonStock {

	private int fixedDividendPercentage;
	
	/**
	 * Getter method for Last Dividend of the stock
	 * 
	 * @return the stockType
	 */
	public StockType getStockType() {
		return StockType.PREFERRED;
	}

	/**
	 * Get the Fixed Dividend Percentage
	 * 
	 * @return the fixedDividendPercentage
	 */
	public int getFixedDividendPercentage() {
		return fixedDividendPercentage;
	}

	/**
	 * Set Fixed Dividend Percentage 
	 * 
	 * @param fixedDividendPercentage the fixedDividendPercentage to set
	 */
	public void setFixedDividendPercentage(int fixedDividendPercentage) {
		this.fixedDividendPercentage = fixedDividendPercentage;
	}
}
