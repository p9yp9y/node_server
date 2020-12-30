package com.github.p9yp9y.nodeserver.service;

import java.io.BufferedOutputStream;
import java.io.IOException;
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

	private void startReader(Socket socket) throws IOException {
		ObservableInputStream in = new ObservableInputStream(socket.getInputStream());
		new Thread() {
			public void run() {
				byte[] buffer = new byte[256];
				try {
					while (true) {
						int l = in.read(buffer);
						System.out.println("out:" + new String(buffer, 0, l));
					}
				} catch (IOException e) {
					logger.error(e.toString(), e);
				}
			};
		}.start();
	}

	public void setColor(final int red, final int green, final int blue) throws IOException {
		String data = "31%02x%02x%02x00f00f";
		data = String.format(data, red, green, blue);
		System.out.println("data " + data);
		System.out.println("DATA " + DatatypeConverter.parseHexBinary(data));
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
		return (byte) (res & 0xff);
	}

	private void sendPackage(final byte[] data) throws IOException {
		try (Socket socket = new Socket(host, 5577);
				BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream())) {
			out.write(data);
			out.write(getHash(data));
			out.flush();
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
	}
}
