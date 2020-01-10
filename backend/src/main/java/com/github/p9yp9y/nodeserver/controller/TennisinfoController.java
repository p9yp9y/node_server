package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.p9yp9y.nodeserver.service.TennisinfoService;

@Controller
public class TennisinfoController {
	final private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Autowired
	private TennisinfoService tennisinfoService;

	@RequestMapping(value = "/tennisinfo")
	public String tennisinfo(final Model model) throws IOException {
		String content = tennisinfoService.getResultsContent(today());
		model.addAttribute("content", content);
		return "tennisinfo";
	}

	// sport_event[@status="not_started"]/season[@tournament_id="sr:tournament:31263"]/..


	private String today() {
		return LocalDate.now().format(formatter);
	}
}