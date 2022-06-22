package com.cosmos.cameldemo.process;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

@Component
public class CallAClassMethod {
    public void callMethodUsingClassRoute(){
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:start")
                            .to("class:com.cosmos.cameldemo.hello.HelloWorld?method=executeCamelProgramForBuiltInClass");
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        camelContext.start();
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start","Hello Everyone");
    }
}
