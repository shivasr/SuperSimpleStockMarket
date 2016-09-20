# SuperSimpleStockMarket Project

## Overview
This Project contains source code and classes for Super Simple Stock Market which does the below:

a. For a given stock,
	i. Given any price as input, calculate the dividend yield
	ii. Given any price as input, calculate the P/E Ratio
	iii. Record a trade, with timestamp, quantity of shares, buy or sell indicator and traded price
	iv. Calculate Volume Weighted Stock Price based on trades in past 15 minutes

b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks

## Important Classes
1. com.app.stockmarket.StockExchangeSimulator : Class containing main to run and execute the StockMarket API

2. com.app.stockmarket.StockExchangeAPI : Interface to define operations on stock market to buy/sell stock, create new stock, calculate stock data

3. com.app.stockmarket.AbstractStockExchange : An abstract implemnentation of StockExchangeAPI 

4. com.app.stockmarket.api.IStockAPI : Interface for calculative operations on stock

5. com.app.stockmarket.api.CommonStockAPI: Implementation of IStockAPI on Common type of stocks

6. com.app.stockmarket.api.PreferredStockAPI: Implementation of IStockAPI on Preferred type of stocks

7. com.app.stockmarket.domain.TradeTransaction: Class to encapsulate Trade Transaction Record

[Click to see the Class Illustration](https://drive.google.com/file/d/0BwuG_5pxYjcVdmRCX3J0MkpVb1k/view?usp=sharing)

## How to run:

.1 Using Maven

	1. Download the zip to a local directory 
	2. With all maven settings run mvn clean install
	3. Refer Test Classes to know how it will work	

2. Using pre-built jar: This source code has pre-built using java version "1.8.0_101".
	1. Download SuperSimpleStockMarket.jar to a location say C:\
	2. with Java executables in PATH variable run the sample application using - "java -jar SuperSimpleStockMarket.jar". You much see output as below:
	
	**************************** Current Stock Summary *****************************
        TEA      COMMON                     0             100
        POP      COMMON                     8             100
        ALE      COMMON                    23              60
        JOE      COMMON                    23             250
        GIN      PREFERRED                   8  2 %       100
	********************************************************************************
	
	
	**************************** Transactions **************************************
	Bought JOE Stock for $ 117.5 at  20-09-2016 01:04:20
	Bought POP Stock for $ 69.0 at  20-09-2016 01:04:35
	Bought ALE Stock for $ 13.8 at  20-09-2016 01:04:50
	Bought GIN Stock for $ 82.0 at  20-09-2016 01:05:05
	Sold JOE Stock for $ 225.0 at 20-09-2016 01:05:20
	Bought TEA Stock for $ 9.0 at  20-09-2016 01:05:35
	Sold ALE Stock for $ 42.6 at 20-09-2016 01:05:50
	Sold ALE Stock for $ 46.8 at 20-09-2016 01:06:05
	Sold POP Stock for $ 18.0 at 20-09-2016 01:06:20
	Bought TEA Stock for $ 3.0 at  20-09-2016 01:06:35
	Sold GIN Stock for $ 36.0 at 20-09-2016 01:06:50
	Sold JOE Stock for $ 242.5 at 20-09-2016 01:07:05
	Bought POP Stock for $ 69.0 at  20-09-2016 01:07:20
	Bought TEA Stock for $ 3.0 at  20-09-2016 01:07:35
	Bought JOE Stock for $ 115.0 at  20-09-2016 01:07:50
	Sold GIN Stock for $ 38.0 at 20-09-2016 01:08:05
	Bought POP Stock for $ 63.0 at  20-09-2016 01:08:20
	.
	.
	.
