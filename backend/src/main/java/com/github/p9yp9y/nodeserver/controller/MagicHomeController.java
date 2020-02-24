package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.p9yp9y.nodeserver.service.MagicHomeService;

@Controller
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

	@RequestMapping(value = "/connect")
	public String doConnect() {
		connect();
		return "OK";
	}

	@RequestMapping(value = "/on")
	public String doOn() throws UnknownHostException, IOException {
		magicHomeService.turnLed(true);
		return "OK";
	}

	@RequestMapping(value = "/off")
	public String doOff() throws UnknownHostException, IOException {
		magicHomeService.turnLed(false);
		return "OK";
	}

	@RequestMapping(value = "/setColor/{red}/{green}/{blue}")
	public String doSetColor(@PathVariable final byte red, @PathVariable final byte green, @PathVariable final byte blue)
			throws UnknownHostException, IOException {
		magicHomeService.setColor(red, green, blue);
		return "OK";
	}

	private void connect() {
		magicHomeService.connect(host);
	}
}
