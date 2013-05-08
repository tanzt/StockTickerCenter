package com.stockcenter;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import com.util.filter.SocketMessageCodecFactory;

/**
 * ��Ʊ��Ϣ��������Server
 * User: tanzetao
 * Date: 13-5-2
 * Time: ����12:19
 */
public class StockMinaServer {

    // �������˿�
    private static final int SERVER_PORT = 8891;

    public static void main(String[] args) throws Exception {

        //����һ����������Server��Socket����NIO
        SocketAcceptor acceptor = new NioSocketAcceptor();

        // ����ÿ�ν������ݴ�С
        SocketSessionConfig sessionConfig = acceptor.getSessionConfig();
        sessionConfig.setReadBufferSize(2048);

        //�����������ݵĹ�����
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();

        //�趨�����������һ��һ�У�/r/n���Ķ�ȡ����
        chain.addLast("myChain", new ProtocolCodecFilter(new TextLineCodecFactory()));
		chain.addLast("codec",new ProtocolCodecFilter(new SocketMessageCodecFactory()));
	    chain.addLast("exector",new ExecutorFilter(Executors.newCachedThreadPool()));

        //�趨�������˵���Ϣ������: һ�� StockMinaServerHandler ����
        acceptor.setHandler(new StockMinaServerHandler());

        //�󶨶˿ڣ�����������
        try {
            acceptor.bind(new InetSocketAddress(SERVER_PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[log server]:Mina server is listing port:" + SERVER_PORT);

    }

}