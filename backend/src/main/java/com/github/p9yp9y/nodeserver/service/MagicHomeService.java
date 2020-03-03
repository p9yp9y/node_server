package com.github.p9yp9y.nodeserver.service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.input.ObservableInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MagicHomeService {
	private static final Logger logger = LoggerFactory.getLogger(MagicHomeService.class);

    private String host;

	public void connect(final String host) {
		this.host = host;
	}

	public void setColor(final byte red, final byte green, final byte blue) throws IOException {
		String data = "31%02x%02x%02x00000f";
		data = String.format(data, red, green, blue);
		
		sendPackage(DatatypeConverter.parseHexBinary(data));
}

	public void turnLed(final boolean on) throws IOException {
		String data;
		if (on) {
			data = "71230f";
		} else {
			data = "71240f";
		}
		sendPackage(DatatypeConverter.parseHexBinary(data));
	}

	public byte getHash(final byte[] data) {
		int res = 0;
		for (int i = 0; i < data.length; i++) {
			res += data[i];
		}
		return (byte) (res % 0xff);
	}

	private void sendPackage(final byte[] data) throws IOException {

		try {
			Socket socket = new Socket(host, 5577);
			ObservableInputStream in = new ObservableInputStream(socket.getInputStream());
			BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
			out.write(data);
			out.write(getHash(data));
			out.flush();
			out.close();
			in.close();
			socket.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
}
