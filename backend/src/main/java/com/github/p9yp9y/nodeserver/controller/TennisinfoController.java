package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.p9yp9y.nodeserver.service.TennisinfoService;

@RestController
public class TennisinfoController {
	final private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	final private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	@Value("${tournamentId}")
	private String tournamentId;

	@Autowired
	private TennisinfoService tennisinfoService;

	@GetMapping(value = "/tennisinfo")
	public String tennisinfo(final Model model) throws IOException {
		List<Object[][]> results = tennisinfoService.getResultsContent(today(), tournamentId);
		model.addAttribute("results", results);
		model.addAttribute("time", LocalDateTime.now().format(timeFormatter));
		return "tennisinfo";
	}

	// sport_event[@status="not_started"]/season[@tournament_id="sr:tournament:31263"]/..

	private String today() {
		return LocalDate.now().format(dateFormatter);
	}
}
