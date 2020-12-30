package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.p9yp9y.nodeserver.service.ExecService;

@RestController
@RequestMapping(value = "/exec")
public class ExecController {
	@Autowired
	private ExecService execService;

	@GetMapping(value = "/")
	public void exec(final HttpServletResponse response, @RequestParam(name = "command", required = false) final String command)
			throws IOException {
		execService.exec(command, response.getOutputStream());
	}
}
