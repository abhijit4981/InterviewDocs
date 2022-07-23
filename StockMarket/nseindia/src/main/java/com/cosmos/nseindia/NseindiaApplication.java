package com.cosmos.nseindia;

import com.cosmos.nseindia.util.NseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class NseindiaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NseindiaApplication.class, args);
	}

	@Autowired
	private NseUtility nseUtility;
	@Bean
	public RestTemplate simpleRestTemplate() {
		return new RestTemplateBuilder()
				.rootUri("https://www.nseindia.com/")
				.setConnectTimeout(Duration.ofMillis(10000))
				.setReadTimeout(Duration.ofMillis(10000))
				.messageConverters(new StringHttpMessageConverter(), new MappingJackson2HttpMessageConverter())
				.build();
	}

	@Override
	public void run(String... args) throws Exception {
		nseUtility.readExcelFile();
	}
}
