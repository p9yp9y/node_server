package com.github.p9yp9y.nodeserver.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.Test;

public class ExecServiceTests {

	@Test
	public void exec() throws IOException {
		new ExecService().exec("ls", System.out);
	}

	@Test
	public void execWrong() {
		assertThrows(IOException.class, () -> {
			new ExecService().exec("wrongcommand123", System.out);
		});
	}
}
