package com.github.p9yp9y.nodeserver.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class MagicHomeServiceTests {

	@Test
	public void testGetHash() throws IOException {
		System.out.println("!!!" + (byte) 0xa3);

		MagicHomeService magicHomeService = new MagicHomeService();
		assertEquals(magicHomeService.getHash(new byte[] {(byte) 0x00}), (byte)0);

		assertEquals(magicHomeService.getHash(new byte[] {(byte) 0x01}), (byte)1);

		assertEquals(magicHomeService.getHash(new byte[] {(byte) 0x01, (byte) 0x01}), (byte)2);

		assertEquals(magicHomeService.getHash(new byte[] {(byte) 0x71, (byte) 0x24, (byte) 0x0f}), (byte)0xa4);
	}
}
