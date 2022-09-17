package com.dgsh.rest.webservices.restfulwebservices.helloworld.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dgsh.rest.webservices.restfulwebservices.helloworld.bean.HellowWorldBean;

@RestController
public class HellowWordlController {
	
	private MessageSource messageSource;
	public HellowWordlController(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

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
	
	@GetMapping("/hello-world-internationalized")
	public String hellowWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
		
		//1.Store Somewhere  ==>good.morning.message=Good Morning
		// Example: `en` - English(Good Morning)
		// Example: `nl` - Dutch(Goedemorgen)
		// Example: `fr` - French(Bonjour)
		// Example: `de` - Deutsch(Guten Morgen)
		//2.Code to pick those value
	}
		
	
}
