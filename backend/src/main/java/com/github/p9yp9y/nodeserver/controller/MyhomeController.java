package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyhomeController {
	@RequestMapping(value = "/myhome")
	public String myhome()
			throws IOException {
		return "myhome";
	}
}
