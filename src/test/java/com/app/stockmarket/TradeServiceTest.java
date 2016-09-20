/**
 * 
 */
package com.app.stockmarket;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.app.stockmarket.domain.CommonStock;
import com.app.stockmarket.domain.FixedDividendStock;
import com.app.stockmarket.domain.Stock;
import com.app.stockmarket.exception.InvalidStockException;
import com.app.stockmarket.service.ITradeService.BuySellIndicator;
import com.app.stockmarket.service.Logger;
import com.app.stockmarket.service.impl.StockDataSource;
import com.app.stockmarket.service.impl.TradeService;
import com.app.stockmarket.types.Currency;

/**
 * @author sramanna
 *
 */
public class TradeServiceTest {

	TradeService tradeService;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		try {
			
			final StockDataSource stockDataSource = new StockDataSource();
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
			tradeService = new TradeService(stockDataSource);
		
		} catch (InvalidStockException e) {
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getErrorCode().toString(), e);
		}
	}

	/**
	 * Test method for {@link com.app.stockmarket.service.impl.TradeService#createStockInMarket(com.app.stockmarket.domain.Stock)}.
	 */
	@Test
	public void testCreateStockInMarket() {
		try {
			Stock stock = new CommonStock();
			stock.setSymbol("JOE");
			stock.setParValue(60);
			stock.setCurrency(Currency.USD);
			assertTrue(tradeService.createStockInMarket(stock));
		} catch (InvalidStockException e) {
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getErrorCode().toString(), e);
		}
	}

	/**
	 * Test method for {@link com.app.stockmarket.service.impl.TradeService#tradeStockInMarket(java.lang.String, int, com.app.stockmarket.service.ITradeService.BuySellIndicator, double, java.util.Date)}.
	 */
	@Test
	public void testTradeStockInMarket() {
		try {
			assertTrue(tradeService.tradeStockInMarket("TEA", 1, BuySellIndicator.BUY, 10.00, new Date()));
		} catch (InvalidStockException e) {
			Logger.logDebugMessage("Message : " + e.getMessage());
			assertNull(e.getErrorCode().toString(), e);
		}
	}
}
