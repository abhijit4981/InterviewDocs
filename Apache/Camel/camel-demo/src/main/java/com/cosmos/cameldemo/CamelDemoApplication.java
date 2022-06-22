package com.cosmos.cameldemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelDemoApplication implements CommandLineRunner {
	@Autowired
	private HelloWorld helloWorld;

	public static void main(String[] args) {
		SpringApplication.run(CamelDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		helloWorld.executeCamelProgram();
	}
}