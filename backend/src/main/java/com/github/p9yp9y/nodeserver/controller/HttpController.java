package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.p9yp9y.nodeserver.service.HttpService;

@RestController
@RequestMapping(value = "/http")
public class HttpController {
	@Autowired
	private HttpService httpService;

	@GetMapping(value = "/get")
	public void get(final HttpServletResponse response, @RequestParam(name = "url", required = false) final String url) throws IOException {
		httpService.get(url, response.getOutputStream());
	}
}
