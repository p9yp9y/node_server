package com.github.p9yp9y.nodeserver.service;

import java.io.IOException;

import org.junit.Test;

public class TennisinfoServiceTests {

	@Test
	public void getResultsContent() throws IOException {
		new TennisinfoService().getResultsContent("2020-01-10");
	}
}
