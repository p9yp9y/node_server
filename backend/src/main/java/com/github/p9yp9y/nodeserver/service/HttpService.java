package com.github.p9yp9y.nodeserver.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class HttpService {
	public void get(final String url, final OutputStream outputStream) throws IOException {
		URL url2 = new URL(url);
		HttpURLConnection con = (HttpURLConnection) url2.openConnection();
		con.setRequestMethod("GET");
		IOUtils.copy(con.getInputStream(), outputStream);
	}
}
