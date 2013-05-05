package com.util.filter;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class SocketMessageDecoder extends CumulativeProtocolDecoder {

	private static final char MESSAGE_END = '\0';
	private CharsetDecoder decoder;
	
	public SocketMessageDecoder(Charset charset) {
		decoder = charset.newDecoder();
	}

	@Override
	protected boolean doDecode(IoSession session, IoBuffer byteBuffer,
			ProtocolDecoderOutput output) throws Exception {

		int start = byteBuffer.position();

		while (byteBuffer.hasRemaining()) {
			byte current = byteBuffer.get();
			if (current == MESSAGE_END) {
				int position = byteBuffer.position();
				int limit = byteBuffer.limit();
				byteBuffer.position(start);
				byteBuffer.limit(position);
				byte[] bytes = new byte[byteBuffer.limit()
						- byteBuffer.position() - 1];
				byteBuffer.get(bytes, 0, bytes.length);
				String msg = new String(bytes, decoder.charset());
				output.write(msg);
				byteBuffer.position(position);
				byteBuffer.limit(limit);

				return true;
			}

		}

		byteBuffer.position(start);
		return false;
	}

}
