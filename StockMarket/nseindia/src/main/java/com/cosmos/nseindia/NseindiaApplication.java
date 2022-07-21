package com.cosmos.nseindia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class NseindiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NseindiaApplication.class, args);
	}

	@Bean
	public RestTemplate simpleRestTemplate() {
		return new RestTemplateBuilder()
				.rootUri("https://www.nseindia.com/")
				.setConnectTimeout(Duration.ofMillis(10000))
				.setReadTimeout(Duration.ofMillis(10000))
				.messageConverters(new StringHttpMessageConverter(), new MappingJackson2HttpMessageConverter())
				.build();
	}
}
