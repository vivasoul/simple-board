package com.odth.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping({"/", "/boards*", "/boards/*", "/cond/*" })
	public String nexaView(Model model) {

		return "nexaMain";
	}
}
