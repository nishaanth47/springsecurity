package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hellocontroller {
	@GetMapping("/")
	public String greet()
	{
		return "welcome";
	}
}
