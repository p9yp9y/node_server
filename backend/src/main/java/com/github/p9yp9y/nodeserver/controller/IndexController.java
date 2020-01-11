package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@Value("${otp.azonosito}")
	private String azonosito;
	
	@Value("${otp.telekod}")
	private String telekod;
	
	@RequestMapping(value = "/")
	public ModelAndView index() {

		return new ModelAndView("redirect:/myhome");
	}

	@RequestMapping(value = "/hello")
	public String hello(@RequestParam(name = "name", required = false, defaultValue = "World") final String name,
			final Model model) throws IOException {
		model.addAttribute("name", name);
		return "hello";
	}

	@RequestMapping(value = "/otp")
	public String otp(final Model model) throws IOException {
		Document doc = Jsoup.connect(
				"https://www.otpbankdirekt.hu/homebank/do/bankkartyaEgyenlegLekerdezes?muvelet=login&azonosito="
						+ azonosito + "&telekod=" + telekod)
				.get();
		String html = doc.select("div.formrow span strong").get(0).html().replaceAll("\\W", "");
		int count = Integer.parseInt(html);
		model.addAttribute("count", (1000000 - count) + " Ft");
		return "otp";
	}
}
