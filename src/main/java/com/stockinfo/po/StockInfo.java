package com.stockinfo.po;

import java.util.Date;

public class StockInfo {

	private String Company	;       //��˾����
	private String TickerSymbol ;   //��Ʊ����
	private double Price ;          //�۸�
	private double Change;          //�仯��
	private double PercentChange  ;    //�仯�ٷֱ�
	private int ExchangeShares ;   //���׹�Ʊ����
	private String ExchangeType;      //�������ͣ�Sell or Buy
    private String ExchangeDate ;       //����ʱ��
	
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
