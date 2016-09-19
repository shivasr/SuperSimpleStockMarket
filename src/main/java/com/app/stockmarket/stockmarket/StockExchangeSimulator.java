package com.app.stockmarket;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.app.stockmarket.domain.CommonStock;
import com.app.stockmarket.domain.FixedDividendStock;
import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;
import com.app.stockmarket.service.impl.StockDataSource;
import com.app.stockmarket.service.impl.TradeService;
import com.app.stockmarket.types.Currency;

public class StockExchangeSimulator {

	public StockExchangeSimulator() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InvalidStockException {
		StockExchangeAPI stockExchange = new StockExchange("GBCE", "UK");
		
		IStockDataService stockDS = new StockDataSource();
		stockExchange.registerStockDataService(stockDS);
		stockExchange.registerTradeService(new TradeService(stockDS));
		
		Stock stock = new CommonStock();
		stock.setSymbol("TEA");
		stock.setLastDividend(0);
		stock.setParValue(100);
		stock.setLastDividend(0);
		stock.setCurrency(Currency.USD);
		stockExchange.createStockInMarket(stock);
		
		stock.setSymbol("POP");
		stock.setLastDividend(0);
		stock.setParValue(100);
		stock.setLastDividend(8);
		stock.setCurrency(Currency.USD);
		stockExchange.createStockInMarket(stock);
		
		stock.setSymbol("ALE");
		stock.setLastDividend(0);
		stock.setParValue(100);
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
		
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
		
		for(int i = 0; i < 6; i++) {
			final Date currTime = new Date();
			stockExchange.buyStock("GIN", 1, Math.round(Math.random() * 10));
			System.out.println("Bought GIN Stock - Step " + (i + 1) + " Time : "+ dt1.format(currTime));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i = 6; i < 12; i++) {
			final Date currTime = new Date();
			stockExchange.sellStock("GIN", 1, Math.round(Math.random() * 10));
			System.out.println("Sold GIN Stock - Step " + (i + 1) + " Time : "+ dt1.format(currTime));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
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
