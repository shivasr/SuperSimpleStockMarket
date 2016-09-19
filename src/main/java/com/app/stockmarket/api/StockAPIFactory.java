/**
 * 
 */
package com.app.stockmarket.api;

import com.app.stockmarket.api.impl.CommonStockAPI;
import com.app.stockmarket.api.impl.PreferredStockAPI;
import com.app.stockmarket.service.IStockDataService;
import com.app.stockmarket.types.StockType;

/**
 * @author Shivakumar Ramannavar
 *
 */
public class StockAPIFactory {

	/**
	 * 
	 */
	private StockAPIFactory() {
		
	}

	public static IStockAPI generateStockAPI(StockType stockType, IStockDataService stockDataService) {

		switch (stockType) {
			case COMMON:
				return new CommonStockAPI(stockDataService);
	
			case PREFERRED:
				return new PreferredStockAPI(stockDataService);
	
			default:
				break;
		}

		return null;
	}

}
