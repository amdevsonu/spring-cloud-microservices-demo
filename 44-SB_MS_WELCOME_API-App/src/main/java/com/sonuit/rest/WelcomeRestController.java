package com.sonuit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonuit.GreetFeignClient;

@RestController
public class WelcomeRestController {
	@Autowired
	private GreetFeignClient greetclient;
	
	@GetMapping("/welcome")
	public String getWelcomeMsg() {
		String welcomemsg =  "Welcome Sonu";
		String greetmsg = greetclient.invokeGreetApi();
		return welcomemsg + ", " + greetmsg;
	}
}
