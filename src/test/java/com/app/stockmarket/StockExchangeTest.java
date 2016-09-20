/**
 * 
 */
package com.app.stockmarket;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.app.stockmarket.domain.CommonStock;
import com.app.stockmarket.domain.FixedDividendStock;
import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.IStockDataService;
import com.app.stockmarket.service.Logger;
import com.app.stockmarket.service.impl.StockDataSource;
import com.app.stockmarket.service.impl.TradeService;
import com.app.stockmarket.types.Currency;

/**
 * @author sramanna
 *
 */
public class StockExchangeTest {

	static StockExchangeAPI stockExchange = null;
	
	SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		
		IStockDataService stockDS = new StockDataSource();
		
		stockExchange = new StockExchange("GBCE", "UK")
				.registerStockDataService(stockDS)
				.registerTradeService(new TradeService(stockDS));
		
		try {
			Stock stock = new FixedDividendStock();
			stock.setSymbol("GIN");
			stock.setParValue(100);
			stock.setCurrency(Currency.USD);
			stockExchange.createStockInMarket(stock);
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getErrorCode().toString(), e);
		}
		
	}

	/**
	 * Test method for {@link com.app.stockmarket.AbstractStockExchange#createStockInMarket(com.app.stockmarket.domain.Stock)}.
	 */
	@Test
	public void testCreateStockInMarket() {
		try {
			Stock stock = new CommonStock();
			stock.setSymbol("TEA");
			stock.setLastDividend(0);
			stock.setParValue(100);
			stock.setLastDividend(0);
			stock.setCurrency(Currency.USD);
			stockExchange.createStockInMarket(stock);
			
			stock = new CommonStock();
			stock.setSymbol("POP");
			stock.setLastDividend(0);
			stock.setParValue(100);
			stock.setLastDividend(8);
			stock.setCurrency(Currency.USD);
			stockExchange.createStockInMarket(stock);
			
			stock = new CommonStock();
			stock.setSymbol("ALE");
			stock.setLastDividend(0);
			stock.setParValue(100);
			stock.setLastDividend(23);
			stock.setCurrency(Currency.USD);
			stockExchange.createStockInMarket(stock);
			
			
			stock = new CommonStock();
			stock.setSymbol("JOE");
			stock.setLastDividend(0);
			stock.setParValue(100);
			stock.setLastDividend(13);
			stock.setCurrency(Currency.USD);
			stockExchange.createStockInMarket(stock);
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getErrorCode().toString(), e);
		}
	}

	/**
	 * Test method for {@link com.app.stockmarket.AbstractStockExchange#buyStock(java.lang.String, int, double)}.
	 */
	@Test
	public void testBuyStock() {
		try {
				
			for(int i = 0; i < 6; i++) {
				Date  currTime = new Date();
				stockExchange.buyStock("GIN", 1, 10.00);
				Logger.logDebugMessage("Bought GIN Stock - Step " + (i + 1) + " Time : "+ dt1.format(currTime));
			}
			
			
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getErrorCode().toString(), e);
		}
	}

	/**
	 * Test method for {@link com.app.stockmarket.AbstractStockExchange#sellStock(java.lang.String, int, double)}.
	 */
	@Test
	public void testSellStock() {
		for(int i = 0; i < 6; i++) {
			Date  currTime = new Date();
			
			try {
				stockExchange.sellStock("GIN", 1, 10.00);
				Logger.logDebugMessage("Sold GIN Stock - Step " + (i + 1) + " Time : "+ dt1.format(currTime));
			} catch (InvalidStockException e) {
				Logger.logDebugMessage("Message : " + e.getMessage());
				assertNull(e.getErrorCode().toString(), e);
			}
		}
	}

	/**
	 * Test method for {@link com.app.stockmarket.AbstractStockExchange#calculateDividendYield(java.lang.String, double)}.
	 */
	@Test
	public void testCalculateDividendYield() {
		try {
			assertNotNull(stockExchange.calculateDividendYield("GIN", 20));
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getMessage(), e);
		}
	}

	/**
	 * Test method for {@link com.app.stockmarket.AbstractStockExchange#calculateVolumeWeightedStockPrice(java.lang.String)}.
	 */
	@Test
	public void testCalculateVolumeWeightedStockPrice() {
		try {
			assertNotNull(stockExchange.calculateVolumeWeightedStockPrice("GIN", 2));
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getMessage(), e);
		}
	}

	/**
	 * Test method for {@link com.app.stockmarket.AbstractStockExchange#calculateAllShareIndex()}.
	 */
	@Test
	public void testCalculateAllShareIndex() {
		try {
			assertNotNull(stockExchange.calculateAllShareIndex());
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getMessage(), e);
		}
	}

	/**
	 * Test method for {@link com.app.stockmarket.AbstractStockExchange#priceOverDividendRatio(java.lang.String, double)}.
	 */
	@Test
	public void testPriceOverDividendRatio() {
		try {
			assertNotNull(stockExchange.priceOverDividendRatio("GIN", 10));
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getMessage(), e);
		}
	}

	/**
	 * Test method for {@link com.app.stockmarket.AbstractStockExchange#volumeWeightedStockPriceByTime(java.lang.String, int)}.
	 */
	@Test
	public void testVolumeWeightedStockPriceByTime() {
		try {
			assertNotNull(stockExchange.calculateVolumeWeightedStockPrice("GIN", 2));
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getMessage(), e);
		}
	}
	
	
	@Test
	public void testListOfAllStockSymbols() {
		
		List<String> listOfAllSymbols = stockExchange.listAllStockSymbols();
		
		assertNotNull(listOfAllSymbols);
		
		Logger.logDebugMessage("List of all symbols :");
		for(String symbol : listOfAllSymbols) {
			Logger.logDebugMessage(symbol);
		}
	}
	
	@Test
	public void testListOfAllStock() {
		
		List<Stock> listOfAllStocks = stockExchange.listAllStocksInMarket();
		
		assertNotNull(listOfAllStocks);
		
		Logger.logDebugMessage("List of all stocks :");
		for(Stock stock : listOfAllStocks) {
			Logger.logDebugMessage(stock.toString());
		}
	}

}
