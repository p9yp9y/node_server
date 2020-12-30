package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.p9yp9y.nodeserver.service.MagicHomeService;

@RestController
@RequestMapping(value = "/magichome")
public class MagicHomeController {
	private static final Logger logger = LoggerFactory.getLogger(MagicHomeController.class);

	@Value("${magichome.led.host:}")
	private String host;

	@Autowired
	private MagicHomeService magicHomeService;

	@PostConstruct
	public void init() {
		connect();
	}

	@GetMapping(value = "/connect")
	public String doConnect() {
		connect();
		return "OK";
	}

	@GetMapping(value = "/on")
	public String doOn() throws UnknownHostException, IOException {
		magicHomeService.turnLed(true);
		return "OK";
	}

	@GetMapping(value = "/off")
	public String doOff() throws UnknownHostException, IOException {
		magicHomeService.turnLed(false);
		return "OK";
	}

	@GetMapping(value = "/setColor/{red}/{green}/{blue}")
	public String doSetColor(@PathVariable final int red, @PathVariable final int green, @PathVariable final int blue)
			throws UnknownHostException, IOException {
		magicHomeService.setColor(red, green, blue);
		return "OK";
	}

	private void connect() {
		magicHomeService.connect(host);
	}
}
