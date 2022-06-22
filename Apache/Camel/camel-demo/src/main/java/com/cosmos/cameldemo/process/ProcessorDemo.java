package com.cosmos.cameldemo.process;

import org.apache.camel.*;
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
                            .process(new Processor() {
                                @Override
                                public void process(Exchange exchange) throws Exception {
                                    String msg = exchange.getIn().getBody().toString();
                                    msg = msg + " - By Abhijit Mishra";
                                    exchange.getOut().setBody(msg);
                                }
                            })
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
