package com.wordmaster;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SmokeTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	SmokeTestController smokeTestController;
	
	private static final String CONTEXT_PATH = "wordmaster";

	@Test
	public void contextLoads() {
		assertThat(smokeTestController).isNotNull();
	}
	
	@Test
	public void shouldReturnTestMessage()  {
		assertThat(
				this.restTemplate.getForObject("http://localhost:" + port + CONTEXT_PATH + "/smoketest", String.class))
				.contains("wordmaster test passed!");
		
	}

}
