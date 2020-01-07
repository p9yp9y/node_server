package com.github.p9yp9y.nodeserver.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping(value = "/hello")
	public String hello(@RequestParam(name = "name", required = false, defaultValue = "World") final String name, final Model model)
			throws IOException {
		model.addAttribute("name", name);
		return "hello";
	}
	
	@RequestMapping(value = "/")
	public ModelAndView index() {
		
		return new ModelAndView("redirect:/myhome");
	}
}
