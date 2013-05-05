package com.stockcenter;


import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.stockinfo.service.impl.StockExchangeServiceImpl;
import com.util.mina.RandomUtil;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ������Ϣ������
 * User: tanzetao
 * Date: 13-5-2
 * Time: ����12:19
 */
public class StockMinaServerHandler extends IoHandlerAdapter {

	/**
	 * ��һ���ͻ������ӽ���ʱ
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {

		System.out.println("[Server log]:client[" + session.getRemoteAddress()+"] create a connection with server");
		StockExchangeServiceImpl stockExchangeServiceImpl = new StockExchangeServiceImpl();
		List stockInfoList = StockExchangeServiceImpl.list;  //��ȡ�ڴ�ά���Ĺ�Ʊ��Ϣ����
		ListIterator it = stockInfoList.listIterator();//Ĭ�ϴ��±�0��ʼ
        //�����Nodejs�м��
		while(it.hasNext()){
			String stockMsg = (String) it.next();
			session.write(stockMsg);
			System.out.println("[Server log]:��Ʊ��Ϣ�м����ʼ�����ӽ������ĺ���"+session.getRemoteAddress()+"����ȫ�������Ʊ������Ϣ��"+stockMsg);
		}		
		while(session.isConnected())
		{
			Thread.sleep(RandomUtil.RandomInt(10)*500); //ÿ�����ʱ������Ϣ�м�����͹�Ʊ������Ϣ
			String randomStockExchangeMsg = stockExchangeServiceImpl.getRandomStockInfo();
			session.write(randomStockExchangeMsg);
			System.out.println("[Server log]:�����������Ʊ��Ϣ�м��"+session.getRemoteAddress()+"����ʵʱ��Ʊ����������Ϣ"+randomStockExchangeMsg);	
		}
	}
	
	/**
	 * ���ͻ��˽��ܵ���Ϣʱ
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		System.out.println("[server log]:receive msg from client : " + (String)message);
		session.write("send a msg to client");

	}

	/**
	 * ��һ���ͻ��˱��ر�ʱ
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("[server log]:one client disconnect");
	}
	
	
	

}