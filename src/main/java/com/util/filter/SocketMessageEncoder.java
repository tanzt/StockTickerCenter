package com.util.filter;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class SocketMessageEncoder implements ProtocolEncoder {

	private static final int BUFFER_SIZE = 1024 * 100;
	private static final char MESSAGE_END = '\0';
	private CharsetEncoder encoder;
	
	public SocketMessageEncoder(Charset charset) {
		encoder = charset.newEncoder();
	}
	
	@Override
	public void dispose(IoSession session) throws Exception {}

	@Override
	public void encode(IoSession session, Object object, ProtocolEncoderOutput output)
			throws Exception {
		
		IoBuffer ioBuffer = IoBuffer.allocate(BUFFER_SIZE);
		String message = (String) object;
		ioBuffer.putString(message + MESSAGE_END, encoder);
		ioBuffer.flip();
		output.write(ioBuffer);

	}

}
