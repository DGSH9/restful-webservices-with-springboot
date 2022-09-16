package com.dgsh.rest.webservices.restfulwebservices.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dgsh.rest.webservices.restfulwebservices.helloworld.bean.HellowWorldBean;

@RestController
public class HellowWordlController {
	
	@GetMapping("/helloworld")
	public String hellowWord() {
		return "Hello World";
	}
	
	//hellow world bean
	@GetMapping("/hello-world-bean")
	public HellowWorldBean displayHellowWorld() {
		return new HellowWorldBean("Hello World");
	}
	
	
	//path parameters
	//users/{id}/todos/{id}  =>
	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HellowWorldBean displayPathVariable(@PathVariable String name) {
		return new HellowWorldBean("Hello "+ name);
	}
		
	
}
