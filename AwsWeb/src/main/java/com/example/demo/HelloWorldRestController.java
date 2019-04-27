package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

	@GetMapping("/hello")
	public String sayHello(@RequestParam String msg) {
		return "Hello " + msg;
	}
}
