/**
 * 
 */
package com.app.stockmarket.api.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.app.stockmarket.api.IStockAPI;
import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.domain.TradeTransaction;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;
import com.app.stockmarket.service.Logger;

/**
 * @author sramanna
 *
 */
public abstract class AbstractStockAPI implements IStockAPI {

	IStockDataService stockDataService;

	public AbstractStockAPI(IStockDataService stockDataService) {
		this.stockDataService = stockDataService;
	}

	@Override
	public abstract double calculateDividendYield(String stockSymbol, double price) throws InvalidStockException;

	@Override
	public double priceOverDividendRatio(String stockSymbol, double price) throws InvalidStockException {
		Stock stock = stockDataService.getStockData(stockSymbol);

		double dividend = stock.getLastDividend();

		Logger.logDebugMessage("P/E   = Stock Price / Dividend ");
		Logger.logDebugMessage("      = " + price + "/" + dividend);
		
		if (dividend != 0.0) {
			final double pbyE = price / dividend;
			Logger.logDebugMessage("      = " + pbyE + "\n");
			return pbyE;
		}

		return 0;
	}

	@Override
	public double volumeWeightedStockPriceByTime(String stockSymbol, int minutes) throws InvalidStockException {

		double volumeWeightedStockPrice = 0.0;

		Date currTime = Calendar.getInstance().getTime();
		
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Logger.logDebugMessage("Current Time is : " + dt1.format(new Date()));
		
		List<TradeTransaction> records = stockDataService.getTransactionRecordsByDuration(stockSymbol, currTime,
				minutes);

		double cumPrice = 0.0;

		int totalQty = 0;

		for (TradeTransaction trade : records) {
			double price = trade.getTradedPrice();

			int qty = trade.getQuantity();

			cumPrice += (price * qty);
			totalQty += qty;

		}

		Logger.logDebugMessage("Volume Weighted Stock Price   = Cumulative Price in last 15 min / Sum Of Qty ");
		Logger.logDebugMessage("                              = "+ cumPrice + " / " + totalQty);
		Logger.logDebugMessage("                              = "+ cumPrice + " / " + totalQty);
		if (totalQty != 0) {
			volumeWeightedStockPrice = cumPrice / totalQty;
			Logger.logDebugMessage("                              = " + volumeWeightedStockPrice + "\n");
		}
		return volumeWeightedStockPrice;
	}

	/**
	 * Calculate the GBCE All Share Index using the geometric mean of prices for
	 * all stocks
	 */
	public double calculateAllShareIndex() throws InvalidStockException {
		double geometricMean = 0.0;

		List<TradeTransaction> records = stockDataService.getAllTransactionRecords();

		int size = records.size();
		
		double cumPrice = 0.0;

		for (TradeTransaction trade : records) {
			double price = trade.getTradedPrice();

			cumPrice += price;
		}

		geometricMean = Math.pow(cumPrice, 1.0 / size);
		
		Logger.logDebugMessage("Geometric mean   = nth root of Cumulative Price");
		Logger.logDebugMessage("                 = " + cumPrice + " ^  1.0 / " + size);
		Logger.logDebugMessage("                 = " + geometricMean + "\n");
		return geometricMean;

	}

	@Override
	public boolean validate(String stockSymbol) throws InvalidStockException {
		return stockDataService.getStockData(stockSymbol) != null;
	}

}
