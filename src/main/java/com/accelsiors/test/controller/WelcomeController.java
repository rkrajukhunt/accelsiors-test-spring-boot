package com.accelsiors.test.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to process application main page
 * 
 * @author ZoltanS
 *
 */

@Controller
public class WelcomeController {
	// inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@GetMapping("/")
	public String main(Model model, Principal principal) {
		model.addAttribute("message", message);
		model.addAttribute("user", principal == null ? "Guest" : principal.getName());

		return "welcome-test"; // view
	}

}
