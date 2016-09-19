/**
 * 
 */
package com.app.stockmarket.types;

/**
 * @author sramanna
 *
 */
public enum Country {
	US ("United States"), UK ("United Kingdom");
	
	/**
	 * 
	 */
	private String countryName;
	
	/**
	 * 
	 * @param countryName
	 */
	private Country(String countryName) {
		this.setCountryName(countryName);
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
}
