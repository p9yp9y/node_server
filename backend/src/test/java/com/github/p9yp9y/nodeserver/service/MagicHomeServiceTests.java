package com.github.p9yp9y.nodeserver.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class MagicHomeServiceTests {
	MagicHomeService magicHomeService = new MagicHomeService();

	@Test
	public void testGetHash() throws IOException {
		System.out.println("!!!" + (byte) 0xa3);

		assertEquals(magicHomeService.getHash(new byte[] { (byte) 0x00 }), (byte) 0);

		assertEquals(magicHomeService.getHash(new byte[] { (byte) 0x01 }), (byte) 1);

		assertEquals(magicHomeService.getHash(new byte[] { (byte) 0x01, (byte) 0x01 }), (byte) 2);

		assertEquals(magicHomeService.getHash(new byte[] { (byte) 0x71, (byte) 0x24, (byte) 0x0f }), (byte) 0xa4);
	}

	@Test
	public void testSetColor() throws IOException, InterruptedException {
		magicHomeService.connect("192.168.1.5");
		
		magicHomeService.setColor(1, 1, 255);

		Thread.sleep(5000);
		for (int i = 0; i < 256; i += 1) {
			System.out.println("i :" + i);
			magicHomeService.setColor(i, i, i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
