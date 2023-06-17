package com.odth.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

	@GetMapping({"/", "/boards*", "/boards/*", "/cond/*", "/main" })
	public String nexaView(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if(session == null) {
			session = request.getSession(true);
		}

		return "nexaMain";
	}
}
