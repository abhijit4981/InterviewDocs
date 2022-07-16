package com.cosmos.cameldemo.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcess implements Processor {

    public void process(Exchange exchange) throws Exception {
        System.out.println("Hardcode value");
        System.out.println(exchange.getIn().getBody(String.class));
    }
}