package com.github.p9yp9y.nodeserver.service;

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

	private OutputStream out;

	private ObservableInputStream in;

	private Socket socket;

	private String host;

	public void connect(final String host) throws IOException {
		this.host = host;
	}

	public void turnLed(final boolean on) throws IOException {
		String data;
		if (on) {
			data = "71230fa3";
		} else {
			data = "71240fa4";
		}
		sendPackage(DatatypeConverter.parseHexBinary(data));
	}

	private void sendPackage(final byte[] data) throws IOException {
	
		try {
socket = new Socket(host, 5577);
			in = new ObservableInputStream(socket.getInputStream());
			out = socket.getOutputStream();
			
			out.write(data);
			out.flush();
out.close();
in.close();
socket.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
}
