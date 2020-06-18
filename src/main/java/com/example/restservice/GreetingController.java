package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Value("${study_message}")
    private String greeterMessageFormat;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World!!") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping("/welcome")
	public Greeting welcome(@RequestParam(value = "name", defaultValue = "World!!") String name) {
		String temp=System.getenv().getOrDefault("welcome_message", "Hi, %s");
		return new Greeting(counter.incrementAndGet(), String.format(temp, name));
	}
	
	@GetMapping("/replay")
	public Greeting replay(@RequestParam(value = "name", defaultValue = "World!!") String name) {
		String temp=System.getenv().getOrDefault("replay_message", "Hii, %s");
		return new Greeting(counter.incrementAndGet(), String.format(temp, name));
	}
	
	@GetMapping("/study")
	public Greeting study(@RequestParam(value = "name", defaultValue = "World!!") String name) {
//		String temp=System.getenv().getOrDefault("replay_message", "Hii");
		return new Greeting(counter.incrementAndGet(), String.format(greeterMessageFormat, name));
	}
}
