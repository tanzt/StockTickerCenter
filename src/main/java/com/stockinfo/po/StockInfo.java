package com.stockinfo.po;

import java.util.Date;

public class StockInfo {

	private String Company	;       //公司名称
	private String TickerSymbol ;   //股票代码
	private double Price ;          //价格
	private double Change;          //变化量
	private double PercentChange  ;    //变化百分比
	private int ExchangeShares ;   //交易股票数量
	private String ExchangeType;      //交易类型：Sell or Buy
    private String ExchangeDate ;       //交易时间
	
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getTickerSymbol() {
		return TickerSymbol;
	}
	public void setTickerSymbol(String tickerSymbol) {
		TickerSymbol = tickerSymbol;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public double getChange() {
		return Change;
	}
	public void setChange(double change) {
		Change = change;
	}
	public double getPercentChange() {
		return PercentChange;
	}
	public void setPercentChange(double percentChange) {
		PercentChange = percentChange;
	}
	public int getExchangeShares() {
		return ExchangeShares;
	}
	public void setExchangeShares(int exchangeShares) {
		ExchangeShares = exchangeShares;
	}
	public String getExchangeType() {
		return ExchangeType;
	}
	public void setExchangeType(String exchangeType) {
		ExchangeType = exchangeType;
	}

	public void setExchangeDate(String date) {
		ExchangeDate = date;
	}
	public String getExchangeDate() {
		return ExchangeDate;
	}
	
		 	 	 	 
}
