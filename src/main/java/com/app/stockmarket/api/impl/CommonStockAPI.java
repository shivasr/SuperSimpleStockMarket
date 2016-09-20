/**
 * 
 */
package com.app.stockmarket.api.impl;

import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;

/**
 * @author sramanna
 *
 */
public class CommonStockAPI extends AbstractStockAPI {

	public CommonStockAPI(IStockDataService stockDataService) {
		super(stockDataService);
	}

	/* (non-Javadoc)
	 * @see com.app.stockmarket.api.impl.AbstractStockAPI#calculateDividendYield(java.lang.String, double)
	 */
	@Override
	public double calculateDividendYield(String stockSymbol, double price) throws InvalidStockException {
		Stock stock = stockDataService.getStockData(stockSymbol);
		
		double dividendYield = 0.0;

		System.out.println("dividendYield  =" + stock.getLastDividend() + "/" + price);
		if (price != 0.0) {
			dividendYield = stock.getLastDividend() / price;
		}
		
		return dividendYield;
	}

}
