package com.stockcenter;


import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.util.filter.SocketMessageCodecFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * 股票消息交易中心Server
 * User: tanzetao
 * Date: 13-5-2
 * Time: 上午12:19
 */
public class StockMinaServer {

    // 服务器端口
    private static final int SERVER_PORT = 8891;

    public static void main(String[] args) throws Exception {

        //创建一个非阻塞的Server端Socket，用NIO
        SocketAcceptor acceptor = new NioSocketAcceptor();

        // 定义每次接收数据大小
        SocketSessionConfig sessionConfig = acceptor.getSessionConfig();
        sessionConfig.setReadBufferSize(2048);

        //创建接受数据的过滤器
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();

        //设定这个过滤器将一行一行（/r/n）的读取数据
        chain.addLast("myChain", new ProtocolCodecFilter(new TextLineCodecFactory()));
		chain.addLast("codec",new ProtocolCodecFilter(new SocketMessageCodecFactory()));
	    chain.addLast("exector",new ExecutorFilter(Executors.newCachedThreadPool()));

        //设定服务器端的消息处理器: 一个 StockMinaServerHandler 对象
        acceptor.setHandler(new StockMinaServerHandler());

        //绑定端口，启动服务器
        try {
            acceptor.bind(new InetSocketAddress(SERVER_PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[log server]:Mina server is listing port:" + SERVER_PORT);

    }

}