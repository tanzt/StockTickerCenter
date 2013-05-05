package com.util.filter;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class SocketMessageCodecFactory implements ProtocolCodecFactory {

private Charset charset;
	
	public void setCharset(String charsetName) {
		charset = Charset.forName(charsetName);
	}
	
	public SocketMessageCodecFactory(String charsetName) {
		setCharset(charsetName);
	}
	
	public SocketMessageCodecFactory() {
		this("utf-8");
	}


	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new SocketMessageDecoder(charset);
	}

	
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new SocketMessageEncoder(charset);
	}

}
