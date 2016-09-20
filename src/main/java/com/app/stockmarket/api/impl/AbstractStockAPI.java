/**
 * 
 */
package com.app.stockmarket.api.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.app.stockmarket.api.IStockAPI;
import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.domain.TradeTransaction;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;

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

		System.out.println("P/E   =" + price + "/" + dividend);
		
		if (price != 0.0) {
			return price / dividend;
		}

		return 0;
	}

	@Override
	public double volumeWeightedStockPriceByTime(String stockSymbol, int minutes) throws InvalidStockException {

		double volumeWeightedStockPrice = 0.0;

		Date currTime = new Date();
		
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		System.out.println("Current Time is : " + dt1.format(new Date()));
		
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

		System.out.println("Volume Weighted Stock Price   = Cumulative Price in last 15 min / Sum Od Qty ");
		System.out.println("                              = "+ cumPrice + " / " + totalQty);
		if (totalQty != 0) {
			volumeWeightedStockPrice = cumPrice / totalQty;
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
		geometricMean = Math.round(geometricMean);
		
		System.out.println("Geometric mean   = nth root of Cumulative Price");
		System.out.println("                 = "+ cumPrice + "^  1.0 / " + size);
		
		return geometricMean;

	}

	@Override
	public boolean validate(String stockSymbol) throws InvalidStockException {
		return stockDataService.getStockData(stockSymbol) != null;
	}

}
