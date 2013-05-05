package com.stockcenter;


import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.stockinfo.service.impl.StockExchangeServiceImpl;
import com.util.mina.RandomUtil;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 行情消息处理器
 * User: tanzetao
 * Date: 13-5-2
 * Time: 上午12:19
 */
public class StockMinaServerHandler extends IoHandlerAdapter {

	/**
	 * 当一个客户端连接进入时
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {

		System.out.println("[Server log]:client[" + session.getRemoteAddress()+"] create a connection with server");
		StockExchangeServiceImpl stockExchangeServiceImpl = new StockExchangeServiceImpl();
		List stockInfoList = StockExchangeServiceImpl.list;  //获取内存维护的股票信息缓存
		ListIterator it = stockInfoList.listIterator();//默认从下标0开始
        //传输给Nodejs中间件
		while(it.hasNext()){
			String stockMsg = (String) it.next();
			session.write(stockMsg);
			System.out.println("[Server log]:股票消息中间件初始化连接交易中心后，向"+session.getRemoteAddress()+"发送全部缓存股票行情信息："+stockMsg);
		}		
		while(session.isConnected())
		{
			Thread.sleep(RandomUtil.RandomInt(10)*500); //每隔随机时间向消息中间件发送股票行情信息
			String randomStockExchangeMsg = stockExchangeServiceImpl.getRandomStockInfo();
			session.write(randomStockExchangeMsg);
			System.out.println("[Server log]:交易中心向股票消息中间件"+session.getRemoteAddress()+"发送实时股票交易行情信息"+randomStockExchangeMsg);	
		}
	}
	
	/**
	 * 当客户端接受到消息时
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		System.out.println("[server log]:receive msg from client : " + (String)message);
		session.write("send a msg to client");

	}

	/**
	 * 当一个客户端被关闭时
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("[server log]:one client disconnect");
	}
	
	
	

}