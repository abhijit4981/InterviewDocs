package com.cosmos.cameldemo.process;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

@Component
public class ProcessorDemo {

    public void processRequest(){
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:start")
                            .to("seda:end");
                }
            });
        } catch (Exception e) {
            System.out.println("Some Exception : "+e.getMessage());
        }
        camelContext.start();
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start","Hello Everyone");

        ConsumerTemplate consumerTemplate= camelContext.createConsumerTemplate();
        String message = consumerTemplate.receiveBody("seda:end",String.class);
        System.out.println("Got the message : "+message);
    }
}
