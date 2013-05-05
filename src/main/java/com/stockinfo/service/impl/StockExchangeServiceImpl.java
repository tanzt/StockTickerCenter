package com.stockinfo.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stockinfo.po.StockInfo;
import com.stockinfo.service.StockExchangeService;
import com.util.mina.JsonUtil;
import com.util.mina.RandomUtil;

public class StockExchangeServiceImpl implements StockExchangeService {

	public static List<String> list= new ArrayList<String>();
	
	public StockExchangeServiceImpl()
	{
		getAllStockInfo();
	}

	public  List<String> getAllStockInfo() {
		// TODO Auto-generated method stub	
		StockInfo stockInfo = new StockInfo();		
		//随机生成股票信息-0
		stockInfo.setCompany("IXIC");   //NASDQ
		stockInfo.setTickerSymbol("NASDAQ  IXIC");
		stockInfo.setPrice(3340);
		stockInfo.setPercentChange(0.01);
		stockInfo.setChange(41.4);
		stockInfo.setExchangeShares(0);
		stockInfo.setExchangeType("S");	
		stockInfo.setExchangeShares(200);
		stockInfo.setExchangeDate("2013-04-29");
		list.add(JsonUtil.getJsonString4JavaPOJO(stockInfo));  //String化后压入List
		
		//随机生成股票信息-1
		stockInfo.setCompany("Apple Inc");
		stockInfo.setTickerSymbol("NASDAQ  AAPL");
		stockInfo.setPrice(445);
		stockInfo.setPercentChange(0.01);
		stockInfo.setChange(6.2);
		stockInfo.setExchangeShares(0);
		stockInfo.setExchangeType("S");	
		stockInfo.setExchangeShares(200);
		stockInfo.setExchangeDate("2013-04-29");	
		list.add(JsonUtil.getJsonString4JavaPOJO(stockInfo));  //String化后压入List
		
		//随机生成股票信息-2
		stockInfo.setCompany("Microsoft Inc");
		stockInfo.setTickerSymbol("NASDAQ  MSFT");
		stockInfo.setPrice(33);
		stockInfo.setPercentChange(0.012);
		stockInfo.setChange(0.44);
		stockInfo.setExchangeShares(0);
		stockInfo.setExchangeType("S");	
		stockInfo.setExchangeShares(200);
		stockInfo.setExchangeDate("2013-04-29");
		list.add(JsonUtil.getJsonString4JavaPOJO(stockInfo));  //String化后压入List
		
		//随机生成股票信息-3
		stockInfo.setCompany("Yahoo Inc");
		stockInfo.setTickerSymbol("NASDAQ  YHOO");
		stockInfo.setPrice(24);
		stockInfo.setPercentChange(0.02);
		stockInfo.setChange(0.67);
		stockInfo.setExchangeShares(0);
		stockInfo.setExchangeType("S");	
		stockInfo.setExchangeShares(200);
		stockInfo.setExchangeDate("2013-04-29");
		list.add(JsonUtil.getJsonString4JavaPOJO(stockInfo));  //String化后压入List
		
		//随机生成股票信息-4
		stockInfo.setCompany("Google Inc");
		stockInfo.setTickerSymbol("NASDAQ  GOOG");
		stockInfo.setPrice(829);
		stockInfo.setPercentChange(0.01);
		stockInfo.setChange(9.1);
		stockInfo.setExchangeShares(0);
		stockInfo.setExchangeType("S");	
		stockInfo.setExchangeShares(200);
		stockInfo.setExchangeDate("2013-04-29");
		list.add(JsonUtil.getJsonString4JavaPOJO(stockInfo));  //String化后压入List
		
		//随机生成股票信息-5
		stockInfo.setCompany("GaoShen Inc");
		stockInfo.setTickerSymbol("NYSE  GS");
		stockInfo.setPrice(143);
		stockInfo.setPercentChange(0.01);
		stockInfo.setChange(0.74);
		stockInfo.setExchangeShares(0);
		stockInfo.setExchangeType("S");	
		stockInfo.setExchangeShares(200);
		stockInfo.setExchangeDate("2013-04-29");
		list.add(JsonUtil.getJsonString4JavaPOJO(stockInfo));  //String化后压入List
		
		//随机生成股票信息-6
		stockInfo.setCompany("Ebay Inc");
		stockInfo.setTickerSymbol("NASDAQ  EBAY");
		stockInfo.setPrice(53);
		stockInfo.setPercentChange(0.02);
		stockInfo.setChange(-1.1);
		stockInfo.setExchangeShares(0);
		stockInfo.setExchangeType("B");	
		stockInfo.setExchangeShares(1000);
		stockInfo.setExchangeDate("2013-04-29");
		list.add(JsonUtil.getJsonString4JavaPOJO(stockInfo));  //String化后压入List
		
		//随机生成股票信息-7
		stockInfo.setCompany("AIG Inc");
		stockInfo.setTickerSymbol("NYSE  AIG");
		stockInfo.setPrice(213);
		stockInfo.setPercentChange(0.02);
		stockInfo.setChange(-0.94);
		stockInfo.setExchangeShares(0);
		stockInfo.setExchangeType("B");	
		stockInfo.setExchangeShares(400);
		stockInfo.setExchangeDate("2013-04-29");
		list.add(JsonUtil.getJsonString4JavaPOJO(stockInfo));  //String化后压入List	
		return list;
	}

	@Override
	public String getRandomStockInfo() {
		// TODO Auto-generated method stub
		 DecimalFormat dformat=new DecimalFormat("#.0000");
		 
		StockInfo stockInfo = new StockInfo();
		String randomStockInfo = null ; 
		if(list!=null && !list.isEmpty())
		{
			int listCount = list.size();
			int index = RandomUtil.RandomInt(listCount);
			stockInfo=(StockInfo) JsonUtil.getObject4JsonString((String)list.get(index),StockInfo.class);  //随机取List中的一个对象，并由String转化成对象
			stockInfo.setPercentChange(Double.valueOf(dformat.format((stockInfo.getPercentChange()+(RandomUtil.RandomDouble()))/2)));  //随机生成一个[-10%,10%]之间的价格变化率
			stockInfo.setChange(Double.valueOf(dformat.format(stockInfo.getPrice()*stockInfo.getPercentChange())));  //Update价格变化量
			stockInfo.setPrice(Double.valueOf(dformat.format(stockInfo.getPrice()+stockInfo.getChange())));  //update 价格变化			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式	
			stockInfo.setExchangeDate(df.format(new Date()));  //// new Date()为获取当前系统时间
			list.remove(index); 
			list.add(JsonUtil.getJsonString4JavaPOJO(stockInfo));
			randomStockInfo = JsonUtil.getJsonString4JavaPOJO((stockInfo));
		}	
		return randomStockInfo;
	}

}
