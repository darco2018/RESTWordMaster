package com.wordmaster;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmokeTestController {
	
	@GetMapping("/smoketest")
	public String hello() {
		
		return "smoke test passed!";
	}

}
