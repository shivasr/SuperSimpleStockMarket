/**
 * 
 */
package com.app.stockmarket.domain;

import java.util.Date;

import com.app.stockmarket.service.ITradeService.BuySellIndicator;

/**
 * Trade Transaction to capture the trade transaction for a stock
 * 
 * @author Shivakumar Ramannavar
 *
 */
public class TradeTransaction {
	
	/**
	 * 
	 */
	private String stockSymbol;
	
	/**
	 * 
	 */
	private int quantity;
	
	/**
	 * 
	 */
	private BuySellIndicator buySellIndicator; 
	
	/**
	 * @return the stockSymbol
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * @param stockSymbol the stockSymbol to set
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the buySellIndicator
	 */
	public BuySellIndicator getBuySellIndicator() {
		return buySellIndicator;
	}

	/**
	 * @param buySellIndicator the buySellIndicator to set
	 */
	public void setBuySellIndicator(BuySellIndicator buySellIndicator) {
		this.buySellIndicator = buySellIndicator;
	}

	/**
	 * @return the tradedPrice
	 */
	public double getTradedPrice() {
		return tradedPrice;
	}

	/**
	 * @param tradedPrice the tradedPrice to set
	 */
	public void setTradedPrice(double tradedPrice) {
		this.tradedPrice = tradedPrice;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"TradeTransaction [stockSymbol=%s, quantity=%s, buySellIndicator=%s, tradedPrice=%s, timestamp=%s]",
				stockSymbol, quantity, buySellIndicator, tradedPrice, timestamp);
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 
	 */
	private double tradedPrice;
	
	/**
	 * 
	 */
	private Date timestamp;
}
