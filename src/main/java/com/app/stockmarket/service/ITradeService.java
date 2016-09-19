package com.app.stockmarket.service;

import java.util.Date;

import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.exception.InvalidStockException;

/**
 * Trade Service interface to buy/sell stocks in market.
 * 
 * @author Shivakumar Ramannavar
 *
 */
public interface ITradeService {
	
	public enum BuySellIndicator {
		BUY("B"), SELL("S");
		
		private String buySellIndicator;
		
		BuySellIndicator(String buySellIndicator) {
			this.setBuySellIndicator(buySellIndicator);
		}

		/**
		 * @return the buySellIndicator
		 */
		public String getBuySellIndicator() {
			return buySellIndicator;
		}

		/**
		 * @param buySellIndicator the buySellIndicator to set
		 */
		public void setBuySellIndicator(String buySellIndicator) {
			this.buySellIndicator = buySellIndicator;
		}
	};
	
	/**
	 * Create a stock in market
	 * 
	 * @param stock Stock to be created
	 * @throws InvalidStockException 
	 */
	public boolean createStockInMarket(Stock stock) throws InvalidStockException;

	/**
	 * Trade stocks - Buy/Sell 
	 * 
	 * @param stockSymbol Symbol of the stock
	 * @param quantity Quantity of stocks to buy/sell
	 * @param buySellIndicator Buy/Sell Indicator 
	 * @param tradedPrice price per stock
	 * @param timestamp Timestamp of the transaction 
	 * @return
	 */
	public boolean tradeStockInMarket(String stockSymbol, 
							int quantity, 
							BuySellIndicator buySellIndicator, 
							double tradedPrice, 
							Date timestamp) throws InvalidStockException;
	
	/**
	 * Get Stock Data using stock symbol
	 * 
	 * @param stockSymbol
	 * @return
	 * @throws InvalidStockException
	 */
	public Stock getStockData(String stockSymbol) throws InvalidStockException;
	
}
