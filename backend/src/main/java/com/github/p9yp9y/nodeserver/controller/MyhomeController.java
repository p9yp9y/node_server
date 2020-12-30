package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyhomeController {
	@GetMapping(value = "/myhome")
	public String myhome()
			throws IOException {
		return "myhome";
	}
}
