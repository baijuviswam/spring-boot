package com.lab.app.photoapp.api.account.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private Environment env;
	
	@GetMapping("/status/health-check")
	public String healthCheck(){
		return "200 OK"+env.getProperty("local.server.port");
	}
}
