package com.cosmos.cameldemo;

import com.cosmos.cameldemo.files.FileCopy;
import com.cosmos.cameldemo.hello.HelloWorld;
import com.cosmos.cameldemo.process.CallAClassMethod;
import com.cosmos.cameldemo.process.ConsumeRestCall;
import com.cosmos.cameldemo.process.ProcessorDemo;
import com.cosmos.cameldemo.route.ConsumeRestCallRoute;
import com.cosmos.cameldemo.route.FileCopyRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelDemoApplication implements CommandLineRunner {
	//@Autowired
	private HelloWorld helloWorld;
	//@Autowired
	private FileCopy fileCopy;
	//@Autowired
	private ProcessorDemo processorDemo;
	//@Autowired
	private CallAClassMethod callAClassMethod;
	@Autowired
	private ConsumeRestCall consumeRestCall;

	public static void main(String[] args) {
		SpringApplication.run(CamelDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//helloWorld.executeCamelProgram();
		//fileCopy.copyFile();
		//processorDemo.processRequest();
		//callAClassMethod.callMethodUsingClassRoute();
		consumeRestCall.callRestClassRoute02();
	}
}
