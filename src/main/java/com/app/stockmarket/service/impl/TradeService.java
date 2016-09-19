/**
 * 
 */
package com.app.stockmarket.service.impl;

import java.util.Date;

import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.domain.TradeTransaction;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;
import com.app.stockmarket.service.ITradeService;
import com.app.stockmarket.service.ITradeService.BuySellIndicator;

/**
 * @author sramanna
 *
 */
public class TradeService implements ITradeService {
	
	private IStockDataService stockDS;

	public TradeService(IStockDataService stockDS) {
		super();
		this.stockDS = stockDS;
	}

	/* (non-Javadoc)
	 * @see com.app.stockmarket.service.ITradeService#createStockInMarket(com.app.stockmarket.domain.Stock)
	 */
	@Override
	public boolean createStockInMarket(Stock stock) throws InvalidStockException {
		stockDS.saveStockData(stock);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.app.stockmarket.service.ITradeService#tradeStockInMarket(java.lang.String, int, com.app.stockmarket.service.ITradeService.BuySellIndicator, double, java.util.Date)
	 */
	@Override
	public boolean tradeStockInMarket(String stockSymbol, int quantity, BuySellIndicator buySellIndicator,
			double tradedPrice, Date timestamp) throws InvalidStockException {
		TradeTransaction tradeTransaction = new TradeTransaction();
		tradeTransaction.setStockSymbol(stockSymbol);
		tradeTransaction.setBuySellIndicator(buySellIndicator);
		tradeTransaction.setQuantity(quantity);
		tradeTransaction.setTimestamp(timestamp);
		tradeTransaction.setTradedPrice(tradedPrice);
		stockDS.recordTradeTransation(tradeTransaction);
		
		return true;
	}

	@Override
	public Stock getStockData(String stockSymbol) throws InvalidStockException {
		return stockDS.getStockData(stockSymbol);
	}

}
