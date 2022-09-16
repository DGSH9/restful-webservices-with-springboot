package com.dgsh.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowWordlController {
	
	@GetMapping("/helloworld")
	public String hellowWord() {
		return "Hello World";
	}
}
