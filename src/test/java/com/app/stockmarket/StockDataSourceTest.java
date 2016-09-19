/**
 * 
 */
package com.app.stockmarket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.app.stockmarket.domain.CommonStock;
import com.app.stockmarket.domain.FixedDividendStock;
import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.domain.TradeTransaction;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.ITradeService.BuySellIndicator;
import com.app.stockmarket.service.impl.StockDataSource;
import com.app.stockmarket.types.Currency;

/**
 * @author sramanna
 *
 */
public class StockDataSourceTest {
	
	private StockDataSource stockDataSource;

	@Before
	public void setUp() throws Exception {
		stockDataSource = new StockDataSource();
		
		try {
			Stock stock = new CommonStock();
			stock.setSymbol("TEA");
			stock.setParValue(100);
			stock.setCurrency(Currency.USD);
			stockDataSource.saveStockData(stock);
			
			stock = new FixedDividendStock();
			stock.setSymbol("GIN");
			stock.setParValue(100);
			stock.setCurrency(Currency.USD);
			stockDataSource.saveStockData(stock);
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			System.out.println("Message : " + e.getMessage());
			assertNull(e.getErrorCode().toString(), e);
		}
	}
	
	/**
	 * Test method for {@link com.app.stockmarket.service.impl.StockDataSource#listStockSymbols()}.
	 */
	@Test
	public void testListStockSymbols() {
		Set<String> stockSymbols = stockDataSource.listStockSymbols();
		
		assertNotNull("List of Stock symbols empty.", stockSymbols);
		
		assertEquals("Unexpted size returned", 2, stockSymbols.size());
	}

	/**
	 * Test method for {@link com.app.stockmarket.service.impl.StockDataSource#saveStockData(com.app.stockmarket.domain.Stock)}.
	 */
	@Test
	public void testSaveStockData() {
		
		try {
			Stock stock = new CommonStock();
			stock.setSymbol("TEA");
			stock.setParValue(100);
			stock.setCurrency(Currency.USD);
			stockDataSource.saveStockData(stock);
			
			stock = new CommonStock();
			stock.setSymbol("POP");
			stock.setParValue(100);
			stock.setCurrency(Currency.USD);
			stockDataSource.saveStockData(stock);
			
			stock = new CommonStock();
			stock.setSymbol("ALE");
			stock.setParValue(60);
			stock.setCurrency(Currency.USD);
			stockDataSource.saveStockData(stock);
			
			stock = new FixedDividendStock();
			stock.setSymbol("GIN");
			stock.setParValue(100);
			stock.setCurrency(Currency.USD);
			stockDataSource.saveStockData(stock);
			
			stock = new CommonStock();
			stock.setSymbol("JOE");
			stock.setParValue(60);
			stock.setCurrency(Currency.USD);
			stockDataSource.saveStockData(stock);
			
			
		} catch (InvalidStockException e) {
			System.out.println("Message : " + e.getMessage());
			assertNull(e.getErrorCode().toString(), e);
		}
	}

	/**
	 * Test method for {@link com.app.stockmarket.service.impl.StockDataSource#getStockData(java.lang.String)}.
	 */
	@Test
	public void testGetStockData() {
		Stock stock;
		try {
			stock = stockDataSource.getStockData("TEA");
			assertTrue("Unexpected Data for TEA", "TEA".equals(stock.getSymbol()));
			
			
			stock = stockDataSource.getStockData("GIN");
			assertTrue("Unexpected Data for GIN, Symbol was : " + stock.getSymbol(), "GIN".equals(stock.getSymbol()));
			assertTrue("Unexpected Data for GIN, Stock Type was : " + stock.getStockType().toString(), "PREFERRED".equals(stock.getStockType().toString()));
			
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			System.out.println("Message : " + e.getMessage());
			assertNull(e.getErrorCode().toString(), e);
		}
		
		
	}

	/**
	 * Test method for {@link com.app.stockmarket.service.impl.StockDataSource#recordTradeTransation(com.app.stockmarket.domain.TradeTransaction)}.
	 */
	@Test
	public void testRecordTradeTransation() {
		try {
			
			TradeTransaction tradeTransaction = new TradeTransaction();
			tradeTransaction.setStockSymbol("TEA");
			tradeTransaction.setBuySellIndicator(BuySellIndicator.BUY);
			tradeTransaction.setQuantity(1);
			tradeTransaction.setTimestamp(new Date());
			tradeTransaction.setTradedPrice(10.00);
			stockDataSource.recordTradeTransation(tradeTransaction);
			
			tradeTransaction = new TradeTransaction();
			tradeTransaction.setStockSymbol("TEA");
			tradeTransaction.setBuySellIndicator(BuySellIndicator.SELL);
			tradeTransaction.setQuantity(1);
			tradeTransaction.setTimestamp(new Date());
			tradeTransaction.setTradedPrice(10.00);
			stockDataSource.recordTradeTransation(tradeTransaction);
			
			tradeTransaction = new TradeTransaction();
			tradeTransaction.setStockSymbol("TEA");
			tradeTransaction.setBuySellIndicator(BuySellIndicator.BUY);
			tradeTransaction.setQuantity(1);
			tradeTransaction.setTimestamp(new Date());
			tradeTransaction.setTradedPrice(10.00);
			stockDataSource.recordTradeTransation(tradeTransaction);
		} catch (InvalidStockException e) {
			e.printStackTrace(System.out);
			System.out.println("Message : " + e.getMessage());
			assertNull(e.getErrorCode().toString(), e);
		}
	}

}
