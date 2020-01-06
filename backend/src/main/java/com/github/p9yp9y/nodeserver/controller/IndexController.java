package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping(value = "/")
	public void hello(final HttpServletResponse response) throws IOException {
		response.getOutputStream().write("Hello".getBytes());
	}
}
