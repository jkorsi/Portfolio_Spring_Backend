package com.js.portfolio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
		RestTemplate restTemplate = (RestTemplate) ctx.getBean("protoRestTemplate");
		System.out.println(restTemplate);
	}

}
