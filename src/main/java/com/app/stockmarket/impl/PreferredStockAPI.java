/**
 * 
 */
package com.app.stockmarket.api.impl;

import com.app.stockmarket.domain.FixedDividendStock;
import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;

/**
 * @author Shivakumar Ramannavar
 *
 */
public class PreferredStockAPI extends AbstractStockAPI {

	/**
	 * @param stockDataService
	 */
	public PreferredStockAPI(IStockDataService stockDataService) {
		super(stockDataService);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.stockmarket.api.impl.AbstractStockAPI#calculateDividendYield(java
	 * .lang.String, double)
	 */
	@Override
	public double calculateDividendYield(String stockSymbol, double price) throws InvalidStockException {

		FixedDividendStock stock = (FixedDividendStock) stockDataService.getStockData(stockSymbol);;

		double dividendYield = 0.0;
		
		double fixedDividendParValue = (stock.getParValue() * stock.getFixedDividendPercentage() / 100);

		if (price != 0.0) {
			dividendYield = fixedDividendParValue / price;
		}

		return dividendYield;
	}

}
