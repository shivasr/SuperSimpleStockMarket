/**
 * 
 */
package com.app.stockmarket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.app.stockmarket.api.IStockAPI;
import com.app.stockmarket.api.StockAPIFactory;
import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;
import com.app.stockmarket.service.ITradeService;
import com.app.stockmarket.service.ITradeService.BuySellIndicator;
import com.app.stockmarket.types.StockType;

/**
 * @author sramanna
 *
 */
public abstract class AbstractStockExchange implements StockExchangeAPI {
	
	protected ITradeService tradeService;
	
	protected IStockDataService stockDataService;

	/**
	 * Create Stock in Market
	 * 
	 * @param stock Details of the new stock
	 * @return
	 * @throws InvalidStockException 
	 */
	public boolean createStockInMarket(Stock stock) throws InvalidStockException {
		if(tradeService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		return tradeService.createStockInMarket(stock);
	}
	
	
	/**
	 * Buy a stock denoted by a stock symbol
	 * 
	 * @param stockSymbol Symbol 
	 * @param quantity
	 * @param price
	 * @return
	 */
	public boolean buyStock(String stockSymbol, int quantity, double price) throws InvalidStockException {
		
		if(tradeService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		Date date = Calendar.getInstance().getTime();
		
		return tradeService.tradeStockInMarket(stockSymbol, quantity, BuySellIndicator.BUY, price, date);
	}

	/**
	 * Buy a stock denoted by a stock symbol
	 * 
	 * @param stockSymbol
	 * @param quantity
	 * @param price
	 * @return
	 */
	public boolean sellStock(String stockSymbol, int quantity, double price) throws InvalidStockException  {
		if(tradeService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		
		return tradeService.tradeStockInMarket(stockSymbol, quantity, BuySellIndicator.SELL, price, date);
	}
	
	/**
	 * Calculate Dividend for a Stock denoted by a symbol
	 * 
	 * @param stockSymbol
	 * @return
	 */
	public double calculateDividendYield(String stockSymbol, double price) throws InvalidStockException  {
		
		if(tradeService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		
		if(stockDataService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		final StockType stockType = tradeService.getStockData(stockSymbol).getStockType();
		
		IStockAPI stockAPI = StockAPIFactory.generateStockAPI(stockType, stockDataService);
		
		return stockAPI.calculateDividendYield(stockSymbol, price);
	}
	
	/**
	 * Calculate Volume Weighted Stock Price
	 * 
	 * @param stockSymbol
	 * @return
	 */
	public double calculateVolumeWeightedStockPrice(String stockSymbol) throws InvalidStockException  {
		
		if(tradeService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		if(stockDataService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		IStockAPI stockAPI = StockAPIFactory.generateStockAPI(tradeService.getStockData(stockSymbol).getStockType(), stockDataService);
		
		return stockAPI.volumeWeightedStockPriceByTime(stockSymbol, getConfiguredMinuntes());
	}

	
	@Override
	public double priceOverDividendRatio(String stockSymbol, double price) throws InvalidStockException {
		
		if(tradeService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		if(stockDataService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		IStockAPI stockAPI = StockAPIFactory.generateStockAPI(tradeService.getStockData(stockSymbol).getStockType(), stockDataService);
		
		
		return stockAPI.priceOverDividendRatio(stockSymbol, price);
	}

	@Override
	public double calculateVolumeWeightedStockPrice(String stockSymbol, int minutes) throws InvalidStockException {
		
		if(tradeService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		if(stockDataService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		IStockAPI stockAPI = StockAPIFactory.generateStockAPI(tradeService.getStockData(stockSymbol).getStockType(), stockDataService);
		
		
		return stockAPI.volumeWeightedStockPriceByTime(stockSymbol, minutes);
	}
	
	/**
	 * Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
	 * 
	 * @param stockSymbol
	 * @return
	 */
	public double calculateAllShareIndex() throws InvalidStockException  {
		
		if(stockDataService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		IStockAPI stockAPI = StockAPIFactory.generateStockAPI(StockType.COMMON, stockDataService);
		
		return stockAPI.calculateAllShareIndex();
	}
	
	@Override
	public List<String> listAllStockSymbols() {
		if(stockDataService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		return stockDataService.listStockSymbols();
	}

	@Override
	public List<Stock> listAllStocksInMarket() {
		if(stockDataService == null)
			throw new UnsupportedOperationException("Trade Service is not configured");
		
		return stockDataService.listAllStocks();
	}
	/**
	 * Register Trade Service Implementation
	 * 
	 * @param tradeService implementation of ITradeService interface
	 */
	public AbstractStockExchange registerTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
		return this;
	}
	
	/**
	 * @param stockDataService the stockDataService to set
	 */
	public AbstractStockExchange registerStockDataService(IStockDataService stockDataService) {
		this.stockDataService = stockDataService;
		return this;
	}
	
	
	
	/**
	 * Get Configured Minutes for calculating Volume Weighted Stock Price
	 * @return
	 */
	protected abstract int getConfiguredMinuntes();
	
	/**
	 * Get Client Locale
	 * 
	 * @return Clients Locale
	 */
	protected abstract Locale getClientLocale();
}
