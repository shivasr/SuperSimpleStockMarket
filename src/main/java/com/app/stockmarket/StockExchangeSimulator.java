package com.app.stockmarket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.app.stockmarket.domain.CommonStock;
import com.app.stockmarket.domain.FixedDividendStock;
import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;
import com.app.stockmarket.service.ITradeService;
import com.app.stockmarket.service.ITradeService.BuySellIndicator;
import com.app.stockmarket.service.Logger;
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
		
		stock = new CommonStock();
		stock.setSymbol("POP");
		stock.setParValue(100);
		stock.setLastDividend(8);
		stock.setCurrency(Currency.USD);
		stockExchange.createStockInMarket(stock);
		
		stock = new CommonStock();
		stock.setSymbol("ALE");
		stock.setLastDividend(23);
		stock.setParValue(60);
		stock.setCurrency(Currency.USD);
		stockExchange.createStockInMarket(stock);
		
		stock = new CommonStock();
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
		
		Logger.logDebugMessage("Starting the simulator... " + " Done");
		
		Logger.logDebugMessage("\n\n");
		Logger.logDebugMessage("**************************** Current Stock Summary *****************************");
		List<Stock> stocks = stockExchange.listAllStocksInMarket();
		for(Stock currStock : stocks) {
			
			if( currStock instanceof FixedDividendStock) {
				FixedDividendStock fixedStock = (FixedDividendStock) currStock;
				
				Logger.logDebugMessage("\t" + fixedStock.getSymbol() + "\t" + String.format(" %-15s", fixedStock.getStockType()) + 
							   "\t" + String.format(" %5d", (int) fixedStock.getLastDividend()) +"\t" + fixedStock.getFixedDividendPercentage() + " %\t" + String.format("%5d", (int) fixedStock.getParValue()));
			} else {
				Logger.logDebugMessage("\t" + currStock.getSymbol() + "\t" + String.format(" %-15s", currStock.getStockType()) + 
						   "\t" + String.format("%5d", (int) currStock.getLastDividend()) + "\t\t" + String.format("%5d", (int) currStock.getParValue()));
			}
		}
		Logger.logDebugMessage("********************************************************************************");
		Logger.logDebugMessage("\n");
		Logger.logDebugMessage("**************************** Transactions **************************************");
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		
		final String stockSymbol = "POP";
		
		double sumTradedPrice = 0.0;
		
		for(int i = 0; i < 80; i++) {
			final double random = Math.random();
			
			long randomPercentage = Math.round(random * 100);
			
			int index =  (int) randomPercentage / 10;
			int stockIndex = index % setOfSymbols.length;
			
			int buyIndicatorIndex = index % BuySellIndicator.values().length;
			
			stock = stockDS.getStockData(setOfSymbols[stockIndex]);
			
			double tradedPrice =  (randomPercentage * stock.getParValue()) / 100;

			BuySellIndicator buySellIndicator = BuySellIndicator.values()[buyIndicatorIndex];
			
			if(i >= 20 && stockSymbol.equals(setOfSymbols[stockIndex])) {
				Logger.logDebugMessage(setOfSymbols[stockIndex]  + " tradedPrice: "+  tradedPrice + " " + " ( " + sumTradedPrice + ") ");
				sumTradedPrice += tradedPrice;
			}
			
			if(buySellIndicator == BuySellIndicator.BUY) {
				stockExchange.buyStock(setOfSymbols[stockIndex], 1, tradedPrice);
			} else {
				stockExchange.sellStock(setOfSymbols[stockIndex], 1, tradedPrice);
			}
			
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Logger.logDebugMessage("**************************** REPORT *****************************");
		Logger.logDebugMessage("Current Time is : " + dt1.format(new Date()));
		Logger.logDebugMessage(String.format("Dividend Yield : %5.2f", stockExchange.calculateDividendYield(stockSymbol, 20)));
		Logger.logDebugMessage(String.format("P/E Ratio : %5.2f",  stockExchange.priceOverDividendRatio(stockSymbol, 20)));
		Logger.logDebugMessage(String.format("Volume Weighted Stock Price based on trades in past 15 minutes : %5.2f", stockExchange.calculateVolumeWeightedStockPrice(stockSymbol, 15)));
		Logger.logDebugMessage("GBCE All Share Index : " + stockExchange.calculateAllShareIndex());
		Logger.logDebugMessage("*****************************************************************");
	}

}
