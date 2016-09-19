package com.app.stockmarket;
/**
 * 
 */

import java.util.Locale;

/**
 * @author sramanna
 *
 */
public class StockExchange extends AbstractStockExchange {

	/**
	 * 
	 */
	private static final int FIFTEEN_MINUTES = 2;
	
	public StockExchange(String name, String country) {
		this.name = name;
		this.country = country;
	}

	private String name;
	
	private String country;


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	
	
	@Override
	protected int getConfiguredMinuntes() {
		return FIFTEEN_MINUTES;
	}

	@Override
	protected Locale getClientLocale() {
		return Locale.UK;
	}
}
