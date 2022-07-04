package com.cosmos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Java8Application implements CommandLineRunner {
	@Autowired
	private LambdaExpressionPOC lambdaExpressionPOC;
	public static void main(String[] args) {
		SpringApplication.run(Java8Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		lambdaExpressionPOC.AddMethodImpl();
	}
}
