package com.github.p9yp9y.nodeserver.service;

import java.io.IOException;

import org.junit.Test;

public class HttpServiceTests {

	@Test
	public void get() throws IOException {
		new HttpService().get("http://example.com", System.out);
	}
}
