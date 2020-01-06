package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.p9yp9y.nodeserver.service.HttpService;

@Controller
<<<<<<< HEAD
@RequestMapping(value = "/http")
public class HttpController {
	@Autowired
	private HttpService httpService;
=======
public class HttpController {
	@Autowired
	private HttpService httpService;

	@RequestMapping(value = "/")
	public void hello(final HttpServletResponse response) throws IOException {
		response.getOutputStream().write("Hello".getBytes());
	}
>>>>>>> branch 'master' of https://github.com/p9yp9y/node_server.git

	@RequestMapping(value = "/get")
	public void get(final HttpServletResponse response, @RequestParam(name = "url", required = false) final String url) throws IOException {
		httpService.get(url, response.getOutputStream());
	}
}
