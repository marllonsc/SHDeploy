package com.marllonsc.br.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("message","Welcome!!!");
		return "welcome";
	}
}
