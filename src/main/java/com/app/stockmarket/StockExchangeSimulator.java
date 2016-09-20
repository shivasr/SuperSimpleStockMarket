package com.app.stockmarket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.app.stockmarket.domain.CommonStock;
import com.app.stockmarket.domain.FixedDividendStock;
import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;
import com.app.stockmarket.service.ITradeService;
import com.app.stockmarket.service.ITradeService.BuySellIndicator;
import com.app.stockmarket.service.impl.StockDataSource;
import com.app.stockmarket.service.impl.TradeService;
import com.app.stockmarket.types.Currency;

/**
 * Test File to simulate the stock market - Buy/Sell
 * @author sramanna
 *
 */
public class StockExchangeSimulator {

	public StockExchangeSimulator() {
		// TODO Auto-generated constructor stub
	}

	static String[] setOfSymbols = new String[] {"TEA", "POP", "ALE","GIN", "JOE"};
	
	public static void main(String[] args) throws InvalidStockException {
		IStockDataService stockDS = new StockDataSource();
		ITradeService tradeService = new TradeService(stockDS);
		
		StockExchangeAPI stockExchange = new StockExchange("GBCE", "UK")
									.registerStockDataService(stockDS)
									.registerTradeService(tradeService);
		
		
		Stock stock = new CommonStock();
		stock.setSymbol("TEA");
		stock.setLastDividend(0);
		stock.setParValue(100);
		stock.setCurrency(Currency.USD);
		stockExchange.createStockInMarket(stock);
		
		stock.setSymbol("POP");
		stock.setParValue(100);
		stock.setLastDividend(8);
		stock.setCurrency(Currency.USD);
		stockExchange.createStockInMarket(stock);
		
		stock.setSymbol("ALE");
		stock.setLastDividend(23);
		stock.setParValue(60);
		stock.setCurrency(Currency.USD);
		stockExchange.createStockInMarket(stock);
		
		stock.setSymbol("JOE");
		stock.setLastDividend(13);
		stock.setParValue(250);
		stock.setLastDividend(23);
		stock.setCurrency(Currency.USD);
		stockExchange.createStockInMarket(stock);
		
		FixedDividendStock stock1 = new FixedDividendStock();
		stock1.setSymbol("GIN");
		stock1.setParValue(100);
		stock1.setCurrency(Currency.USD);
		stock1.setLastDividend(8);
		stock1.setFixedDividendPercentage(2);
		stockExchange.createStockInMarket(stock1);
		
		System.out.println("Starting the simulator... " + " Done");
		
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		
		for(int i = 0; i < 12; i++) {
			final double random = Math.random();
			final Date currTime = new Date();
			
			long randomPercentage = Math.round(random * 100);
			
			int index =  (int) randomPercentage / 10;
			int stockIndex = index % setOfSymbols.length;
			
			int buyIndicatorIndex = index % BuySellIndicator.values().length;
			
			stock = stockDS.getStockData(setOfSymbols[stockIndex]);
			
			double tradedPrice =  (randomPercentage * stock.getParValue()) / 100;

			BuySellIndicator buySellIndicator = BuySellIndicator.values()[buyIndicatorIndex];
			
			if(buySellIndicator == BuySellIndicator.BUY) {
				stockExchange.buyStock(setOfSymbols[stockIndex], 1, tradedPrice);
				System.out.println("Bought " + setOfSymbols[stockIndex] + " Stock for " + stock.getCurrency() + " " +  tradedPrice + " - Step " + (i + 1) + " Time : "+ dt1.format(currTime));
			} else {
				stockExchange.sellStock(setOfSymbols[stockIndex], 1, tradedPrice);
				System.out.println("Sold " + setOfSymbols[stockIndex] + " Stock for " + stock.getCurrency() + " " +  tradedPrice + " - Step " + (i + 1) + " Time : "+ dt1.format(currTime));
			}
			
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println("**************************** REPORT *****************************");
		System.out.println("Current Time is : " + dt1.format(new Date()));
		System.out.println("Dividend Yield : " + stockExchange.calculateDividendYield("GIN", 20));
		System.out.println("P/E Ratio : " + stockExchange.priceOverDividendRatio("GIN", 20));
		System.out.println("Volume Weighted Stock Price based on trades in past 15 minutes : " 
						+ stockExchange.calculateVolumeWeightedStockPrice("GIN", 2));
		System.out.println("GBCE All Share Index : " + stockExchange.calculateAllShareIndex());
		System.out.println("*****************************************************************");
	}

}
