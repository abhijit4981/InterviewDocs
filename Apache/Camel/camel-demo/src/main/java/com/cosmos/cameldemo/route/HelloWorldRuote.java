package com.cosmos.cameldemo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldRuote extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        System.out.println("Hello Abhijit...");
    }
}
